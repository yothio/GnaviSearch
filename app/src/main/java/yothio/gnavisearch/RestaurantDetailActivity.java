package yothio.gnavisearch;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.squareup.picasso.Picasso;

import yothio.gnavisearch.databinding.ActivityRestaurantDetailBinding;
import yothio.gnavisearch.model.Rest;
import yothio.gnavisearch.util.Const;

public class RestaurantDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRestaurantDetailBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_restaurant_detail);
        binding.setRest(getIntent().getParcelableExtra(Const.INTENT_KEY));
//        Picasso.with(this).load(binding.getRest().getImageUrl().getImageUrl1()).into(binding.restaurantImage);
    }
}
