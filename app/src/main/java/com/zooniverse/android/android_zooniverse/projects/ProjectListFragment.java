package com.zooniverse.android.android_zooniverse.projects;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zooniverse.android.android_zooniverse.R;
import com.zooniverse.android.android_zooniverse.infrastructure.AppBroadcastReceiver;
import com.zooniverse.android.android_zooniverse.infrastructure.BroadcastResponder;
import com.zooniverse.android.android_zooniverse.infrastructure.GraphProvider;

import javax.inject.Inject;

public class ProjectListFragment extends Fragment implements BroadcastResponder{
    @Inject
    AppBroadcastReceiver appBroadcastReceiver;

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
    public void onResume() {
        super.onResume();

        appBroadcastReceiver.register(this);
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(getActivity());
        instance.registerReceiver(appBroadcastReceiver, new IntentFilter(ProjectListActivity.GET_PROJECTS_LIST));
    }

    @Override
    public void onSuccess(String action, Object info) {
        Toast.makeText(getActivity(), "CALL SUCCESSFUL", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure(String action, Object info) {
        Toast.makeText(getActivity(), "CALL FAILFUL", Toast.LENGTH_LONG).show();
    }
}
