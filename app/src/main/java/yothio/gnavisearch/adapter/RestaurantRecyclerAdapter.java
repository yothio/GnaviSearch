package yothio.gnavisearch.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import yothio.gnavisearch.R;
import yothio.gnavisearch.adapter.model.Restaurant;

/**
 * Created by yocchi on 2017/11/10.
 */

public class RestaurantRecyclerAdapter extends RecyclerView.Adapter<RestaurantRecyclerAdapter.RecyclerViewHolder> {

    List<Restaurant> list;
    LayoutInflater layoutInflater;
    Context context;
    FragmentManager fm;

    public RestaurantRecyclerAdapter(List<Restaurant> list,Context context){
        this.list = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.list_item_restaurant,parent, false);

        return new RecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.nameTextView.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder{

        TextView nameTextView;

        public RecyclerViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.name_text_view);
        }
    }
}
