package com.zooniverse.android.android_zooniverse.infrastructure;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.zooniverse.android.android_zooniverse.BuildConfig;
import com.zooniverse.android.android_zooniverse.projects.ProjectsRequestGenerator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowIntent;
import org.robolectric.shadows.support.v4.ShadowLocalBroadcastManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class AppIntentServiceTest {

    private AppIntentService subject;
    private Intent mockIntent;

    @Before
    public void setUp() throws Exception {
        subject = new AppIntentService();

        mockIntent = mock(Intent.class);
    }

    @Test
    public void onHandleIntent_sendsBroadcast() {
        ProjectsRequestGenerator mockCallGenerator = mock(ProjectsRequestGenerator.class);
        ServiceResult result = new ServiceResult();

        stub(mockIntent.getAction()).toReturn("action");
        stub(mockIntent.getParcelableExtra(AppIntentService.CALL_GENERATOR)).toReturn(mockCallGenerator);
        stub(mockCallGenerator.makeCall()).toReturn(result);

        subject.onHandleIntent(mockIntent);

        verify(mockCallGenerator).makeCall();

        ShadowLocalBroadcastManager shadowLocalBroadcastManager = org.robolectric.shadows.support.v4.Shadows.shadowOf(LocalBroadcastManager.getInstance(RuntimeEnvironment.application));
        List<Intent> sentBroadcastIntents = shadowLocalBroadcastManager.getSentBroadcastIntents();
        assertThat(sentBroadcastIntents.size()).isEqualTo(1);
        ShadowIntent shadowIntent = org.robolectric.Shadows.shadowOf(sentBroadcastIntents.get(0));
        assertThat(shadowIntent.getAction()).isEqualTo("action");
        assertThat(shadowIntent.getParcelableExtra(AppIntentService.RESULT)).isEqualTo(result);
    }
}