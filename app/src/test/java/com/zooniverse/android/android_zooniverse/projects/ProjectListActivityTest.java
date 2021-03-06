package com.zooniverse.android.android_zooniverse.projects;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.zooniverse.android.android_zooniverse.BuildConfig;
import com.zooniverse.android.android_zooniverse.R;
import com.zooniverse.android.android_zooniverse.TestAppModule;
import com.zooniverse.android.android_zooniverse.ZooniverseApplication;
import com.zooniverse.android.android_zooniverse.infrastructure.AppIntentService;
import com.zooniverse.android.android_zooniverse.infrastructure.AppModule;
import com.zooniverse.android.android_zooniverse.infrastructure.GraphProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.util.ActivityController;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ProjectListActivityTest {
    @Inject
    ProjectsRequestGenerator mockProjectsRequestGenerator;

    private ActivityController<ProjectListActivity> controller;
    private ProjectListActivity subject;

    @Before
    public void setUp() throws Exception {
        GraphProvider graphProvider = GraphProvider.getInstance();
        graphProvider.setupGraph(new AppModule(mock(ZooniverseApplication.class)), new TestAppModule());
        graphProvider.getGraph().inject(this);

        controller = Robolectric.buildActivity(ProjectListActivity.class);
        subject = controller.get();
    }

    @Test
    public void onCreate_inflatesFragment_onResume_startsService() {
        controller.create();

        Fragment fragment = subject.getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        assertThat(fragment).isInstanceOf(ProjectListFragment.class);

        controller.resume();

        assertThat(mockProjectsRequestGenerator).isNotNull();

        ShadowActivity shadowActivity = Shadows.shadowOf(subject);
        Intent nextStartedService = shadowActivity.getNextStartedService();
        assertThat(nextStartedService.getAction()).isEqualTo(ProjectListActivity.GET_PROJECTS_LIST);
        assertThat(nextStartedService.getParcelableExtra(AppIntentService.CALL_GENERATOR)).isInstanceOf(ProjectsRequestGenerator.class);
    }
}