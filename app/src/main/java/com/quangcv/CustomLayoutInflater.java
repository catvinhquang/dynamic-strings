package com.quangcv;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by QuangCV on 22-Jul-2019
 **/

// Based on com.android.internal.policy.PhoneLayoutInflater
public class CustomLayoutInflater extends LayoutInflater {

    private static final String[] sClassPrefixList = {
            "android.widget.",
            "android.webkit.",
            "android.app."
    };

    public CustomLayoutInflater(Context context) {
        super(context);
    }

    public CustomLayoutInflater(LayoutInflater original, Context newContext) {
        super(original, newContext);
    }

    @Override
    protected View onCreateView(String name, AttributeSet attrs) throws ClassNotFoundException {
        for (String prefix : sClassPrefixList) {
            try {
                View view = createView(name, prefix, attrs);
                if (view != null) {
                    // Replace new string
                    if (view instanceof TextView) {
                        TextView v = (TextView) view;
                        for (int i = 0; i < attrs.getAttributeCount(); i++) {
                            String attName = attrs.getAttributeName(i);
                            if (attName.equalsIgnoreCase("text")) {
                                String attValue = attrs.getAttributeValue(i);
                                if (attValue.startsWith("@")) {
                                    int id = Integer.parseInt(attValue.replace("@", ""));
                                    CharSequence newText = getContext().getText(id);
                                    v.setText(newText);
                                }
                            }
                        }
                    }

                    return view;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return super.onCreateView(name, attrs);
    }

    public LayoutInflater cloneInContext(Context newContext) {
        return new CustomLayoutInflater(this, newContext);
    }

}