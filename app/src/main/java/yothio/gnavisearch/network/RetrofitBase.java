package yothio.gnavisearch.network;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yocchi on 2017/11/13.
 */

public class RetrofitBase {


    private GnaviApi gnaviApi;
    static RetrofitBase retrofitBase = new RetrofitBase();
    private OkHttpClient client;
    private Retrofit retrofit;

    //singleton
    public static RetrofitBase getInstance() {
        return retrofitBase;
    }

    //constructor
    private RetrofitBase() {
        //通信前準備
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        httpClient.addInterceptor(logging);
        client = httpClient.build();
        //baseUrl・Convertの設定
        retrofit = new Retrofit.Builder().baseUrl("https://api.gnavi.co.jp/RestSearchAPI/20150630/").addConverterFactory(GsonConverterFactory.create()).client(client).build();
        gnaviApi = retrofit.create(GnaviApi.class);
    }



    //通信内容記述interface
    interface GnaviApi {
//
//        /**
//         * @param body id:username
//         *             password:password
//         * @return AccountResponse Model
//         */
//        @POST("blinky/login")
//        Call<AccountResponse> login(@Body HashMap<String, String> body);
//
//        /**
//         * @param apiToken
//         * @return image_path
//         */
//        @GET("/blinky/images/all")
//        Call<ImageResponse> getAllImage(@Query("api_token") String apiToken);
//
//        /**
//         * https://blinky.fdev2.net/blinky/register?id=user&name=naaame&password=password
//         *
//         * @param user     username
//         * @param name     nickname
//         * @param password password
//         * @return success or failed
//         */
//        @POST("blinky/register")
//        Call<AccountResponse> register(@Query("id") String user,
//                                       @Query("name") String name,
//                                       @Query("password") String password);
//
//        /**
//         * ログアウト通信
//         *
//         * @param api_token ログイン時に取得したapiトークン
//         * @return success or failed
//         */
//        @GET("/blinky/logout")
//        Call<AccountResponse> logout(@Query("api_token") String api_token);
//
//
//        /**
//         * ユーザ検索通信
//         *
//         * @param searchWord 検索文字列　utf8に変換して送る
//         * @param apiToken   apiトークン
//         * @param offset     検索開始位置
//         * @param limit      最大表示件数
//         * @return
//         */
//        @GET("/blinky/users/{search_word}")
//        Call<SearchUserResponse> searchUser(@Path("search_word") String searchWord,
//                                            @Query("api_token") String apiToken,
//                                            @Query("offset") int offset,
//                                            @Query("limit") int limit);
//
//        /**
//         * フレンド一覧通信
//         *
//         * @param myId
//         * @param apiToken
//         * @param offset
//         * @param limit
//         * @return
//         */
//        @GET("/blinky/users/{id}/friends/")
//        Call<SearchFriendResponse> searchFriend(@Path("id") String myId,
//                                                @Query("api_token") String apiToken,
//                                                @Query("offset") int offset,
//                                                @Query("limit") int limit);

    }

}
