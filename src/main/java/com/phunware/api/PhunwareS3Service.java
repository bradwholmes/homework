package com.phunware.api;

import com.phunware.domain.Venue;
import retrofit.http.GET;
import rx.Observable;

import java.util.List;

public interface PhunwareS3Service {
    @GET("/jon-hancock-phunware/nflapi-static.json") Observable<List<Venue>> getVenues();
}
