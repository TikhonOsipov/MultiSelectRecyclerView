package com.tixon.universalrecycleradapter.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tixon.universalrecycleradapter.R;

import java.util.ArrayList;

/**
 * Created by tikhon.osipov on 12.04.2016.
 */
public class MultiSelectAdapter extends RecyclerView.Adapter<MultiSelectAdapter.MsViewHolder> {

    private ArrayList<String> entries;
    private ArrayList<Long> selectedPositions;
    boolean inSelectionMode;

    public MultiSelectAdapter(ArrayList<String> list) {
        init();
        this.entries = new ArrayList<>(list);
    }

    private void init() {
        selectedPositions = new ArrayList<>();
        inSelectionMode = false;
    }

    @Override
    public MsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MsViewHolder(inflater.inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(MsViewHolder holder, int position) {
        holder.position = position;
        holder.textView.setText(entries.get(position));
        Log.d("myLogs", "selectedPositions size: " + selectedPositions.size());
        if(!selectedPositions.isEmpty()) {
            holder.selectionIndicator.setVisibility(holder.position == selectedPositions.get(position) ? View.VISIBLE : View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public class MsViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener, View.OnClickListener {

        TextView textView;
        View selectionIndicator;
        long position;

        public MsViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textViewAdapter);
            selectionIndicator = itemView.findViewById(R.id.selection);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(inSelectionMode) {
                if(selectedPositions.contains(position)) {
                    Log.d("myLogs", "deselect: " + position);
                    selectedPositions.remove(position);
                    selectionIndicator.setVisibility(View.INVISIBLE);
                    if(selectedPositions.isEmpty()) {
                        Log.d("myLogs", "end selection");
                        inSelectionMode = false;
                    }
                } else {
                    Log.d("myLogs", "select: " + position);
                    selectedPositions.add(position);
                    selectionIndicator.setVisibility(View.VISIBLE);
                }
            } else {
                Log.d("myLogs", "click: " + position);
                Toast.makeText(v.getContext(), "click " + position, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if(!inSelectionMode) {
                Log.d("myLogs", "start selection: long click");
                selectionIndicator.setVisibility(View.VISIBLE);
                selectedPositions.add(position);
                inSelectionMode = true;
            }
            return true;
        }
    }
}
