package com.zooniverse.android.android_zooniverse.projects;

import android.content.Context;
import android.os.Parcel;
import android.support.v7.widget.LinearLayoutManager;

import com.zooniverse.android.android_zooniverse.infrastructure.AppBroadcastReceiver;
import com.zooniverse.android.android_zooniverse.infrastructure.OnResultListPresenter;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                ProjectListActivity.class,
                ProjectListFragment.class,
                ProjectsRequestGenerator.class,
        },
        complete = false,
        library = true
)
public class ProjectsModule {

    public ProjectsModule() {
    }

    @Provides
    AppBroadcastReceiver appBroadcastReceiver() {
        return new AppBroadcastReceiver();
    }

    @Provides
    ProjectsRequestGenerator projectsRequestGenerator() {
        return new ProjectsRequestGenerator(Parcel.obtain());
    }

    @Provides
    OnResultListPresenter onResultListPresenter() {
        return new OnResultListPresenter();
    }

    @Provides
    LinearLayoutManager linearLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

    @Provides
    ProjectListAdapter projectListAdapter() {
        return new ProjectListAdapter();
    }
}
