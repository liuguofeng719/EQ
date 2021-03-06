package me.danwi.eq;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.danwi.eq.entity.Upload;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by RunningSnail on 16/7/27.
 * 请求辅助类
 */
public class RequestUtils {

    private RequestUtils() {

    }

    /**
     * 组合文件上传参数
     *
     * @param uploadList
     * @return
     */
    public static Map<String, RequestBody> combine(List<Upload> uploadList) {
        Map<String, RequestBody> params = new HashMap<>();
        if (uploadList != null) {
            for (Upload upload : uploadList) {
                RequestBody requestBody;
                if (upload.value != null) {
                    requestBody = RequestBody.create(null, upload.value);
                    //普通键值对添加
                    params.put(upload.key, requestBody);
                } else {
                    requestBody = RequestBody.create(MultipartBody.FORM, new File(upload.filePath));
                    //包含文件
                    params.put(upload.key + "\";fileName=" + upload.fileName, requestBody);
                }
            }
        }
        return params;
    }
}
