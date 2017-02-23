package com.zlw.lifequan.net;

import android.text.TextUtils;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.zhy.http.okhttp.https.HttpsUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by lixinyuan on 16/5/10.
 */
class OkHttpManager {

    private static final String TAG = OkHttpManager.class.getSimpleName();

    private volatile static OkHttpManager okHttpManager;

    private OkHttpManager() {

        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new OkHttpLogger());
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addNetworkInterceptor(new StethoInterceptor())
                .addInterceptor(interceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        String method = request.method();
                        Headers headers = request.headers();
                        Logger.i(TAG, "headers:" + headers.toString());
                        Response response;
                        try {
                            response = chain.proceed(request);
                        } catch (Exception e) {
                            SpecialStatus.filterError2Toast(e);
                            throw e;
                        }
                        //特殊状态处理
                        if (response.code() != 200) {
                            ResponseBody responseBody = response.body();
                            if (responseBody == null) {
                                return response;
                            }
                            String responseBodyString = responseBody.string();
                            Logger.i(TAG, "responseBodyString :" + responseBodyString);
                            MediaType responseBodyContentType = responseBody.contentType();
                            if (TextUtils.isEmpty(responseBodyString) || responseBodyContentType == null) {
                                return response;
                            }
                            SpecialStatus.filter(responseBodyString);
                            return response.newBuilder().body(responseBody).build();
                        } else {
                            return response;
                        }
                    }

                })
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .build();

    }

    static OkHttpManager getSingleton() {
        if (okHttpManager == null) {
            synchronized (OkHttpManager.class) {
                if (okHttpManager == null) {
                    okHttpManager = new OkHttpManager();
                }
            }
        }
        return okHttpManager;
    }

    private OkHttpClient okHttpClient;

    OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    private static final class OkHttpLogger implements HttpLoggingInterceptor.Logger {

        @Override
        public void log(String message) {
            Logger.i(TAG, "" + message);
        }

    }

    private static class Logger {
        static boolean isShow = false;

        static void i(String tag, String format, Object... args) {
            if (isShow) {
                Logger.i(tag, format, args);
            }
        }

        static void e(String tag, String format, Object... args) {
            if (isShow) {
                Logger.e(tag, format, args);
            }
        }
    }


}
