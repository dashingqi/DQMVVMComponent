package com.dashingqi.module.web

import android.os.Bundle
import android.text.Html
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.just.agentweb.AgentWeb
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.web_activity_common_web.*


@Route(path = "/web/commonView")
class CommonWebActivity : AppCompatActivity() {

    @JvmField
    @Autowired
    var url = ""

    @JvmField
    @Autowired
    var title = ""

    lateinit var agentWeb: AgentWeb

    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_activity_common_web)
        Logger.d("url ---> $url")
        webTitle.text = Html.fromHtml(title).toString()
        agentWeb = AgentWeb.with(this)
                .setAgentWebParent(webContainerLayout, LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(url)
        back.setOnClickListener {
            finish()
        }
    }


    override fun onResume() {
        agentWeb.webLifeCycle.onResume()
        super.onResume()
    }

    override fun onPause() {
        agentWeb.webLifeCycle.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        agentWeb.webLifeCycle.onDestroy()
        super.onDestroy()
    }
}
