package com.zooniverse.android.android_zooniverse.infrastructure;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zooniverse.android.android_zooniverse.R;

import java.util.ArrayList;

public class OnResultListPresenter {
    private View progressBar;
    private View listView;
    private View errorView;
    private View emptyListView;

    public void presentOnFailureList(View listLayout) {
        setListLayoutFields(listLayout);

        progressBar.setVisibility(View.GONE);
        listView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
        emptyListView.setVisibility(View.GONE);
    }

    public void presentOnSuccessList(AppListAdapter listAdapter, ArrayList resultList, View listLayout) {
        setListLayoutFields(listLayout);

        if (resultList.size() != 0) {
            ((RecyclerView) listView).setAdapter((RecyclerView.Adapter) listAdapter);
            listAdapter.setListItems(resultList);
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

    private void setListLayoutFields(View listLayout) {
        progressBar = listLayout.findViewById(R.id.progress_bar);
        listView = listLayout.findViewById(android.R.id.list);
        errorView = listLayout.findViewById(R.id.error_view);
        emptyListView = listLayout.findViewById(R.id.empty_list_view);
    }
}
