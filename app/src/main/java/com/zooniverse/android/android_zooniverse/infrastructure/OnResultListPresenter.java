package com.zooniverse.android.android_zooniverse.infrastructure;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class OnResultListPresenter {
    public void presentOnFailureList(View progressBar, View listView, View errorView, View emptyListView) {
            progressBar.setVisibility(View.GONE);
            listView.setVisibility(View.GONE);
            errorView.setVisibility(View.VISIBLE);
            emptyListView.setVisibility(View.GONE);
    }

    public void presentOnSuccessList(AppListAdapter listAdapter, ArrayList result, View progressBar, View listView, View errorView, View emptyListView) {
        if (result.size() != 0) {
            ((RecyclerView) listView).setAdapter((RecyclerView.Adapter) listAdapter);
            listAdapter.setListItems(result);
            listAdapter.wrapNotifyDataSetChanged();
            listView.setVisibility(View.VISIBLE);
            emptyListView.setVisibility(View.GONE);
        } else {
            emptyListView.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        }

        progressBar.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
    }
}
