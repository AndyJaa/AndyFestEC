package com.andy.jaa.festec.activities;

import com.andy.jaa.andyfec.activities.ProxyActivity;
import com.andy.jaa.andyfec.delegates.LatteDelegate;
import com.andy.jaa.festec.fragment_delegate.AndyDelegate;

public class AndyActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDelegate() {
        return new AndyDelegate();
    }
}
