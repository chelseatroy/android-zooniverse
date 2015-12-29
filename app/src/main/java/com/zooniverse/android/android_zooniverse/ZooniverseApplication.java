package com.zooniverse.android.android_zooniverse;

import android.app.Application;

import com.zooniverse.android.android_zooniverse.infrastructure.AppModule;
import com.zooniverse.android.android_zooniverse.infrastructure.GraphProvider;

import java.util.ArrayList;
import java.util.List;

public class ZooniverseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        GraphProvider graphProvider = GraphProvider.getInstance();
        graphProvider.setupGraph(getModules().toArray());
    }

    protected List<Object> getModules() {
        ArrayList<Object> objects = new ArrayList<>();

        objects.add(new AppModule(this));

        return objects;
    }
}

