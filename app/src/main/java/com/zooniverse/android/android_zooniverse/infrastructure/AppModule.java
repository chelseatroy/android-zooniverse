package com.zooniverse.android.android_zooniverse.infrastructure;

import android.content.Context;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.zooniverse.android.android_zooniverse.R;
import com.zooniverse.android.android_zooniverse.projects.ProjectsModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.converter.Converter;
import retrofit.converter.JacksonConverter;

@Module(
        includes = {
                ProjectsModule.class,
        },
        injects = {
                //whitelisted classes
        },
        complete = false,
        library = true
)
public class AppModule {
    private final Context context;

    public AppModule(final Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    RestAdapter.Builder provideRestAdapterBuilder(Converter converter, String url) {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setConverter(converter);
        builder.setEndpoint(url);
        builder.setLogLevel(RestAdapter.LogLevel.FULL);
        return builder;
    }

    @Provides
    Converter providesConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        return new JacksonConverter(objectMapper);
    }

    @Provides
    String providesBaseUrl() {
        return "localhost:8080";
    }
}
