package com.zooniverse.android.android_zooniverse.projects;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.zooniverse.android.android_zooniverse.BuildConfig;
import com.zooniverse.android.android_zooniverse.R;
import com.zooniverse.android.android_zooniverse.TestAppModule;
import com.zooniverse.android.android_zooniverse.ZooniverseApplication;
import com.zooniverse.android.android_zooniverse.infrastructure.AppModule;
import com.zooniverse.android.android_zooniverse.infrastructure.GraphProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ProjectListAdapterTest {
    private ProjectListAdapter subject;
    private ProjectsResponse.Project project;

    @Before
    public void setUp() {
        GraphProvider graphProvider = GraphProvider.getInstance();
        graphProvider.setupGraph(new AppModule(mock(ZooniverseApplication.class)), new TestAppModule());
        graphProvider.getGraph().inject(this);

        subject = new ProjectListAdapter();

        ArrayList listItems = new ArrayList();
        project = new ProjectsResponse.Project();
        project.setDisplayName("Identify Stars");
        project.setDescription("Help researchers classify the stars!");
        listItems.add(project);

        subject.setListItems(listItems);
    }

    @Test
    public void getItemCount_returnsListSize() {
        assertThat(subject.getItemCount()).isEqualTo(1);
    }

    @Test
    public void getItemAtPosition_returnsCorrectItem() {
        assertThat(subject.getItemAtPosition(0)).isEqualTo(project);
    }

    @Test
    public void onCreateViewHolder() {
        ProjectListAdapter subject = new ProjectListAdapter();
        FrameLayout parent = new FrameLayout(RuntimeEnvironment.application);

        RecyclerView.ViewHolder viewHolder = subject.onCreateViewHolder(parent, 0);

        assertThat(viewHolder).isInstanceOf(ProjectListAdapter.ProjectViewHolder.class);
        assertThat(((ProjectListAdapter.ProjectViewHolder) viewHolder).projectTitle).isNotNull();
    }

    @Test
    public void onBindViewHolder_setsListItemText() {

        LayoutInflater inflater = (LayoutInflater) RuntimeEnvironment.application.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View listItemView = inflater.inflate(R.layout.project_list_card_layout, null, false);

        ProjectListAdapter.ProjectViewHolder holder = new ProjectListAdapter.ProjectViewHolder(listItemView);
        subject.onBindViewHolder(holder, 0);
        assertThat(holder.projectTitle.getText()).isEqualTo("Identify Stars");
        assertThat(holder.projectDescription.getText()).isEqualTo("Help researchers classify the stars!");
    }
}