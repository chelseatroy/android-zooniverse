package com.zooniverse.android.android_zooniverse;

import com.zooniverse.android.android_zooniverse.projects.TestProjectsModule;

import dagger.Module;

@Module(
        includes = {
                TestProjectsModule.class,
        },
        injects = {
                //whitelisted classes
        },
        library = true,
        overrides = true,
        complete = false
)
public class TestAppModule {
    public TestAppModule() {}
}
