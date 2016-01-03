package com.zooniverse.android.android_zooniverse.infrastructure;

import android.app.Activity;
import android.content.Intent;

import com.zooniverse.android.android_zooniverse.projects.ProjectListFragment;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class AppBroadcastReceiverTest {
    private AppBroadcastReceiver subject;
    private Intent intent;
    private ProjectListFragment responder;

    @Before
    public void setUp() throws Exception {
        subject = new AppBroadcastReceiver();

        intent = mock(Intent.class);
    }

    @Test
    public void register_unRegister_setsResponder() {
        responder = new ProjectListFragment();
        subject.register(responder);

        assertThat(subject.getResponder()).isEqualTo(responder);

        subject.unregister();
        assertThat(subject.getResponder()).isNull();
    }

    @Test
    public void onRecieve_successResponse() {
        ServiceResult result = new ServiceResult();
        Object payload = new Object();
        result.setSuccessful(true);
        result.setPayload(payload);

        stub(intent.getAction()).toReturn("some-action");
        stub(intent.getParcelableExtra(AppIntentService.RESULT)).toReturn(result);

        BroadcastResponder mockResponder = mock(BroadcastResponder.class);
        subject.register(mockResponder);
        subject.onReceive(new Activity(), intent);

        verify(mockResponder).onSuccess("some-action", payload);
    }

    @Test
    public void onReceive_FailureResponse() {
        ServiceResult result = new ServiceResult();
        Object payload = new Object();
        result.setSuccessful(false);
        result.setPayload(payload);

        stub(intent.getAction()).toReturn("some-action");
        stub(intent.getParcelableExtra(AppIntentService.RESULT)).toReturn(result);

        BroadcastResponder mockResponder = mock(BroadcastResponder.class);
        subject.register(mockResponder);
        subject.onReceive(new Activity(), intent);

        verify(mockResponder).onFailure("some-action", payload);
    }

}