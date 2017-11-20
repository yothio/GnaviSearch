package yothio.gnavisearch.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yothio.gnavisearch.R;
import yothio.gnavisearch.adapter.RestaurantItem;
import yothio.gnavisearch.adapter.RestaurantRecyclerAdapter;
import yothio.gnavisearch.model.SearchResponse;
import yothio.gnavisearch.network.api.EscApiManager;
import yothio.gnavisearch.util.Const;
import yothio.gnavisearch.util.PermissionUtil;

import static yothio.gnavisearch.util.Const.GPS_PERMISSIONS;
import static yothio.gnavisearch.util.Const.GPS_REQUEST_CODE;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.restaurant_recycler_view)
    RecyclerView recyclerView;
    List<RestaurantItem> list = new ArrayList<>();
    private RecyclerView.Adapter adapter;
    String dummyImageUrl = "http://androck.jp/wp-content/uploads/file/apps/ICON/1/140.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        butterknifeの事前準備
        ButterKnife.bind(this);

//        recyclerViewのクリック処理をcallbackとして渡す
        adapter = new RestaurantRecyclerAdapter(this.list, this, position -> {
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, recyclerView.findViewById(R.id.shop_image), getString(R.string.restaurant_image));
            Intent intent = new Intent(MainActivity.this, RestaurantDetailActivity.class);
            intent.putExtra(Const.INTENT_KEY, list.get(position));
            ActivityCompat.startActivity(MainActivity.this, intent, options.toBundle());
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int totalCount = recyclerView.getAdapter().getItemCount(); //合計のアイテム数
                int childCount = recyclerView.getChildCount(); // RecyclerViewに表示されてるアイテム数
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();

                if (layoutManager instanceof GridLayoutManager) { // GridLayoutManager
                    GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                    int firstPosition = gridLayoutManager.findFirstVisibleItemPosition(); // RecyclerViewに表示されている一番上のアイテムポジション
                    if (totalCount == childCount + firstPosition) {
                        // ページング処理
                        // GridLayoutManagerを指定している時のページング処理
                    }
                } else if (layoutManager instanceof LinearLayoutManager) { // LinearLayoutManager
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                    int firstPosition = linearLayoutManager.findFirstVisibleItemPosition(); // RecyclerViewの一番上に表示されているアイテムのポジション
                    if (totalCount == childCount + firstPosition) {
                        // ページング処理
                        // LinearLayoutManagerを指定している時のページング処理
                        Log.d("MainActivity", "ページング処理");
                    }
                }
            }
        });
    }

    @OnClick(R.id.search_button)
    void sendBtnClick() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle(R.string.search_area_text);

        // 表示アイテムを指定する //
        String[] items = {"300m", "500m", "1000m", "2000m", "3000m"};

        dialogBuilder.setItems(items, (dialogInterface, selectItemIndex) ->
//                自身の周りから一定距離を検索する
                EscApiManager.getRestaurantsForRange(selectItemIndex, 34.700387f, 135.4906603f, response -> {
                    list.clear();
                    for (SearchResponse.Rest rest : response.getRest()) {
                        list.add(convertResponseItem(rest));
                    }
                    adapter.notifyItemChanged(0);
                }));


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (PermissionUtil.hasSelfPermissions(this, GPS_PERMISSIONS)) {
                dialogBuilder.create().show();
            } else {
                // 権限がない場合は、パーミッション確認アラートを表示する
                requestPermissions(GPS_PERMISSIONS, GPS_REQUEST_CODE);
            }
        } else {
            dialogBuilder.create().show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    //    リストに追加するアイテムの一部を変換する
    private RestaurantItem convertResponseItem(SearchResponse.Rest rest) {
        RestaurantItem item = new RestaurantItem();
//        空の場合はダミーurlに変更
        item.setImageUri(Objects.equals(rest.getImageUrl().getImageUrl1().toString(), "{}") ? dummyImageUrl : rest.getImageUrl().getImageUrl1().toString());
        item.setName(rest.getName());
        item.setTel(rest.getTel());
        item.setAddress(rest.getAddress());
//        空の場合は文字列を追加
        item.setOpenTime(Objects.equals(rest.getOpenTime().toString(), "{}") ? "詳細はありません" : rest.getOpenTime().toString());
//        アクセスが空の場合は最寄り駅なども省くためのチェック
        if (!Objects.equals(rest.getAccess().getLine().toString(), "{}")) {
            item.setAccessLine(rest.getAccess().getLine().toString());
            item.setAccessStation(rest.getAccess().getStation().toString());
            item.setAccessWalk(rest.getAccess().getWalk().toString());
        }
        return item;
    }


}
