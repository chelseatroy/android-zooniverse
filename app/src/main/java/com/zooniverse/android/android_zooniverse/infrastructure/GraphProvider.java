package com.zooniverse.android.android_zooniverse.infrastructure;

import dagger.ObjectGraph;

public class GraphProvider {
    private static GraphProvider instance;
    private ObjectGraph graph;

    private GraphProvider() {
        graph = ObjectGraph.create();
    }

    public static GraphProvider getInstance() {
        if (instance == null) {
            instance = new GraphProvider();
        }
        return instance;
    }

    public void setupGraph(Object... modules) {
        graph = graph.plus(modules);
    }

    public ObjectGraph getGraph() {
        return graph;
    }
}
