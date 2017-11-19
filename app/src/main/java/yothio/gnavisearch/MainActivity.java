package yothio.gnavisearch;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yothio.gnavisearch.adapter.RestaurantItem;
import yothio.gnavisearch.adapter.RestaurantRecyclerAdapter;
import yothio.gnavisearch.model.SearchResponse;
import yothio.gnavisearch.network.api.EscApiManager;
import yothio.gnavisearch.util.Const;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.restaurant_recycler_view)
    RecyclerView recyclerView;
    List<RestaurantItem> list = new ArrayList<>();
    private RecyclerView.Adapter adapter;

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
        String[] items = {"300m","500m","1000m","2000m","3000m"};

        dialogBuilder.setItems(items, (dialogInterface, selectItemIndex) ->
                EscApiManager.getRestaurants("test",selectItemIndex, response -> {
            list.clear();
            for (SearchResponse.Rest rest : response.getRest()) {
                RestaurantItem item = new RestaurantItem();
                item.setImageUri(rest.getImageUrl().getImageUrl1());
                item.setName(rest.getName());
                item.setTel(rest.getTel());
                item.setAddress(rest.getAddress());
                item.setOpenTime(rest.getOpenTime());

                item.setAccessLine(rest.getAccess().getLine());
                item.setAccessStation(rest.getAccess().getStation());
                item.setAccessWalk(rest.getAccess().getWalk());
                list.add(item);
            }
            adapter.notifyItemChanged(0);
        }));

        dialogBuilder.create().show();

    }


}
