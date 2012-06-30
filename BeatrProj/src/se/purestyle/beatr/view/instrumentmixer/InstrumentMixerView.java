package se.purestyle.beatr.view.instrumentmixer;

import se.purestyle.beatr.view.instrumentmixer.volumeobject.InstrumentView;
import android.content.Context;
import android.widget.LinearLayout;

public class InstrumentMixerView extends LinearLayout {

	public InstrumentMixerView( Context context ) {
	
		super( context );
		
		InstrumentView i1 = new InstrumentView(context);
		InstrumentView i2 = new InstrumentView(context);
		InstrumentView i3 = new InstrumentView(context);
		
		setOrientation(VERTICAL);
		
		addView( i1 );
		addView( i2 );
		addView( i3 );
		
		setBackgroundColor( 0xffffff );
	}
}