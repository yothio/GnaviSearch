package yothio.gnavisearch.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import yothio.gnavisearch.R;
import yothio.gnavisearch.model.Rest;

/**
 * Created by yocchi on 2017/11/10.
 */

public class RestaurantRecyclerAdapter extends RecyclerView.Adapter<RestaurantRecyclerAdapter.RestaurantViewHolder> {

    List<Rest> list;
    LayoutInflater layoutInflater;
    Context context;

    public RestaurantRecyclerAdapter(List<Rest> list, Context context){
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.list_item_restaurant,parent, false);
        RestaurantViewHolder viewHolder = new RestaurantViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
        holder.nameTextView.setText(list.get(position).getName());
        if (list.get(position).getImageUrl() != null){
            Picasso.with(context).load(list.get(position).getImageUrl().getImageUrl1()).into(holder.shopImage);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RestaurantViewHolder extends RecyclerView.ViewHolder{

        TextView nameTextView;
        ImageView shopImage;

        public RestaurantViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.name_text_view);
            shopImage = itemView.findViewById(R.id.shop_image);
        }
    }
}
