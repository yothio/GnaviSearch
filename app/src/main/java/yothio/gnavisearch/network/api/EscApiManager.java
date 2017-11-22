package yothio.gnavisearch.network.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yothio.gnavisearch.network.model.SearchResponse;


/**
 * Created by yocchi on 2017/11/13.
 */

public class EscApiManager {

    private static EscJsonApi escJsonApi;

    private static EscJsonApi getEscJsonApi() {
        if (escJsonApi == null) escJsonApi = new EscJsonApi();
        return escJsonApi;
    }

    /**
     *
     * @param i         自身の周囲の距離を選択したインデックスを渡す
     * @param latitude  緯度
     * @param longitude 軽度
     * @param callback  通信完了後に行いたい処理
     *
     *                  Apiとの通信の部分と画面とを繋ぐマネージャメソッド
     *                  必要に応じて大きくする
     */
    public static void getRestaurantsForRange(int i,double latitude,double longitude,final EscApiCallback<SearchResponse> callback) {
        getEscJsonApi().searchRestaurantForRange(i,latitude,longitude,new Callback<SearchResponse>() {

            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                callback.callback(response.body());
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {

            }
        });
    }

}
