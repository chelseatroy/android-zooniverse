import com.zooniverse.android.android_zooniverse.TestAppModule;
import com.zooniverse.android.android_zooniverse.infrastructure.AppModule;

import java.util.ArrayList;
import java.util.List;

public class TestApplication extends ZooniverseApplication {
    @Override
    protected List<Object> getModules() {

        List<Object> objects = new ArrayList<>();

        objects.add(new AppModule(this));
        objects.add(new TestAppModule());

        return objects;
    }
}
