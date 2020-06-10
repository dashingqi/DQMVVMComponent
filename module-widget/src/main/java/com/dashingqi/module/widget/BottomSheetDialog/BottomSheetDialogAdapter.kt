package com.dashingqi.module.widget.BottomSheetDialog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dashingqi.module.widget.databinding.WidgetBootomSheetDialogItemLayoutBinding

/**
 *
 * @ProjectName: DQMVVMComponent
 * @Package: com.dashingqi.module.widget.BottomSheetDialog
 * @ClassName: BottomSheetDialogAdapter
 * @Author: DashingQI
 * @CreateDate: 2020/6/10 10:09 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/10 10:09 PM
 * @UpdateRemark:
 * @Version: 1.0
 */
class BottomSheetDialogAdapter(var list:ArrayList<String> = ArrayList()): RecyclerView.Adapter<BottomSheetDialogAdapter.MyViewHolder>() {

    class MyViewHolder(var item:View) : RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var viewBinding = WidgetBootomSheetDialogItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(viewBinding.root)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var bind = DataBindingUtil.bind<WidgetBootomSheetDialogItemLayoutBinding>(holder.item)
        bind?.tv?.text = list[position]

    }
}