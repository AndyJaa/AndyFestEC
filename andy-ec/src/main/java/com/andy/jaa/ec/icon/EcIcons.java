package com.andy.jaa.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by quanxi on 2018/3/9.
 */

public enum EcIcons implements Icon {
    icon_re_test('\ue629'),
    icon_test('\ue61f'),
    icon_scan('\ue602'),
    icon_ali_pay('\ue606');

    private char aChar;

    EcIcons(char aChar) {
        this.aChar = aChar;
    }

    @Override
    public String key() {
        return name().replace('_','-');
    }

    @Override
    public char character() {
        return aChar;
    }
}
