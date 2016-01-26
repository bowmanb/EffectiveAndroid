package com.bowmanb.effectiveandroid.models;

import rx.Observer;

public interface LoremIpsumModel {

    void getLoremIpsum(Observer<String> observer);

}
