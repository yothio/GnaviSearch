package yothio.gnavisearch.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;
import yothio.gnavisearch.adapter.model.Restaurant;

/**
 * Created by yocchi on 2017/11/13.
 */

public class EscJsonApi {
    private EscJsonApi.Service service;

    public EscJsonApi() {
        service = ApiClient.getClient().create(EscJsonApi.Service.class);
    }


    public void searchRestaurantForName(String name, Callback<Restaurant> callback) {
        Call<Restaurant> call = service.searchName("", "大阪", "json");
        call.enqueue(callback);
    }

    interface Service {
        @GET(".")
        Call<Restaurant> searchName(@Query("keyid") String key, @Query("name") String name, @Query("format") String format);
    }

}
