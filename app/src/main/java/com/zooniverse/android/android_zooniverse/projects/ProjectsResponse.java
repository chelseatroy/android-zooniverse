package com.zooniverse.android.android_zooniverse.projects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ProjectsResponse {
    @JsonProperty(value = "projects")
    private List<Project> projects;

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    private class Project {
        @JsonProperty(value = "display_name")
        private String displayName;
        @JsonProperty(value = "description")
        private String description;
        @JsonProperty(value = "classifications_count")
        private int classificationsCount;

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getClassificationsCount() {
            return classificationsCount;
        }

        public void setClassificationsCount(int classificationsCount) {
            this.classificationsCount = classificationsCount;
        }
    }
}
