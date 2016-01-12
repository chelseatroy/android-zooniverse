package com.zooniverse.android.android_zooniverse.projects;

import android.support.v7.widget.LinearLayoutManager;

import com.zooniverse.android.android_zooniverse.infrastructure.AppBroadcastReceiver;
import com.zooniverse.android.android_zooniverse.infrastructure.OnResultListPresenter;

import org.mockito.Mock;

import dagger.Module;
import dagger.Provides;

import static org.mockito.MockitoAnnotations.initMocks;

@Module(
        injects = {
                ProjectListActivityTest.class,
                ProjectListFragmentTest.class,
                ProjectListAdapterTest.class,
                ProjectsRequestGeneratorTest.class,
        },
        library = true,
        overrides = true,
        complete = false
)
public class TestProjectsModule {
    @Mock
    ProjectsRequestGenerator mockProjectsRequestGenerator;

    @Mock
    ProjectListAdapter mockProjectListAdapter;

    @Mock
    LinearLayoutManager mockLinearLayoutManager;

    @Mock
    AppBroadcastReceiver mockBroadcastReceiver;

    @Mock
    OnResultListPresenter mockOnResultListPresenter;

    public TestProjectsModule() {
        initMocks(this);
    }

    @Provides
    AppBroadcastReceiver appBroadcastReceiver() {
        return mockBroadcastReceiver;
    }

    @Provides
    ProjectsRequestGenerator mockProjectsRequestGenerator() {
        return mockProjectsRequestGenerator;
    }

    @Provides
    ProjectListAdapter mockListAdapter() {
        return mockProjectListAdapter;
    }

    @Provides
    LinearLayoutManager mockLayoutManager() {
        return mockLinearLayoutManager;
    }

    @Provides
    OnResultListPresenter mockOnResultListPresenter() {
        return mockOnResultListPresenter;
    }
}
