package yothio.gnavisearch.network.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;
import yothio.gnavisearch.model.SearchResponse;
import yothio.gnavisearch.network.API_KEY;

/**
 * Created by yocchi on 2017/11/13.
 */

class EscJsonApi {
    private EscJsonApi.Service service;

    EscJsonApi() {
        service = ApiClient.getClient().create(EscJsonApi.Service.class);
    }


    void searchRestaurantForRange(int areaRangeIndex,double latitude,double longitude,Callback<SearchResponse> callback){
        Call<SearchResponse> call = service.searchArea(API_KEY.API_KEY,areaRangeIndex,latitude,longitude,"json");
        call.enqueue(callback);
    }

    interface Service {
        /**
         *
         * @param key           apiキー
         * @param areaRange     検索範囲
         * @param latitude      緯度
         * @param longitude     経度
         * @param format        レスポンス形式
         * @return              jsonに対応したモデル
         */
         @GET(".")
        Call<SearchResponse> searchArea(@Query("keyid") String key,
                                        @Query("range") int areaRange,
                                        @Query("latitude") double latitude,
                                        @Query("longitude") double longitude,
                                        @Query("format") String format);
        @GET(".")
        Call<SearchResponse> searchRestaurant(@Query("keyid") String key,
                                        @Query("range") int areaRange,
                                        @Query("latitude") float latitude,
                                        @Query("longitude") float longitude,
                                        @Query("format") String format);
    }

}
