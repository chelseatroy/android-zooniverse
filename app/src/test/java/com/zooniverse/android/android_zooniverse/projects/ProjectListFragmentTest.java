package com.zooniverse.android.android_zooniverse.projects;

import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zooniverse.android.android_zooniverse.BuildConfig;
import com.zooniverse.android.android_zooniverse.TestAppModule;
import com.zooniverse.android.android_zooniverse.infrastructure.AppBroadcastReceiver;
import com.zooniverse.android.android_zooniverse.infrastructure.GraphProvider;
import com.zooniverse.android.android_zooniverse.infrastructure.OnResultListPresenter;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.ShadowLocalBroadcastManager;
import org.robolectric.shadows.support.v4.Shadows;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import java.util.ArrayList;

import javax.inject.Inject;

import static org.assertj.android.recyclerview.v7.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ProjectListFragmentTest {
    @Inject
    AppBroadcastReceiver mockAppBroadcastReceiver;

    @Inject
    LinearLayoutManager mockLayoutManager;

    @Inject
    ProjectListAdapter mockListAdapter;

    @Inject
    OnResultListPresenter mockOnResultListPresenter;

    private ProjectListFragment subject;

    @Before
    public void setUp() {
        GraphProvider graphProvider = GraphProvider.getInstance();
        graphProvider.setupGraph(new TestAppModule());
        graphProvider.getGraph().inject(this);

        reset(mockListAdapter);

        subject = new ProjectListFragment();
        SupportFragmentTestUtil.startFragment(subject);
    }

    @Test
    public void onViewCreated_setsListAdapter() {
        RecyclerView listView = (RecyclerView) subject.getView().findViewById(android.R.id.list);
        assertThat(listView).hasLayoutManager(mockLayoutManager);
        verify(mockListAdapter).setListItems(any(ArrayList.class));
        assertThat(listView).hasAdapter(mockListAdapter);
    }

    @Test
    public void onResume_registersReceiver() {
        verify(mockAppBroadcastReceiver).register(subject);

        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(RuntimeEnvironment.application);
        ShadowLocalBroadcastManager shadowLocalBroadcastManager = Shadows.shadowOf(localBroadcastManager);
        Assertions.assertThat(shadowLocalBroadcastManager.getRegisteredBroadcastReceivers().size()).isEqualTo(1);
        Assertions.assertThat(shadowLocalBroadcastManager.getRegisteredBroadcastReceivers().get(0).intentFilter.getAction(0)).isEqualTo(ProjectListActivity.GET_PROJECTS_LIST);
        Assertions.assertThat(shadowLocalBroadcastManager.getRegisteredBroadcastReceivers().get(0).broadcastReceiver).isEqualTo(mockAppBroadcastReceiver);
    }

    @Test
    public void onSuccess_showsListOfProjects() {
        ArrayList<ProjectsResponse.Project> listOfProjects = new ArrayList<>();
        subject.onSuccess("action", listOfProjects);

        verify(mockOnResultListPresenter).presentOnSuccessList(
                mockListAdapter,
                listOfProjects,
                subject.getView()
        );
    }

    @Test
    public void onFailure_presentsFailureView() {
        subject.onFailure("action", new Object());

        verify(mockOnResultListPresenter).presentOnFailureList(subject.getView());
    }



}