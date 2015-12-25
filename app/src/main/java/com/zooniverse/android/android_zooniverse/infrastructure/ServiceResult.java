package com.zooniverse.android.android_zooniverse.infrastructure;

import android.os.Parcel;
import android.os.Parcelable;

public class ServiceResult implements Parcelable{
    private boolean successful;
    private Object payload;

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    //Parcelable Implementation

    protected ServiceResult(Parcel in) {
        successful = in.readByte() != 0;
    }

    public static final Creator<ServiceResult> CREATOR = new Creator<ServiceResult>() {
        @Override
        public ServiceResult createFromParcel(Parcel in) {
            return new ServiceResult(in);
        }

        @Override
        public ServiceResult[] newArray(int size) {
            return new ServiceResult[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (successful ? 1 : 0));
    }
}
