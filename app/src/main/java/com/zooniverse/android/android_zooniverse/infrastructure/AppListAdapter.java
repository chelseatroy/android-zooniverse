package com.zooniverse.android.android_zooniverse.infrastructure;

import java.util.ArrayList;

public interface AppListAdapter {
    void setListItems(ArrayList listItems);

    void wrapNotifyDataSetChanged();
}
