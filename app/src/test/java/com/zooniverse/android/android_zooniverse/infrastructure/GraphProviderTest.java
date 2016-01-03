package com.zooniverse.android.android_zooniverse.infrastructure;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GraphProviderTest {
    @Test
    public void getInstance_returnsSameInstance() {
        GraphProvider instance = GraphProvider.getInstance();
        GraphProvider secondInstance = GraphProvider.getInstance();

        assertThat(instance).isSameAs(secondInstance);
    }

}