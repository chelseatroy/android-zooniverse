import android.app.Application;

import com.zooniverse.android.android_zooniverse.infrastructure.AppModule;
import com.zooniverse.android.android_zooniverse.infrastructure.GraphProvider;

import java.util.ArrayList;
import java.util.List;

import dagger.ObjectGraph;

public class ZooniverseApplication extends Application {
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        GraphProvider graphProvider = GraphProvider.getInstance();
        graphProvider.setupGraph(getModules().toArray());
        objectGraph = graphProvider.getGraph();
    }

    protected List<Object> getModules() {
        ArrayList<Object> objects = new ArrayList<>();

        objects.add(new AppModule(this));

        return objects;
    }

    public void inject(Object object) {
        objectGraph.inject(object);
    }
}

