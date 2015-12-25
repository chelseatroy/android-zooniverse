package com.zooniverse.android.android_zooniverse.projects;

import retrofit.http.GET;

public interface ProjectsRetrofitInterface {
    @GET("/projects")
    ProjectsResponse getProjects();
}

