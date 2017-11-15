package yothio.gnavisearch;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import yothio.gnavisearch.databinding.ActivityRestaurantDetailBinding;
import yothio.gnavisearch.model.Rest;

public class RestaurantDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRestaurantDetailBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_restaurant_detail);
        binding.setRest(getIntent().getParcelableExtra("test"));
    }
}
