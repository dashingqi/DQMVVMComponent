package com.dashingqi.base.widget.history

import android.content.Context
import android.text.Editable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.alibaba.android.arouter.facade.model.TypeWrapper
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.KeyboardUtils
import com.dashingqi.base.empty.EmptyTextWatcher
import com.dashingqi.base.providers.mmkv.IMMKVProviders
import com.dashingqi.library_base.R
import com.dashingqi.library_base.databinding.BaseHistoryLayoutBinding

/**
 * @author : zhangqi
 * @time : 2020/6/10
 * desc : 搜索历史布局
 *
 * 1. 第一个实现的目标：点击查询或者键盘上的查询，隐藏历史布局，实现正常的搜索结果页面（已完成）
 *    1) 点击搜索完后，需要把键盘给隐藏了
 *    2) EditText不需要焦点
 * 2. 第二个实现目标：点击查询完后，再次展示历史布局的时候，需要把刚才的搜索内容展示在历史搜索布局上(已完成)
 *    1）展示布局的时候，去展示之前的
 *    2）点击查询后，把当前的搜索的存储到MMKV中
 *
 * 3. 第三个目标：点击历史布局中的item，能去查询数据
 *     1)注册adaper条目点击事件监听
 *     2）editext文本设置，长度设置，不获取焦点
 *     3）发出请求
 *
 * 4. 点击删除按钮，删除当前布局上的数据，再次进入不会展示之前的查询记录（已完成）
 *     1）清空适配器中的数据，重新刷新布局
 *     2）持久化存储一个空的集合
 */
class DQHistoryLayout : ConstraintLayout {

    var adapter: DQHistoryLayoutAdapter

    var historyBinding = BaseHistoryLayoutBinding
            .bind(LayoutInflater.from(context)
                    .inflate(R.layout.base_history_layout, this, false))

    var mmkv = ARouter.getInstance().navigation(IMMKVProviders::class.java)


    private var mmkvKey = ""
    var mSearchText: EditText? = null
    lateinit var mContentView: View
    var mSearchListener: ((key: String) -> Unit)? = null

    constructor(context: Context) : super(context, null) {
    }

    constructor(context: Context, attributes: AttributeSet) : super(context, attributes) {
    }

    init {
        /**
         * 添加布局
         */
        addView(historyBinding.root)
        adapter = DQHistoryLayoutAdapter(LayoutInflater.from(context))
        //设置适配器
        historyBinding.tagFlowLayout.adapter = adapter
        /**
         * 清除记录的事件监听
         */
        historyBinding.ivClearHistory.setOnClickListener { view ->
            /**
             * 刷新适配器数据
             */
            adapter.data.clear()
            adapter.notifyDataChanged()
            //进行一次数据持久化存储，只不过这次的数据源为空的集合,存储空也能拿到空
            updateDataToMMKV()
        }

        /**
         * 记录条目的点击事件
         */
        historyBinding.tagFlowLayout.setOnTagClickListener { _, position, _ ->
            var content = adapter.data[position]
            mSearchText?.setText(content)
            mSearchText?.setSelection(content.length)
            //触发键盘搜索按钮的监听事件
            mSearchText?.onEditorAction(EditorInfo.IME_ACTION_SEARCH)
            true
        }
    }

    /**
     * 对外提供的方法
     * key:用来标示要查询那个分类（可能是首页的查询，也可能是项目的查询）
     * search:输入框
     * contentView:要显示内容的区域
     * autoSearch:是否要实时查询 默认不是
     * listener：对外提供的回调
     */
    fun bindExitText(key: String, searchText: EditText, contentView: View, autoSearch: Boolean = false, listener: ((key: String) -> Unit)) {
        mmkvKey = key
        mSearchText = searchText
        mContentView = contentView
        mSearchListener = listener
        getDataFromMMKV()

        if (autoSearch) {
            //实时查询
            searchText.addTextChangedListener(object : EmptyTextWatcher() {
                override fun afterTextChanged(s: Editable?) {
                    //当输入框发生变化后，就回调
                    listener.invoke(s.toString())
                }
            })
        } else {
            //不是实时查询，监控EdiText的焦点
            searchText.onFocusChangeListener = OnFocusChangeListener { v, hasFocus ->
                if (hasFocus) {
                    //获得焦点，展示历史布局
                    showHistory()
                } else {
                    //失去焦点，不展示历史布局，展示正常的数据布局
                    hideHistory()
                }
            }

            searchText.setOnClickListener {
                //点击 意味着获取到焦点了
                showHistory()
            }

        }

        //监听键盘的搜索按钮
        searchText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                //拿到当前的输入框中输入的内容
                var content = searchText.text.toString().trim()
                search(content)
                true
            }
            false
        }

    }

    /**
     * 展示历史布局
     */
    private fun showHistory() {
        this.visibility = View.VISIBLE
        mContentView.visibility = View.GONE
    }

    /**
     * 隐藏历史布局
     */
    private fun hideHistory() {
        this.visibility = View.GONE
        mContentView.visibility = View.VISIBLE
    }

    /**
     * 查询方法，也是对外提供的，比如搜索框右边加一个搜索按钮
     */
    fun search(key: String) {
        if (key.isNotEmpty()) {
            //当之前的查询记录中存在过，就将它删除
            if (adapter.data.contains(key)) {
                adapter.data.remove(key)
            }
            //将新查询的关键字，放到第一个位置
            adapter.data.add(0, key)
            //刷新当前数据源头
            adapter.notifyDataChanged()
            //以上操作都是，针对当前适配器来说的，我们还要把数据进行持久化存储,把当前的数据源重新存储一下
            updateDataToMMKV()

            mSearchListener?.invoke(key)
            mSearchText?.clearFocus()
            mSearchText?.let {
                KeyboardUtils.hideSoftInput(it)
            }
        }else{


        }
    }

    /**
     * 从MMKV中获取数据,用于第一次打开布局
     */
    private fun getDataFromMMKV() {
        var type = object : TypeWrapper<List<String>>() {}.type
        var data: MutableList<String> =
                mmkv.getDefaultMMKV().getObject(MMKV_COMMON_PATH + mmkvKey, type)
                        ?: ArrayList()
        adapter.data.clear()
        adapter.data.addAll(data)
        adapter.notifyDataChanged()
    }

    /**
     * 将数据存储到mmkv中
     */
    private fun updateDataToMMKV() {
        mmkv.getDefaultMMKV().putObject(MMKV_COMMON_PATH + mmkvKey, adapter.data)
    }

    companion object {
        const val MMKV_COMMON_PATH = "dq_search_history_path_"
    }

}