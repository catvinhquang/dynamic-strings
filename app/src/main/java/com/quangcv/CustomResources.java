package com.quangcv;

import android.content.res.Resources;
import android.os.Build;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by QuangCV on 22-Jul-2019
 **/

public class CustomResources extends Resources {

    private static final Spanned EMPTY_TEXT = new SpannableString("");
    private static final Map<String, String> STRINGS = new HashMap<>();

    public CustomResources(Resources res) {
        super(res.getAssets(), res.getDisplayMetrics(), res.getConfiguration());

        // TODO initialize new strings
        STRINGS.put("demo", "<b>Beta</b>");
    }

    @Override
    public CharSequence getText(int id) throws NotFoundException {
        String key = getResourceEntryName(id);
        CharSequence value = STRINGS.get(key);
        if (TextUtils.isEmpty(value)) {
            value = super.getText(id);
        } else {
            value = fromHtml(value);
        }
        return value;
    }

    private Spanned fromHtml(CharSequence content) {
        if (TextUtils.isEmpty(content)) {
            return EMPTY_TEXT;
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return Html.fromHtml(content.toString(), Html.FROM_HTML_MODE_LEGACY);
            } else {
                return Html.fromHtml(content.toString());
            }
        }
    }

}