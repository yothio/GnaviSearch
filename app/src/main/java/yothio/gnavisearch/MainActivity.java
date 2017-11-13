package yothio.gnavisearch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import yothio.gnavisearch.adapter.RestaurantRecyclerAdapter;
import yothio.gnavisearch.network.api.EscApiManager;
import yothio.gnavisearch.model.SearchResponse;
import yothio.gnavisearch.model.Rest;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.restaurant_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.name_edit_text)
    EditText nameEditText;

    List<Rest> list = new ArrayList<>();

    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        butterknifeの事前準備
        ButterKnife.bind(this);

        Rest rest = new Rest();
        rest.setName("test");
        list.add(rest);

        adapter = new RestaurantRecyclerAdapter(this.list,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.send_button)
    void sendBtnClick(){
        EscApiManager.getRestaurants(nameEditText.getText().toString(), response -> {
            list.clear();
            list.addAll(response.getRest());
            adapter.notifyItemChanged(0);
        });
    }
}
