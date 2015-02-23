package com.phunware;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.phunware.activity.Details;
import com.phunware.activity.HomeScreen;
import com.phunware.api.PhunwareS3Service;
import com.phunware.fragment.VenueList;
import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

@Module(injects = {HomeScreen.class, VenueList.class, Details.class},
        library = true)
public class ApplicationModule {
    @Provides Gson provideGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss Z")
                .create();
    }

    @Provides RestAdapter provideRestAdapter(Gson gson) {
        return new RestAdapter.Builder()
                .setEndpoint("https://s3.amazonaws.com")
                .setConverter(new GsonConverter(gson))
                .build();
    }

    @Provides PhunwareS3Service providePhunwareS3Service(RestAdapter restAdapter) {
        return restAdapter.create(PhunwareS3Service.class);
    }
}
