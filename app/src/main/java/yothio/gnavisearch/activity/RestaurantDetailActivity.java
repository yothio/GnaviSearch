package yothio.gnavisearch.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import yothio.gnavisearch.R;
import yothio.gnavisearch.databinding.ActivityRestaurantDetailBinding;
import yothio.gnavisearch.util.Const;

public class RestaurantDetailActivity extends AppCompatActivity {

    private MapFragment mMapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRestaurantDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant_detail);
        binding.setRest(getIntent().getParcelableExtra(Const.INTENT_KEY));
        Picasso.with(this).load(binding.getRest().getImageUri()).into(binding.restaurantImage);

        // map表示の設定
        mMapFragment = MapFragment.newInstance();
        FragmentTransaction fragmentTransaction =
                getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.map, mMapFragment);
        fragmentTransaction.commit();
        mMapFragment.getMapAsync(googleMap -> {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(binding.getRest().getLatitude(), binding.getRest().getLongitude()), 14));

            googleMap.addMarker(new MarkerOptions().position(new LatLng(binding.getRest().getLatitude(), binding.getRest().getLongitude()))
                    .title(binding.getRest().getName()));

        });



    }
}
