package com.dashingqi.module.widget.tiktok

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.dashingqi.base.base.viewmodel.BasePageViewModel
import com.dashingqi.module.widget.R

/**
 * @author : zhangqi
 * @time : 2020/8/27
 * desc :
 */
class TikTokSnapHelperViewModel(application: Application) : BasePageViewModel<String>(application) {

    val liveData = MutableLiveData<String>()

    init {
        refresh()
        liveData.value = "zhangqi"
    }

    override fun requestData(page: Int) {
        items.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1598511988840&di" +
                "=639725388059d4c8e67624a06e1bd685&imgtype=0&src=http%3A%2F%2Fa4.att.hudong.com%2F52%2F52%2F01200000169026136208529565374.jpg")
        items.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1598511988839&di=233fe29176d7cfcdc12bdb5bf1edd330&imgtype=0&src=http%3A%2F%2Fa0.att.hudong.com%2F16%2F12%2F01300535031999137270128786964.jpg")

        items.add(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1598511988840&di=" +
                    "639725388059d4c8e67624a06e1bd685&imgtype=0&src=http%3A%2F%2Fa4.att.hudong.com%" +
                    "2F52%2F52%2F01200000169026136208529565374.jpg"
        )
        items.add(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1598511988839&di=" +
                    "233fe29176d7cfcdc12bdb5bf1edd330&imgtype=0&src=http%3A%2F%2Fa0.att.hudong.com%2F16%" +
                    "2F12%2F01300535031999137270128786964.jpg"
        )
        items.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1598511988837&di=4776e5c37a6b118200a4cc65ae69222a&imgtype=0&src=http%3A%2F%2Fa3.att.hudong.com%2F65%2F38%2F16300534049378135355388981738.jpg")
        items.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1598511988836&di=ed75e313c966792039fc97080a9508b5&imgtype=0&src=http%3A%2F%2Ffile02.16sucai.com%2Fd%2Ffile%2F2014%2F0829%2F372edfeb74c3119b666237bd4af92be5.jpg")
    }

    override fun getItemLayoutId(): Int {
        return R.layout.widget_item_tik_tok_snap_helper_layout
    }
}