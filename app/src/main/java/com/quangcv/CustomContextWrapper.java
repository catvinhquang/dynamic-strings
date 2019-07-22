package com.quangcv;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.view.LayoutInflater;

/**
 * Created by QuangCV on 22-Jul-2019
 **/

public class CustomContextWrapper extends ContextWrapper {

    private CustomResources resources;
    private CustomLayoutInflater inflater;

    public CustomContextWrapper(Context base) {
        super(base);
        resources = new CustomResources(base.getResources());
    }

    @Override
    public Resources getResources() {
        return resources;
    }

    @Override
    public Object getSystemService(String name) {
        if (Context.LAYOUT_INFLATER_SERVICE.equals(name)) {
            if (inflater == null) {
                inflater = new CustomLayoutInflater(LayoutInflater.from(getBaseContext()), this);
            }
            return inflater;
        } else {
            return super.getSystemService(name);
        }
    }

}