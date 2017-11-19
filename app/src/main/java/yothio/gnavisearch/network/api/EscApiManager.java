package yothio.gnavisearch.network.api;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yothio.gnavisearch.model.SearchResponse;


/**
 * Created by yocchi on 2017/11/13.
 */

public class EscApiManager {

    private static EscJsonApi escJsonApi;

    private static EscJsonApi getEscJsonApi() {
        if (escJsonApi == null) escJsonApi = new EscJsonApi();
        return escJsonApi;
    }
    public static void getRestaurants(String name, final EscApiCallback<SearchResponse> callback) {
        getEscJsonApi().searchRestaurantForName(name, new Callback<SearchResponse>() {

            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                Log.d("EscApiManager", response.message());
                callback.callback(response.body());
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Log.d("EscApiManager", t.getMessage());

            }
        });
    }
    public static void getRestaurantsForRange(int i,float latitude,float longitude,final EscApiCallback<SearchResponse> callback) {
        Log.d("EscApiManager", "i:" + i);
        getEscJsonApi().searchRestaurantForRange(i,latitude,longitude,new Callback<SearchResponse>() {

            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                Log.d("EscApiManager", response.message());
                callback.callback(response.body());
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Log.d("EscApiManager", t.getMessage());

            }
        });
    }

}
