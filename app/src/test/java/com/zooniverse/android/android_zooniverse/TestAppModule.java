package com.zooniverse.android.android_zooniverse;

import dagger.Module;

@Module(
        injects = {
                //whitelisted classes
        },
        library = true,
        overrides = true,
        complete = false
)
public class TestAppModule {
    public TestAppModule() {}
}
