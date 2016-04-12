package com.tixon.universalrecycleradapter.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by tikhon.osipov on 12.04.2016.
 */
public abstract class MultiSelectViewHolder extends RecyclerView.ViewHolder implements
        View.OnClickListener,
        View.OnLongClickListener {

    abstract void click(View v, long position);
    abstract View setSelectionView(View itemView);

    private static boolean isInSelectionMode = false;

    private static ArrayList<Long> selectedPositions = new ArrayList<>();
    private View selectionView = null;

    public long position;

    public MultiSelectViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        selectionView = setSelectionView(itemView);
    }

    @Override
    public void onClick(View v) {
        if(isInSelectionMode) {
            if(selectedPositions.contains(position)) {
                Log.d("myLogs", "deselect: " + position);
                selectedPositions.remove(position);
                if(selectionView != null) {
                    selectionView.setVisibility(View.INVISIBLE);
                }
                if(selectedPositions.isEmpty()) {
                    Log.d("myLogs", "end selection");
                    isInSelectionMode = false;
                }
            } else {
                Log.d("myLogs", "select: " + position);
                selectedPositions.add(position);
                if(selectionView != null) {
                    selectionView.setVisibility(View.VISIBLE);
                }
            }
        } else {
            Log.d("myLogs", "click: " + position);
            click(v, position);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if(!isInSelectionMode) {
            Log.d("myLogs", "start selection: long click");
            isInSelectionMode = true;
            selectedPositions.add(position);
            if(selectionView != null) {
                selectionView.setVisibility(View.VISIBLE);
            }
        }
        return true;
    }

    public ArrayList<Long> getSelectedPositions() {
        return selectedPositions;
    }
}
