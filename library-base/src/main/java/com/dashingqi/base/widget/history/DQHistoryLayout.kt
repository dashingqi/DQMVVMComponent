package com.dashingqi.base.widget.history

import android.content.Context
import android.text.Editable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
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
 * 1. 第一个实现的目标：点击查询或者键盘上的查询，隐藏历史布局，实现正常的搜索结果页面
 *    1) 点击搜索完后，需要把键盘给隐藏了
 *    2) EditText不需要焦点
 * 2. 第二个实现目标：点击查询完后，再次展示历史布局的时候，需要把刚才的搜索内容展示在历史搜索布局上
 *
 * 3. 第三个目标：点击历史布局中的item，能去查询数据
 */
class DQHistoryLayout : ConstraintLayout {

    var adapter: DQHistoryLayoutAdapter

    var historyBinding = BaseHistoryLayoutBinding
            .bind(LayoutInflater.from(context)
                    .inflate(R.layout.base_history_layout, this, false))

    var mmkv = ARouter.getInstance().navigation(IMMKVProviders::class.java)

    private val MMKV_COMMON_PATH = "DQ_WAN_ANDROID_PATH"

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
        adapter = DQHistoryLayoutAdapter(LayoutInflater.from(context), mutableListOf())
        //设置适配器
        historyBinding.tagFlowLayout.adapter = adapter
        historyBinding.ivClearHistory.setOnClickListener { view ->
            /**
             * 刷新适配器数据
             */
            adapter.data.clear()
            adapter.notifyDataChanged()
        }
    }

    /**
     * 对外提供的方法
     * key:用来标示要查询那个分类
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
                //拿到当前的输入框中输入内通
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

    private fun search(key: String) {
        if (key.isNotEmpty()) {
            mSearchListener?.invoke(key)
            mSearchText?.clearFocus()
            mSearchText?.let {
                KeyboardUtils.hideSoftInput(it)
            }

        }
    }

    /**
     * 从MMKV中获取数据
     */
    private fun getDataFromMMKV() {
        var type = object : TypeWrapper<List<String>>() {}.type
        var data: MutableList<String> = mmkv.getDefaultMMKV().getObject(MMKV_COMMON_PATH + mmkvKey, type)
        adapter.data.clear()
        adapter.data.addAll(data)
        adapter.notifyDataChanged()
    }


}