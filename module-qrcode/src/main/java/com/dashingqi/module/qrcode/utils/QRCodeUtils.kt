package com.dashingqi.module.qrcode.utils

import android.graphics.Bitmap
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import java.util.*

/**
 * @author : zhangqi
 * @time : 2020/7/25
 * desc : 二维码生成工具
 */
object QRCodeUtils {

    fun createQRCode(url: String?, w: Int, h: Int): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            //判断URL合法性
            if (url == null || "" == url || url.length < 1) {
                return null
            }
            val hints =
                    Hashtable<EncodeHintType, String?>()
            hints[EncodeHintType.CHARACTER_SET] = "utf-8"
            //图像数据转换，使用了矩阵转换
            val bitMatrix =
                    QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, w, h, hints)
            val pixels = IntArray(w * h)
            //下面这里按照二维码的算法，逐个生成二维码的图片，
            //两个for循环是图片横列扫描的结果
            for (y in 0 until h) {
                for (x in 0 until w) {
                    if (bitMatrix[x, y]) {
                        pixels[y * w + x] = -0x1000000
                    } else {
                        pixels[y * w + x] = -0x1
                    }
                }
            }
            //生成二维码图片的格式，使用ARGB_8888
            bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
            bitmap.setPixels(pixels, 0, w, 0, 0, w, h)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
        return bitmap
    }
}