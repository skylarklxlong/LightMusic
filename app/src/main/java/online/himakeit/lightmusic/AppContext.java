package online.himakeit.lightmusic;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.facebook.common.internal.Supplier;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import online.himakeit.lightmusic.util.LogUtils;
import online.himakeit.lightmusic.util.NetUtils;
import online.himakeit.lightmusic.util.ToastUtil;

/**
 * @author：LiXueLong
 * @date：2018/1/31
 * @mail1：skylarklxlong@outlook.com
 * @mail2：li_xuelong@126.com
 * @des:
 */
public class AppContext extends Application {
    private static final String TAG = "AppContext";
    /**
     * 全局应用的上下文
     */
    static AppContext mAppContext;

    static Handler mHandler;

    static Gson mGson;

    private static int MAX_MEM = (int) Runtime.getRuntime().maxMemory() / 4;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppContext = this;
        mHandler = new Handler();

        ToastUtil.register(this);

        initFresco();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        /**
         * 清空内存缓存（包括Bitmap缓存和未解码图片的缓存）
         */
        imagePipeline.clearMemoryCaches();
    }

    private void initFresco() {
        Fresco.initialize(this, getConfigureCaches(this));
    }

    private ImagePipelineConfig getConfigureCaches(Context context) {
        final MemoryCacheParams cacheParams = new MemoryCacheParams(
                /**
                 * 内存缓存中总图片的最大大小,以字节为单位。
                 */
                MAX_MEM,
                /**
                 * 内存缓存中图片的最大数量。
                 */
                Integer.MAX_VALUE,
                /**
                 * 内存缓存中准备清除但尚未被删除的总图片的最大大小,以字节为单位。
                 */
                MAX_MEM,
                /**
                 * 内存缓存中准备清除的总图片的最大数量。
                 */
                Integer.MAX_VALUE,
                /**
                 * 内存缓存中单个图片的最大大小。
                 */
                Integer.MAX_VALUE / 10
        );

        Supplier<MemoryCacheParams> memoryCacheParamsSupplier = new Supplier<MemoryCacheParams>() {
            @Override
            public MemoryCacheParams get() {
                return cacheParams;
            }
        };

        ImagePipelineConfig.Builder builder = ImagePipelineConfig.newBuilder(this).setDownsampleEnabled(true);
        builder.setBitmapMemoryCacheParamsSupplier(memoryCacheParamsSupplier);

        return builder.build();
    }

    public static Handler getHandler() {
        if (mHandler == null) {
            mHandler = new Handler();
        }
        return mHandler;
    }

    public static Gson gsonInstance() {
        if (mGson == null) {
            mGson = new Gson();
        }
        return mGson;
    }

    /**
     * 获取全局的AppContext
     *
     * @return
     */
    public static AppContext getAppContext() {
        return mAppContext;
    }

    public static OkHttpClient defaultOkHttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.writeTimeout(30 * 1000, TimeUnit.MILLISECONDS);
        client.readTimeout(30 * 1000, TimeUnit.MILLISECONDS);
        client.connectTimeout(30 * 1000, TimeUnit.MILLISECONDS);
        //设置缓存路径
        File httpCacheDirectory = new File(mAppContext.getCacheDir(), "okhttpCache");
        //设置缓存 10M
        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
        client.cache(cache);
        //设置拦截器
        client.addInterceptor(LOGGING_INTERCEPTOR);
        client.addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);
        client.addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);
        return client.build();
    }

    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            boolean hasNetWorkConection = NetUtils.hasNetWorkConection(AppContext.getAppContext());
            Request request = chain.request();
            if (!hasNetWorkConection) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response response = chain.proceed(request);
            if (hasNetWorkConection) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControlStr = request.cacheControl().toString();
                /**
                 * 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                 */
                response.newBuilder().removeHeader("Pragma")
                        .header("Cache-Control", cacheControlStr)
//                        .addHeader("user-agent","Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 7;
                response.newBuilder().removeHeader("Pragme")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                        .addHeader("user-agent","Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
                        .build();
            }
            return response;
        }
    };

    private static final Interceptor LOGGING_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            LogUtils.show(TAG, "-----LoggingInterceptor----- :\nrequest url:" + request.url() + "\ntime:" + (t2 - t1) / 1e6d + "\nbody:" + content + "\n");
            return response.newBuilder()
                    .body(ResponseBody.create(mediaType, content))
                    .build();
        }
    };

}
