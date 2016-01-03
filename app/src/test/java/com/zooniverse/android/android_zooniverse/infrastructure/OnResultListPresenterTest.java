package com.zooniverse.android.android_zooniverse.infrastructure;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.zooniverse.android.android_zooniverse.R;
import com.zooniverse.android.android_zooniverse.projects.ProjectListAdapter;
import com.zooniverse.android.android_zooniverse.projects.ProjectsResponse;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class OnResultListPresenterTest {
    private OnResultListPresenter subject;
    private View mockView;
    private View mockProgressBar;
    private RecyclerView mockListView;
    private View mockErrorView;
    private View mockEmptyListView;

    private ProjectListAdapter mockAppListAdapter;

    @Before
    public void setUp() throws Exception {
        subject = new OnResultListPresenter();

        mockAppListAdapter = mock(ProjectListAdapter.class);

        mockView = mock(View.class);
        mockProgressBar = mock(View.class);
        mockListView = mock(RecyclerView.class);
        mockErrorView = mock(View.class);
        mockEmptyListView = mock(View.class);
        stub(mockView.findViewById(R.id.progress_bar)).toReturn(mockProgressBar);
        stub(mockView.findViewById(android.R.id.list)).toReturn(mockListView);
        stub(mockView.findViewById(R.id.error_view)).toReturn(mockErrorView);
        stub(mockView.findViewById(R.id.empty_list_view)).toReturn(mockEmptyListView);
    }

    @Test
    public void presentOnSuccessList_populatedList() {
        ArrayList resultList = new ArrayList();
        resultList.add(new ProjectsResponse.Project());

        subject.presentOnSuccessList(mockAppListAdapter, resultList, mockView);

        verify(mockListView).setAdapter(mockAppListAdapter);
        verify(mockAppListAdapter).setListItems(resultList);
        verify(mockAppListAdapter).wrapNotifyDataSetChanged();

        verify(mockListView).setVisibility(View.VISIBLE);
        verify(mockEmptyListView).setVisibility(View.GONE);
        verify(mockProgressBar).setVisibility(View.GONE);
        verify(mockErrorView).setVisibility(View.GONE);
    }

    @Test
    public void presentOnSuccessList_emptyList() {
        ArrayList resultList = new ArrayList();

        subject.presentOnSuccessList(mockAppListAdapter, resultList, mockView);

        verifyZeroInteractions(mockAppListAdapter);

        verify(mockListView).setVisibility(View.GONE);
        verify(mockEmptyListView).setVisibility(View.VISIBLE);
        verify(mockProgressBar).setVisibility(View.GONE);
        verify(mockErrorView).setVisibility(View.GONE);
    }

    @Test
    public void presentOnFaiureList() {
        subject.presentOnFailureList(mockView);

        verify(mockProgressBar).setVisibility(View.GONE);
        verify(mockListView).setVisibility(View.GONE);
        verify(mockErrorView).setVisibility(View.VISIBLE);
        verify(mockEmptyListView).setVisibility(View.GONE);
    }
}