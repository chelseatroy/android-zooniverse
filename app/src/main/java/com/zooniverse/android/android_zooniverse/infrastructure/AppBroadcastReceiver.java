package com.zooniverse.android.android_zooniverse.infrastructure;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AppBroadcastReceiver extends BroadcastReceiver {
    private BroadcastResponder responder;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        ServiceResult result = intent.getParcelableExtra(AppIntentService.RESULT);
        boolean callSuccessful = result.isSuccessful();
        Object payload = result.getPayload();

        if (!callSuccessful) {
            responder.onFailure(action, payload);
            return;
        }
        responder.onSuccess(action, payload);
    }

    public void register(BroadcastResponder responder) {
        this.responder = responder;
    }

    public void unregister() {
        this.responder = null;
    }

    public BroadcastResponder getResponder() {
        return responder;
    }
}
