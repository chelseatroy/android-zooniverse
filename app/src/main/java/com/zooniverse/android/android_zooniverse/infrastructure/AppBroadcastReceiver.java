package com.zooniverse.android.android_zooniverse.infrastructure;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;

import java.util.Set;

public class AppBroadcastReceiver extends BroadcastReceiver{
    public static final String PAYLOAD = "com.zooniverse.android-zooniverse.appbroadcastreceiver.PAYLOAD";

    private BroadcastResponder responder;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Set<String> categories = intent.getCategories();
        Parcelable payload = intent.getParcelableExtra(PAYLOAD);

        if (categories.contains("FAILURE")) {
            responder.onFailure(action, payload);
            return;
        }
        responder.onSuccess(action, payload);
    }

    public void register(BroadcastResponder responder) {
        this.responder = responder;
    }
}
