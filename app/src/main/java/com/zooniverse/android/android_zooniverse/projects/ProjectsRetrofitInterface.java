package com.zooniverse.android.android_zooniverse.projects;

import retrofit.http.GET;
import retrofit.http.Headers;

public interface ProjectsRetrofitInterface {
    @Headers("Accept: application/vnd.api+json; version=1")
    @GET("/projects")
    ProjectsResponse getProjects() throws Exception;
}

