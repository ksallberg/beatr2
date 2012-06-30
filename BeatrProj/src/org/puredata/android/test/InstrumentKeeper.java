package org.puredata.android.test;

import android.content.Context;
import android.widget.LinearLayout;

public class InstrumentKeeper extends LinearLayout {

	public InstrumentKeeper( Context context ) {
	
		super( context );
		
		InstrumentMixerView i1 = new InstrumentMixerView(context);
		InstrumentMixerView i2 = new InstrumentMixerView(context);
		InstrumentMixerView i3 = new InstrumentMixerView(context);
		
		setOrientation(VERTICAL);
		
		addView( i1 );
		addView( i2 );
		addView( i3 );
		
		setBackgroundColor( 0xffffff );
	}
}