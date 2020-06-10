package com.dashingqi.module.widget.BottomSheetDialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.LinearLayoutManager
import com.dashingqi.module.widget.R
import com.dashingqi.module.widget.databinding.WidgetBottomSheetDialogLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


/**
 *
 * @ProjectName: DQMVVMComponent
 * @Package: com.dashingqi.module.widget.BottomSheetDialog
 * @ClassName: DQWidgetBottomSheetDialog
 * @Author: DashingQI
 * @CreateDate: 2020/6/10 10:01 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/10 10:01 PM
 * @UpdateRemark:
 * @Version: 1.0
 */
class DQWidgetBottomSheetDialog:BottomSheetDialogFragment() {

    var list = ArrayList<String>()

    private var mBottomSheetBehavior: BottomSheetBehavior<View>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var viewBinding = WidgetBottomSheetDialogLayoutBinding.inflate(inflater, container, false)
        initData()
        viewBinding.rv.layoutManager = LinearLayoutManager(context)
        viewBinding.rv.adapter = BottomSheetDialogAdapter(list)

        return viewBinding.root
    }



    private val mBottomSheetBehaviorCallback: BottomSheetCallback = object : BottomSheetCallback() {
        override fun onStateChanged(@NonNull bottomSheet: View, newState: Int) {
            //禁止拖拽，
            if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                //设置为收缩状态
                mBottomSheetBehavior!!.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }

        override fun onSlide(@NonNull bottomSheet: View, slideOffset: Float) {}
    }

    override fun onStart() {
        super.onStart()
//        var myDialog = dialog
//        if (myDialog!=null){
//            var bottomSheet = myDialog.findViewById<View>(R.id.design_bottom_sheet)
//            bottomSheet.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
//        }
//
//        var myView = view
//        myView?.post {
//            var parent = myView.parent as View
//            val params = parent.layoutParams as CoordinatorLayout.LayoutParams
//            val behavior = params.behavior
//            mBottomSheetBehavior = behavior as BottomSheetBehavior
//            mBottomSheetBehavior?.setBottomSheetCallback(mBottomSheetBehaviorCallback)
//            val display = activity!!.windowManager.defaultDisplay
//            //设置高度
//            //设置高度
//            val height = display.height / 2
//            mBottomSheetBehavior?.setPeekHeight(height)
//        }
    }


    private fun initData(){
        for (index in 0..30){
            list.add("${index}位置")
        }
    }
}