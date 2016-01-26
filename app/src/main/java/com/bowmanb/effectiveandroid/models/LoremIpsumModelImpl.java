package com.bowmanb.effectiveandroid.models;

import com.bowmanb.effectiveandroid.services.LoremIpsumService;
import com.bowmanb.effectiveandroid.services.StringConverterFactory;

import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoremIpsumModelImpl implements LoremIpsumModel {

    private final LoremIpsumService mService;

    public LoremIpsumModelImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://loripsum.net")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(StringConverterFactory.create())
                .build();
        mService = retrofit.create(LoremIpsumService.class);
    }

    LoremIpsumModelImpl(LoremIpsumService service) {
        mService = service;
    }

    @Override
    public void getLoremIpsum(Observer<String> observer) {
        mService.loremIpsum()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
