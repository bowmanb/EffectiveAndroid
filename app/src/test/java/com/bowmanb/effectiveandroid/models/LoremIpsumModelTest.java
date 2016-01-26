package com.bowmanb.effectiveandroid.models;

import com.bowmanb.effectiveandroid.BuildConfig;
import com.bowmanb.effectiveandroid.services.LoremIpsumService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class LoremIpsumModelTest {

    private static final String LOREM_IPSUM = "Lorem ipsum";

    @Mock private LoremIpsumService mService;
    @Mock private Observer<String> mObserver;

    @Captor private ArgumentCaptor<Observable<String>> mArgumentCaptor;

    private LoremIpsumModel mModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mModel = new LoremIpsumModelImpl(mService) {
            @Override
            public void getLoremIpsum(Observer<String> observer) {
                mService.loremIpsum()
                        .subscribe(observer);
            }
        };

    }

    @Test
    public void testLoremIpsum() throws Exception {
        when(mService.loremIpsum()).thenReturn(mockLoremIpsumObservable());
        mModel.getLoremIpsum(mObserver);
        verify(mService).loremIpsum();
        verify(mObserver).onNext(eq(LOREM_IPSUM));
        verify(mObserver).onCompleted();
    }

    private Observable<String> mockLoremIpsumObservable() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(LOREM_IPSUM);
                subscriber.onCompleted();
            }
        });
    }

    @Test
    public void testLoremIpsumFailure() throws Exception {
        when(mService.loremIpsum()).thenReturn(mockLoremIpsumObservableFailure());
        mModel.getLoremIpsum(mObserver);
        verify(mObserver).onError(any(Exception.class));
    }

    private Observable<String> mockLoremIpsumObservableFailure() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onError(new Exception());
                subscriber.onCompleted();
                subscriber.unsubscribe();
            }
        });
    }

}