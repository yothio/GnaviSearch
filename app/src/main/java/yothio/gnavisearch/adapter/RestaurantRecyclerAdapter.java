package yothio.gnavisearch.adapter;

import android.annotation.SuppressLint;
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
import yothio.gnavisearch.model.SearchResponse;
import yothio.gnavisearch.util.Convert;

/**
 * Created by yocchi on 2017/11/10.
 */

public class RestaurantRecyclerAdapter extends RecyclerView.Adapter<RestaurantRecyclerAdapter.RestaurantViewHolder> {

    private List<RestaurantItem> list;
    private LayoutInflater layoutInflater;
    private Context context;
    private ItemClickCallback callback;

    public RestaurantRecyclerAdapter(List<RestaurantItem> list, Context context, ItemClickCallback callback){
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.callback = callback;
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.list_item_restaurant,parent, false);
        return new RestaurantViewHolder(v);
    }
//        リストアイテムとの結びつけ
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {

        holder.nameTextView.setText(list.get(position).getName());
//        アクセスについての詳細があるかチェック
        if(list.get(position).getAccessLine() != null) {
            holder.accessTextView.setText(list.get(position).getAccessLine() +
                                            list.get(position).getAccessStation() +
                    Convert.accessTime(list.get(position).getAccessWalk())+"分");
        }
//        画像の結びつけ
        Picasso.with(context).load(list.get(position).getImageUri()).into(holder.shopImage);
        holder.itemView.setOnClickListener(view ->{
            callback.callback(holder.shopImage,position);
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    class RestaurantViewHolder extends RecyclerView.ViewHolder{

        TextView accessTextView;
        TextView nameTextView;
        ImageView shopImage;


        RestaurantViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.name_text_view);
            shopImage = itemView.findViewById(R.id.shop_image);
            accessTextView = itemView.findViewById(R.id.access_text_view);
        }
    }
}
