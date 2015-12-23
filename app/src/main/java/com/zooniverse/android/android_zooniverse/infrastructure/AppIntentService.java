package com.zooniverse.android.android_zooniverse.infrastructure;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

public class AppIntentService extends IntentService{
    public static final String CALL_GENERATOR = "apiintentservice.CALL_GENERATOR";

    private CallGenerator callGenerator;

    public AppIntentService() {
        super("AppIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String actionName = intent.getAction();
        callGenerator = intent.getParcelableExtra(CALL_GENERATOR);

        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);

        Object result = callGenerator.makeCall();

        Intent resultIntent = new Intent();
        resultIntent.setAction(actionName);
        //add the success or failure category?
        //hopefully we can get result in a tuple
//        resultIntent.putExtra(AppBroadcastReceiver.PAYLOAD, result);

        localBroadcastManager.sendBroadcast(resultIntent);



    }
}
