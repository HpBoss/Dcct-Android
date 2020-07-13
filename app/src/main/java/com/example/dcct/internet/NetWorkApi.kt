package com.example.dcct.internet

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 单例分为饿汉模式、懒汉模式、双重检查模式
 * 当单例有引用activity实例时，我们需要注意内存泄漏，为了避免内存泄漏我们需要将context替换成ApplicationContext
 * ApplicationContext存活于整个应用生命周期，而activity存活于一个较短的生命周期
 * 单例类一旦被创建，那么需要等到应用销毁自身才会被回收，这是它就是存活于一个长生命周期
 * 长生命周期引用短生命周期的实例，必定会导致GC无法回收短生命周期对象的实例，一直存在内存中无法被回收，导致内存泄漏
 */
class NetWorkApi private constructor() {
    private val mRetrofit: Retrofit
    val service: InternetAPI
        get() = mRetrofit.create(InternetAPI::class.java)

    companion object {
        //双重检查模式
        @Volatile
        private var ourInstance: NetWorkApi? = null
        @JvmStatic
        val instance: NetWorkApi?
            get() {
                if (ourInstance == null) {
                    synchronized(NetWorkApi::class.java) {
                        if (ourInstance == null) {
                            ourInstance = NetWorkApi()
                        }
                    }
                }
                return ourInstance
            }

        @JvmStatic
        fun <T> applySchedulers(observer: Observer<T>): ObservableTransformer<T,T> {
            return ObservableTransformer { upstream: Observable<T> ->
                val observable = upstream.subscribeOn(io())
                        .observeOn(AndroidSchedulers.mainThread())
                observable.subscribe(observer)
                observable
            }
        }
    }

    init {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .addInterceptor { chain: Interceptor.Chain ->
                    val request = chain.request()
                    val build = request.newBuilder()
                            .addHeader("Authorization", "Jason ")
                            .build()
                    chain.proceed(build)
                }
        mRetrofit = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
}