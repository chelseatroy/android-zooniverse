package com.zooniverse.android.android_zooniverse.projects;

import android.os.Parcel;
import android.os.Parcelable;

import com.zooniverse.android.android_zooniverse.infrastructure.CallGenerator;
import com.zooniverse.android.android_zooniverse.infrastructure.GraphProvider;
import com.zooniverse.android.android_zooniverse.infrastructure.ServiceResult;

import javax.inject.Inject;

import retrofit.RestAdapter;

public class ProjectsRequestGenerator implements CallGenerator, Parcelable {
    @Inject
    RestAdapter restAdapter;

    @Override
    public ServiceResult makeCall() {
        GraphProvider.getInstance().getGraph().inject(this);

        ServiceResult apiResult = new ServiceResult();
        ProjectsRetrofitInterface projectsRetrofitInterface = restAdapter.create(ProjectsRetrofitInterface.class);

        try {
            ProjectsResponse response = projectsRetrofitInterface.getProjects();

            apiResult.setSuccessful(true);
            apiResult.setPayload(response.getProjects());
        } catch (Exception error) {
            apiResult.setSuccessful(false);
        }

        return apiResult;
    }

    //Parcelable Implementation

    protected ProjectsRequestGenerator(Parcel in) {
    }

    public static final Creator<ProjectsRequestGenerator> CREATOR = new Creator<ProjectsRequestGenerator>() {
        @Override
        public ProjectsRequestGenerator createFromParcel(Parcel in) {
            return new ProjectsRequestGenerator(in);
        }

        @Override
        public ProjectsRequestGenerator[] newArray(int size) {
            return new ProjectsRequestGenerator[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
