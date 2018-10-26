package com.skumbam.flickerassignmet;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by skumbam on 10/25/18.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("/services/rest/")
    Call<PhotoCollectionResponse> getImages(@FieldMap HashMap<String,Object> imageRequestData);
}
