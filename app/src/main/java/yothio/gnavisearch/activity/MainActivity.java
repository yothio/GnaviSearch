package yothio.gnavisearch.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yothio.gnavisearch.R;
import yothio.gnavisearch.adapter.RestaurantItem;
import yothio.gnavisearch.adapter.RestaurantRecyclerAdapter;
import yothio.gnavisearch.network.model.SearchResponse;
import yothio.gnavisearch.network.GpsManager;
import yothio.gnavisearch.network.api.EscApiManager;
import yothio.gnavisearch.util.Const;
import yothio.gnavisearch.util.PermissionUtil;

import static yothio.gnavisearch.util.Const.GPS_PERMISSIONS;
import static yothio.gnavisearch.util.Const.GPS_REQUEST_CODE;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.restaurant_recycler_view)
     RecyclerView recyclerView;
    private List<RestaurantItem> list = new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private GpsManager gpsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // butterKnifeの事前準備
        ButterKnife.bind(this);
        // GPS関連のクラスのインスタンスの作成
        gpsManager = new GpsManager(this);

        // recyclerViewのクリック処理をcallbackとして渡す
        adapter = new RestaurantRecyclerAdapter(this.list, this, (view, position) -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                view.findViewById(R.id.shop_image).setTransitionName("restaurant_transition_key");
            }
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, view, getString(R.string.restaurant_image));
            Intent intent = new Intent(MainActivity.this, RestaurantDetailActivity.class);
            intent.putExtra(Const.INTENT_KEY, list.get(position));
            ActivityCompat.startActivity(MainActivity.this, intent, options.toBundle());

        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    // 現在地取得中を管理するフラグ
    private boolean alreadyGetGps = false;

    @OnClick(R.id.search_button)
    void sendBtnClick() {

        if (alreadyGetGps){
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (PermissionUtil.hasSelfPermissions(this, GPS_PERMISSIONS)) {
                alreadyGetGps =true;
                // 現在地を取得して、ダイアログに反映
                gpsManager.getLocation(location -> showRestaurantDialog(location.getLatitude(),location.getLongitude()));
            } else {
                // 権限がない場合は、パーミッション確認アラートを表示する
                requestPermissions(GPS_PERMISSIONS, GPS_REQUEST_CODE);
            }
        } else {
            alreadyGetGps =true;
            // 現在地を取得して、ダイアログに反映
            gpsManager.getLocation(location -> showRestaurantDialog(location.getLatitude(),location.getLongitude()));
        }
    }

    /**
     * 受け取った現在地からレストランを検索する
     * @param latitude
     * @param longitude
     */
    private void showRestaurantDialog(double latitude,double longitude) {

        alreadyGetGps = false;

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle(R.string.search_area_text);

        // 表示アイテムを指定する //
        String[] items = {"300m", "500m", "1000m", "2000m", "3000m"};

        dialogBuilder.setItems(items, (dialogInterface, selectItemIndex) ->
                // 自身の周りから一定距離を検索する
                EscApiManager.getRestaurantsForRange(selectItemIndex, latitude, longitude, response -> {
                    list.clear();
                    for (SearchResponse.Rest rest : response.getRest()) {
                        list.add(convertResponseItem(rest));
                    }
                    adapter.notifyItemChanged(0);
                }));
        dialogBuilder.create().show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // アラート表示中に画面回転すると length ０でコールバックされるのでガードする
        if (grantResults.length > 0 && requestCode == GPS_REQUEST_CODE) {
            if (!PermissionUtil.checkGrantResults(grantResults)) {
                // 今後確認しないにチェックが入っているか確認
                if (!PermissionUtil.shouldShowRequestPermissionRationale(this, permissions, grantResults)) {
                    // 今後確認しないにチェックが入っている場合
                    PermissionUtil.showAlertDialog(getSupportFragmentManager());
                }
            } else {
                sendBtnClick();
            }
        }

    }

    //    リストに追加するアイテムの一部を変換する
    private RestaurantItem convertResponseItem(SearchResponse.Rest rest) {
        RestaurantItem item = new RestaurantItem();
        // 空の場合はダミーurlに変更
        String dummyImageUrl = "http://androck.jp/wp-content/uploads/file/apps/ICON/1/140.png";
        item.setImageUri(Objects.equals(rest.getImageUrl().getImageUrl1().toString(), "{}") ? dummyImageUrl : rest.getImageUrl().getImageUrl1().toString());
        item.setName(rest.getName());
        item.setTel(rest.getTel());
        item.setAddress(rest.getAddress());
        // 空の場合は文字列を追加
        item.setOpenTime(Objects.equals(rest.getOpenTime().toString(), "{}") ? "詳細はありません" : rest.getOpenTime().toString());
        // アクセスが空の場合は最寄り駅なども省くためのチェック
        if (!Objects.equals(rest.getAccess().getLine().toString(), "{}")) {
            item.setAccessLine(rest.getAccess().getLine().toString());
            item.setAccessStation(rest.getAccess().getStation().toString());
            item.setAccessWalk(rest.getAccess().getWalk().toString());
        }
        item.setLatitude(Double.parseDouble(rest.getLatitude()));
        item.setLongitude(Double.parseDouble(rest.getLongitude()));

        return item;
    }
}
