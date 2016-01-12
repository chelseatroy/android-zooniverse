package com.zooniverse.android.android_zooniverse;

import com.zooniverse.android.android_zooniverse.projects.TestProjectsModule;

import org.mockito.Mock;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

import static org.mockito.MockitoAnnotations.initMocks;

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
    public TestAppModule() {
        initMocks(this);
    }

    @Mock
    RestAdapter mockRestAdapter;

    @Provides
    RestAdapter mockRestAdapter() {
        return mockRestAdapter;
    }
}
