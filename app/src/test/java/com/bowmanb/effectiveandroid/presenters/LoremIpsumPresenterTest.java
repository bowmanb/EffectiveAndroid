package com.bowmanb.effectiveandroid.presenters;

import com.bowmanb.effectiveandroid.BuildConfig;
import com.bowmanb.effectiveandroid.models.LoremIpsumModel;
import com.bowmanb.effectiveandroid.views.LoremIpsumView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import rx.Observer;

import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 19)
public class LoremIpsumPresenterTest {

    private static final String LOREM_IPSUM = "Lorem ipsum";

    @Mock private LoremIpsumModel mModel;
    @Mock private LoremIpsumView mView;

    private ArgumentCaptor<Observer> mObserverCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mObserverCaptor = ArgumentCaptor.forClass(Observer.class);
        LoremIpsumPresenter presenter = new LoremIpsumPresenterImpl(mView, mModel);
        presenter.create();
    }

    @Test
    public void testCreate() throws Exception {
        verify(mModel).getLoremIpsum(mObserverCaptor.capture());
        Observer<String> observer = mObserverCaptor.getValue();
        observer.onNext(LOREM_IPSUM);
        observer.onCompleted();
        verify(mView).setLoremIpsum(LOREM_IPSUM);
    }

    @Test
    public void testCreateError() throws Exception {
        verify(mModel).getLoremIpsum(mObserverCaptor.capture());
        Observer<String> observer = mObserverCaptor.getValue();
        observer.onError(new Exception());
        verify(mView).showError();
    }

}
