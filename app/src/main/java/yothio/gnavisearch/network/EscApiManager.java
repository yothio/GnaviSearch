package yothio.gnavisearch.network;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yothio.gnavisearch.adapter.model.Restaurant;

/**
 * Created by yocchi on 2017/11/13.
 */

public class EscApiManager {

    private static EscJsonApi escJsonApi;

    private static EscJsonApi getEscJsonApi() {
        if (escJsonApi == null) escJsonApi = new EscJsonApi();
        return escJsonApi;
    }

    public static void getRestaurantList(String name, final List<Restaurant> list) {
        getEscJsonApi().searchRestaurantForName(name, new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                Log.d("EscApiManager", response.message());
                for(Restaurant.Rest rest:response.body().getRest()){
                    Log.d("EscApiManager", rest.getName().toString());
                }
            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {
                Log.d("EscApiManager", t.getMessage());
            }
        });

    }

}
