package com.zooniverse.android.android_zooniverse.projects;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zooniverse.android.android_zooniverse.R;
import com.zooniverse.android.android_zooniverse.infrastructure.AppListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ProjectViewHolder> implements AppListAdapter {
    private List<ProjectsResponse.Project> projectList;

    @Override
    public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_list_card_layout, parent, false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProjectViewHolder holder, int position) {
        final ProjectsResponse.Project project = getItemAtPosition(position);

        holder.projectTitle.setText(project.getDisplayName());
        holder.projectDescription.setText(project.getDescription());

    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    @Override
    public void setListItems(ArrayList listItems) {
        this.projectList = listItems;
    }

    public void wrapNotifyDataSetChanged() {
        notifyDataSetChanged();
    }

    public ProjectsResponse.Project getItemAtPosition(int position) {
        return projectList.get(position);
    }

    public static class ProjectViewHolder extends RecyclerView.ViewHolder {
        public View itemView;
        public TextView projectTitle;
        public TextView projectDescription;

        public ProjectViewHolder(View listItemView) {
            super(listItemView);
            itemView = listItemView;
            projectTitle = (TextView) listItemView.findViewById(R.id.project_list_title);
            projectDescription = (TextView) listItemView.findViewById(R.id.project_list_description);
        }
    }
}
