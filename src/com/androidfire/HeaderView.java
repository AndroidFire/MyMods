package com.androidfire;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * @author AndroidFire
 * 
 */
public class HeaderView extends LinearLayout  {
	int LayoutView;

	public HeaderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutView = HeaderView.this.getResources().getIdentifier("ajb_header",
				"layout", 
				"com.android.systemui");
		/*
		 * To get Layout This Process Does not Required smali editing
		 */
		inflate(getContext(), LayoutView, this);
		/*
		 * Inflating Layout to SystemUI Layout
		 */
	}

}
