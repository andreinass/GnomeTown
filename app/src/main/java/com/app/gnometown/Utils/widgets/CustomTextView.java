package com.app.gnometown.Utils.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.TextView;

import com.app.gnometown.R;

/**
 * Created by andreinasarda on 18/4/16.
 *
 * CustomTextView to incorporate Avenir fonts to Project
 *
 */
public class CustomTextView extends TextView {

    private static final int AVENIR_REGULAR = 0;
    private static final int AVENIR_MEDIUM = 1;
    private static final int AVENIR_HEAVY = 2;

    private final static SparseArray<Typeface> mTypefaces = new SparseArray<>(3);


    public CustomTextView(Context context) {
        super(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(context, attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        parseAttributes(context, attrs);
    }

    /**
     * Parse the attributes.
     *
     * @param context The Context the view is running in, through which it can access the current theme, resources, etc.
     * @param attrs   The attributes of the XML tag that is inflating the view.
     */
    private void parseAttributes(Context context, AttributeSet attrs) {
        TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.CustomText);

        int typefaceValue = values.getInt(R.styleable.CustomText_typeface, 0);
        values.recycle();

        setTypeface(obtaintTypeface(context, typefaceValue));
    }

    /**
     * Obtain typeface.
     *
     * @param context       The Context the view is running in, through which it can
     *                      access the current theme, resources, etc.
     * @param typefaceValue values ​​for the "typeface" attribute
     * @return Roboto {@link Typeface}
     * @throws IllegalArgumentException if unknown `typeface` attribute value.
     */
    private Typeface obtaintTypeface(Context context, int typefaceValue) throws IllegalArgumentException {
        Typeface typeface = mTypefaces.get(typefaceValue);
        if (typeface == null) {
            typeface = createTypeface(context, typefaceValue);
            mTypefaces.put(typefaceValue, typeface);
        }
        return typeface;
    }


    /**
     * Create typeface from assets.
     *
     * @param context       The Context the view is running in, through which it can
     *                      access the current theme, resources, etc.
     * @param typefaceValue values ​​for the "typeface" attribute
     * @return Roboto {@link Typeface}
     * @throws IllegalArgumentException if unknown `typeface` attribute value.
     */

    private Typeface createTypeface(Context context, int typefaceValue) throws IllegalArgumentException {
        Typeface typeface;
        switch (typefaceValue) {

            case AVENIR_HEAVY:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/AvenirLTStd-Heavy.otf");
                break;

            case AVENIR_MEDIUM:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/AvenirLTStd-Medium.otf");

                break;
            case AVENIR_REGULAR:
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/AvenirLTStd-Roman.otf");
                break;

            default:
                throw new IllegalArgumentException("Unknown `typeface` attribute value " + typefaceValue);
        }
        return typeface;
    }


}
