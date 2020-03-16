package com.example.dcct.constants;

import com.example.dcct.model.InternetAPI;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 单例分为饿汉模式、懒汉模式、双重检查模式
 * 当单例有引用activity实例时，我们需要注意内存泄漏，为了避免内存泄漏我们需要将context替换成ApplicationContext
 * ApplicationContext存活于整个应用生命周期，而activity存活于一个较短的生命周期
 * 单例类一旦被创建，那么需要等到应用销毁自身才会被回收，这是它就是存活于一个长生命周期
 * 长生命周期引用短生命周期的实例，必定会导致GC无法回收短生命周期对象的实例，一直存在内存中无法被回收，导致内存泄漏
 */
public class NetWorkApi {//双重检查模式
    private volatile static NetWorkApi ourInstance = null;
    private Retrofit mRetrofit;

    public static NetWorkApi getInstance() {
        if (ourInstance == null) {
            synchronized (NetWorkApi.class) {
                if (ourInstance == null) {
                    ourInstance = new NetWorkApi();
                }
            }
        }
        return ourInstance;
    }

    private NetWorkApi() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl( Constant.BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() )
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create() )
                .build();
    }

    public InternetAPI getService() {
        return mRetrofit.create( InternetAPI.class );
    }

    public static <T>ObservableTransformer<T,T> applySchedulers(final Observer<T> observer) {
        return upstream -> {
            Observable<T> observable = upstream.subscribeOn( Schedulers.io() )
                    .observeOn( AndroidSchedulers.mainThread() );
            observable.subscribe( observer );
            return observable;
        };
    }
}
