package com.zooniverse.android.android_zooniverse.infrastructure;

import android.content.Context;

import com.zooniverse.android.android_zooniverse.projects.ProjectsModule;

import dagger.Module;

@Module(
        includes = {
                ProjectsModule.class,
        },
        injects = {
                //whitelisted classes
        },
        complete = false,
        library = true
)
public class AppModule {
    private final Context context;

    public AppModule(final Context context) {
        this.context = context;
    }
}
