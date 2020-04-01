package com.jayphone.practice.java.pattern.mediator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 具体中介者，联合国安理会
 * Created by JayPhone on 2020/3/31
 */
public class UnitedNationsSecurityCouncil extends UnitedNations {
    private Country mChina;
    private Country mUSA;

    public void setChina(Country china) {
        mChina = china;
    }

    public void setUSA(Country USA) {
        mUSA = USA;
    }

    @Override
    public void declare(String message, Country country) {
        if (country instanceof China) {
            mUSA.receiverMessage(message);
        } else if (country instanceof USA) {
            mChina.receiverMessage(message);
        }
    }
}
