package com.zooniverse.android.android_zooniverse.projects;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zooniverse.android.android_zooniverse.R;
import com.zooniverse.android.android_zooniverse.infrastructure.AppBroadcastReceiver;
import com.zooniverse.android.android_zooniverse.infrastructure.BroadcastResponder;
import com.zooniverse.android.android_zooniverse.infrastructure.GraphProvider;
import com.zooniverse.android.android_zooniverse.infrastructure.OnResultListPresenter;

import java.util.ArrayList;

import javax.inject.Inject;

public class ProjectListFragment extends Fragment implements BroadcastResponder{
    @Inject
    AppBroadcastReceiver appBroadcastReceiver;

    @Inject
    OnResultListPresenter onResultListPresenter;

    @Inject
    ProjectListAdapter projectListAdapter;

    @Inject
    LinearLayoutManager layoutManager;

    private ArrayList<ProjectsResponse.Project> projectList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GraphProvider.getInstance().getGraph().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(android.R.id.list);
        recyclerView.setLayoutManager(layoutManager);
        projectListAdapter.setListItems(projectList);
        recyclerView.setAdapter(projectListAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        appBroadcastReceiver.register(this);
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(getActivity());
        instance.registerReceiver(appBroadcastReceiver, new IntentFilter(ProjectListActivity.GET_PROJECTS_LIST));
    }

    @Override
    public void onSuccess(String action, Object info) {
        projectList = (ArrayList<ProjectsResponse.Project>) info;
        onResultListPresenter.presentOnSuccessList(
                projectListAdapter,
                projectList,
                getView()
        );
    }

    @Override
    public void onFailure(String action, Object info) {
        onResultListPresenter.presentOnFailureList(getView());
    }
}
