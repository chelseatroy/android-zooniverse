package com.zooniverse.android.android_zooniverse.projects;

import dagger.Module;

@Module(
        injects = {
                ProjectsRequestGenerator.class
        },
        complete = false,
        library = true
)
public class ProjectsModule {

}
