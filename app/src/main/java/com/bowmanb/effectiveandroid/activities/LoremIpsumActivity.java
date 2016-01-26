package com.bowmanb.effectiveandroid.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.bowmanb.effectiveandroid.R;
import com.bowmanb.effectiveandroid.presenters.LoremIpsumPresenter;
import com.bowmanb.effectiveandroid.presenters.LoremIpsumPresenterImpl;
import com.bowmanb.effectiveandroid.views.LoremIpsumView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoremIpsumActivity extends AppCompatActivity implements LoremIpsumView {

    @Bind(R.id.lorem_ipsum) TextView mLoremIpsum;

    private LoremIpsumPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lorem_ipsum);
        ButterKnife.bind(this);
        mPresenter = createPresenter();
        mPresenter.create();
    }

    public LoremIpsumPresenter createPresenter() {
        return new LoremIpsumPresenterImpl(this);
    }

    @Override
    public void showError() {
        Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setLoremIpsum(String loremIpsum) {
        mLoremIpsum.setText(loremIpsum);
    }

}
