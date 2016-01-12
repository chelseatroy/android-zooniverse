package com.zooniverse.android.android_zooniverse.projects;

import android.os.Parcel;

import com.zooniverse.android.android_zooniverse.TestAppModule;
import com.zooniverse.android.android_zooniverse.ZooniverseApplication;
import com.zooniverse.android.android_zooniverse.infrastructure.AppModule;
import com.zooniverse.android.android_zooniverse.infrastructure.GraphProvider;
import com.zooniverse.android.android_zooniverse.infrastructure.ServiceResult;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit.RestAdapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

public class ProjectsRequestGeneratorTest {
    @Inject
    RestAdapter mockRestAdapter;

    ProjectsRequestGenerator subject;
    private ProjectsRetrofitInterface mockInterface;

    @Before
    public void setUp() {
        GraphProvider graphProvider = GraphProvider.getInstance();
        graphProvider.setupGraph(new AppModule(mock(ZooniverseApplication.class)), new TestAppModule());
        graphProvider.getGraph().inject(this);

        Parcel mockParcel = mock(Parcel.class);
        subject = new ProjectsRequestGenerator(mockParcel);
        mockInterface = mock(ProjectsRetrofitInterface.class);

        stub(mockRestAdapter.create(ProjectsRetrofitInterface.class)).toReturn(mockInterface);
    }

    @Test
    public void makeCall_successReponse_returnsSuccessfulApiResult() throws Exception {
        ProjectsResponse response = new ProjectsResponse();
        ArrayList<ProjectsResponse.Project> projects = new ArrayList<>();
        response.setProjects(projects);
        stub(mockInterface.getProjects()).toReturn(response);

        ServiceResult actualServiceResult = subject.makeCall();

        assertThat(actualServiceResult.isSuccessful()).isEqualTo(true);
        assertThat(actualServiceResult.getPayload()).isEqualTo(projects);
    }

    @Test
    public void makeCall_failureReponse_returnsFailedApiResult() throws Exception {
        stub(mockInterface.getProjects()).toThrow(new Exception());

        ServiceResult actualServiceResult = subject.makeCall();

        assertThat(actualServiceResult.isSuccessful()).isEqualTo(false);
        assertThat(actualServiceResult.getPayload()).isNull();
    }
}