package me.danwi.utils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.danwi.eq.EQApplication;
import me.danwi.eq.interceptor.BaseHeaderInterceptor;
import me.danwi.eq.interceptor.CacheInterceptor;
import okhttp3.Interceptor;

/**
 * 继承EQApplication
 */
public class AppApplication extends EQApplication {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    @Override
    public String getUrl() {
        return "http://192.168.253.103:3000";
    }

    @Override
    public List<Interceptor> getPre() {
        List<Interceptor> pre = new ArrayList<>();
        pre.add(new BaseHeaderInterceptor() {
            @Override
            public Map<String, String> add() {
                Map<String, String> map = new HashMap<>();
                map.put("name", "Jerry");
                map.put("age", "23");
                return map;
            }
        });
        pre.add(new CacheInterceptor());
//        pre.add(new UploadProgressInterceptor());
        return pre;
    }

    @Override
    public List<Interceptor> getPost() {
//        List<Interceptor> post = new ArrayList<>();
//        post.add(new CacheInterceptor());
        return null;
    }

    //配置缓存目录

    @Override
    public String getDir() {
        return getExternalCacheDir().getPath() + "/cache";
    }
}
