package yothio.gnavisearch.network.api;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import yothio.gnavisearch.util.Const;

/**
 * Created by yocchi on 2017/11/13.
 */

public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {

            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(Const.API_BASE_URL);
            // gson
            builder.addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()));
            return builder.build();
        }
        return retrofit;
    }
}
