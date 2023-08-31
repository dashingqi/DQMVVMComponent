package com.dashingqi.module.qrcode

import android.Manifest
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.route.RoutePath
import com.dashingqi.module.qrcode.utils.QRCodeUtils
import com.huawei.hms.hmsscankit.ScanUtil
import com.huawei.hms.ml.scan.HmsScan
import com.huawei.hms.ml.scan.HmsScanAnalyzerOptions
import kotlinx.android.synthetic.main.qrcode_activity_request.*


@Route(path = RoutePath.QrCode.QRCODE_HW)
class QrCodeRequestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qrcode_activity_request)
        btnLoadScan.setOnClickListener {
            requestPermission()
        }

        btnCreateQrCode.setOnClickListener {
            var url = etInputUrl.text.toString()
            if (url.isNotEmpty()) {
                var qrCodeBitmap = QRCodeUtils.createQRCode(url, 250, 250)
                ivQrCode.setImageBitmap(qrCodeBitmap)
            } else {
                Toast.makeText(this, "请输入url", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE), 1)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (permissions == null || grantResults == null) return

        if (requestCode == 1) {
            ScanUtil.startScan(this, 2, HmsScanAnalyzerOptions.Creator().setHmsScanTypes(HmsScan.QRCODE_SCAN_TYPE).create())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK || data == null) return
        if (requestCode == 2) {
            result.text = data.getParcelableExtra<HmsScan>(ScanUtil.RESULT)?.originalValue

        }
    }
}