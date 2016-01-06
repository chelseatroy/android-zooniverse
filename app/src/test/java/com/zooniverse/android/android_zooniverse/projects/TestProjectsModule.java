package com.zooniverse.android.android_zooniverse.projects;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

@Module(
        injects = {
                ProjectListActivityTest.class,
                ProjectListAdapterTest.class,
        },
        library = true,
        overrides = true,
        complete = false
)
public class TestProjectsModule {
    public TestProjectsModule() {}

    @Provides
    ProjectsRequestGenerator mockProjectsRequestGenerator() {
        return mock(ProjectsRequestGenerator.class);
    }
}
