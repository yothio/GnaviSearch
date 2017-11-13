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

public class EscJsonApi {
    private EscJsonApi.Service service;

    public EscJsonApi() {
        service = ApiClient.getClient().create(EscJsonApi.Service.class);
    }


    public void searchRestaurantForName(String name, Callback<SearchResponse> callback) {
        Call<SearchResponse> call = service.searchName(API_KEY.API_KEY, "大阪", "json");
        call.enqueue(callback);
    }

    interface Service {
        @GET(".")
        Call<SearchResponse> searchName(@Query("keyid") String key, @Query("name") String name, @Query("format") String format);
    }

}
