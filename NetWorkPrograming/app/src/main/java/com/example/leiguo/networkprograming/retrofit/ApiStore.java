package com.example.leiguo.networkprograming.retrofit;

import com.example.leiguo.networkprograming.bean.AcItem;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Lei Guo on 2016/10/29.
 */

public interface ApiStore {
    @GET("index.php/Api/getAcInfo")
    Observable<List<AcItem>> getAcItem(@Query("acid") int id);

}
