package com.zooniverse.android.android_zooniverse.projects;

import android.os.Parcel;

import com.zooniverse.android.android_zooniverse.infrastructure.AppBroadcastReceiver;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                ProjectsRequestGenerator.class,
                ProjectListActivity.class,
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

}
