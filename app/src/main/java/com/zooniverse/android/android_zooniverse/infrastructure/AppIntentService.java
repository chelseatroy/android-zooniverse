package com.zooniverse.android.android_zooniverse.infrastructure;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

public class AppIntentService extends IntentService{
    public static final String CALL_GENERATOR = "apiintentservice.CALL_GENERATOR";
    public static final String RESULT = "apiintentservice.RESULT";

    private CallGenerator callGenerator;

    public AppIntentService() {
        super("AppIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String actionName = intent.getAction();
        callGenerator = intent.getParcelableExtra(CALL_GENERATOR);

        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);

        ServiceResult result = callGenerator.makeCall();

        Intent resultIntent = new Intent();
        resultIntent.setAction(actionName);
        resultIntent.putExtra(RESULT, result);

        localBroadcastManager.sendBroadcast(resultIntent);
    }
}
