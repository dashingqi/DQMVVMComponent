package com.dashingqi.module.widget.BottomSheetDialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dashingqi.module.widget.databinding.WidgetBottomSheetDialogLayoutBinding
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var viewBinding = WidgetBottomSheetDialogLayoutBinding.inflate(inflater, container, false)
        initData()
        viewBinding.rv.layoutManager = LinearLayoutManager(context)
        viewBinding.rv.adapter = BottomSheetDialogAdapter(list)
        return viewBinding.root
    }

    private fun initData(){
        for (index in 0..30){
            list.add("${index}位置")
        }
    }
}