package yothio.gnavisearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import yothio.gnavisearch.adapter.RestaurantRecyclerAdapter;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.restaurant_recycler_view)
    RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        butterknifeの事前準備
        ButterKnife.bind(this);

        adapter = new RestaurantRecyclerAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}
