package com.bowmanb.effectiveandroid.services;

import retrofit2.http.GET;
import rx.Observable;

public interface LoremIpsumService {

    @GET("/api/5/plaintext")
    Observable<String> loremIpsum();

}
