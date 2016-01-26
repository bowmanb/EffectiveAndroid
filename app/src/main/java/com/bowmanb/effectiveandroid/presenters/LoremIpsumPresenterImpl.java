package com.bowmanb.effectiveandroid.presenters;

import android.util.Log;

import com.bowmanb.effectiveandroid.models.LoremIpsumModel;
import com.bowmanb.effectiveandroid.models.LoremIpsumModelImpl;
import com.bowmanb.effectiveandroid.views.LoremIpsumView;

import rx.Observer;

public class LoremIpsumPresenterImpl implements LoremIpsumPresenter {

    private static final String TAG = LoremIpsumPresenterImpl.class.getSimpleName();

    private final LoremIpsumView mView;
    private final LoremIpsumModel mModel;

    public LoremIpsumPresenterImpl(LoremIpsumView view) {
        mView = view;
        mModel = new LoremIpsumModelImpl();
    }

    LoremIpsumPresenterImpl(LoremIpsumView view, LoremIpsumModel model) {
        mView = view;
        mModel = model;
    }

    @Override
    public void create() {
        mModel.getLoremIpsum(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, e.getMessage(), e);
                mView.showError();
            }

            @Override
            public void onNext(String loremIpsum) {
                mView.setLoremIpsum(loremIpsum);
            }
        });
    }

}
