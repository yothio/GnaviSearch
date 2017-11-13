package yothio.gnavisearch.network.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;
import yothio.gnavisearch.model.SearchResponse;

/**
 * Created by yocchi on 2017/11/13.
 */

public class EscJsonApi {
    private EscJsonApi.Service service;

    public EscJsonApi() {
        service = ApiClient.getClient().create(EscJsonApi.Service.class);
    }


    public void searchRestaurantForName(String name, Callback<SearchResponse> callback) {
        Call<SearchResponse> call = service.searchName("b6a7071f10abcc2f6c94c14df171ed25", "大阪", "json");
        call.enqueue(callback);
    }

    interface Service {
        @GET(".")
        Call<SearchResponse> searchName(@Query("keyid") String key, @Query("name") String name, @Query("format") String format);
    }

}
