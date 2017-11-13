package yothio.gnavisearch.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import yothio.gnavisearch.R;
import yothio.gnavisearch.model.SearchResponse.Rest;

/**
 * Created by yocchi on 2017/11/10.
 */

public class RestaurantRecyclerAdapter extends RecyclerView.Adapter<RestaurantRecyclerAdapter.RestaurantViewHolder> {

    List<Rest> list;
    LayoutInflater layoutInflater;

    public RestaurantRecyclerAdapter(List<Rest> list, Context context){
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RestaurantViewHolder extends RecyclerView.ViewHolder{

        TextView nameTextView;

        public RestaurantViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.name_text_view);
        }
    }
}
