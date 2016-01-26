package com.bowmanb.effectiveandroid.activities;

import com.bowmanb.effectiveandroid.BuildConfig;
import com.bowmanb.effectiveandroid.presenters.LoremIpsumPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class LoremIpsumActivityTest {

    @Mock LoremIpsumPresenter mPresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOnCreate() throws Exception {
        LoremIpsumActivity activity = new LoremIpsumActivity() {
            @Override
            public LoremIpsumPresenter createPresenter() {
                return mPresenter;
            }
        };
        ActivityController<LoremIpsumActivity> ac = ActivityController.of(Robolectric.getShadowsAdapter(), activity);
        ac.create();
        verify(mPresenter).create();
    }

}
