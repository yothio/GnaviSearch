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

    private List<Rest> list;
    private LayoutInflater layoutInflater;
    private Context context;
    private ItemClickCallback callback;

    public RestaurantRecyclerAdapter(List<Rest> list, Context context,ItemClickCallback callback){
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.callback = callback;
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
//        画像の結びつけ
            Picasso.with(context).load(list.get(position).getImageUrl().getImageUrl1()).into(holder.shopImage);

        holder.itemView.setOnClickListener(view ->{
            callback.callback(position);
        });
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
