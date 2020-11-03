package com.dashingqi.network.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dashingqi.network.bean.Book
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*

/**
 * @author : zhangqi
 * @time : 2020/5/18
 * desc : 创建服务的控制器
 */
class ServiceController(var baseUrl: String?,
                        var ignoreSSL: Boolean,
                        var keepCookie: Boolean,
                        var okBuilder: ((OkHttpClient.Builder) -> Unit)?,
                        var retrofitBuilder: ((Retrofit.Builder) -> Unit)?
) {

    /**
     * 创建OKHttpClient 主要用来设置公共的控制参数
     */
    private fun createOKHttp(): OkHttpClient {
        var builder = OkHttpClient.Builder()
        if (ignoreSSL) {
            builder.run {
                hostnameVerifier(createHostnameVerifier())
                        .sslSocketFactory(createSSLSocket(), createX509TrustManager())
            }
        }
        okBuilder?.invoke(builder)
        return builder.build()
    }

    /**
     * 创建Retrofit
     */
    private fun createRetrofit(): Retrofit {
        var builder = Retrofit.Builder()
        baseUrl?.let {
            builder.baseUrl(baseUrl)
        }
        builder.client(createOKHttp())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().registerTypeAdapter(LiveDataType().type, object :
                        TypeAdapter<LiveData<String>>() {
                    override fun write(out: JsonWriter, value: LiveData<String>?) {
                        out.value(value?.value)
                    }

                    override fun read(reader: JsonReader): LiveData<String> {
                        return MutableLiveData<String>().apply {
                            value = reader.nextString()
                        }
                    }

                }).create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        retrofitBuilder?.invoke(builder)
        return builder.build()
    }


    private fun createHostnameVerifier() = HostnameVerifier { _, _ -> true }

    /**
     * 创建SSLSocketFactory
     */
    private fun createSSLSocket(): SSLSocketFactory {
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, arrayOf<TrustManager>(createX509TrustManager()), SecureRandom())
        return sslContext.socketFactory
    }

    /**
     * 创建X509TrustManager
     */
    private fun createX509TrustManager(): X509TrustManager {
        return object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            }

            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf<X509Certificate>()
            }
        }
    }

    /**
     * 创建对应的Service接口类
     */
    fun <T> createService(cla: Class<T>): T = createRetrofit().create(cla)

    class LiveDataType : TypeToken<MutableLiveData<String>>() {}
}