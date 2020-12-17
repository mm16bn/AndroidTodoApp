package com.example.simpletodo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{

    List<String> items;
    OnLongClickListener longClickListener;

    public interface OnLongClickListener {
        void onItemLongClicked(int position);
    }

    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
    }

    // Creates each view
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Create new view and wrap inside view holder
        // Use layout inflater to inflate a view
        View todoView = LayoutInflater.from(viewGroup.getContext()).inflate(
                android.R.layout.simple_list_item_1,
                viewGroup, false);

        return new ViewHolder(todoView);
    }

    // Takes data and puts into view holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        // grab the item at the position
        String item = items.get(i);

        // bind the item to the specified view holder
        viewHolder.bind(item);
    }

    // Number of items available in data, or in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Container to provide easy access ot view to rep each item in list
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvItem;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        // Updates the view inside the view holder
        public void bind(String item) {
            tvItem.setText(item);

            // delete item on long click
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    // when long pressed, notify listener this position was specifically
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;    // consumes long click
                }
            });
        }
    }
}

