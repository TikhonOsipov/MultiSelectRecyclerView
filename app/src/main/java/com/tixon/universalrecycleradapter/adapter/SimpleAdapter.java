package com.tixon.universalrecycleradapter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tixon.universalrecycleradapter.R;

import java.util.ArrayList;

/**
 * Created by tikhon.osipov on 12.04.2016.
 */
public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder> {

    private ArrayList<String> entries;

    public SimpleAdapter(ArrayList<String> list) {
        this.entries = new ArrayList<>(list);
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new SimpleViewHolder(inflater.inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.position = position;
        holder.textView.setText(entries.get(position));
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public class SimpleViewHolder extends MultiSelectViewHolder {
        TextView textView;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textViewAdapter);
        }

        @Override
        void click(View v, long position) {

        }

        @Override
        View setSelectionView(View itemView) {
            return itemView.findViewById(R.id.selection);
        }
    }
}
