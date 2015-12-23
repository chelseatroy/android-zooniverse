package com.zooniverse.android.android_zooniverse.infrastructure;

public interface BroadcastResponder {
    void onSuccess(String action, Object info);

    void onFailure(String action, Object info);
}
