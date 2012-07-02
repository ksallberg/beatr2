package se.purestyle.beatr.view.instrumentmixer;

import se.purestyle.beatr.view.instrumentmixer.volumeobject.IInstrumentView;
import se.purestyle.beatr.view.instrumentmixer.volumeobject.InstrumentView;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

public class InstrumentMixerView extends LinearLayout implements IInstrumentMixer {

	public InstrumentMixerView( Context context ) {
	
		super( context );
		
		setOrientation( VERTICAL );
		
		setBackgroundColor( Color.RED );
		
		init( context );
	}
	
	private void init( Context context ) {
		
		for( int i = 0; i < 5; i ++ ) {
			
			addInstrumentView( new InstrumentView( context ) );
		}
	}

	@Override
	public void addInstrumentView(IInstrumentView view) {
		
		addView( (View) view );
	}

	@Override
	public void removeInstrumentView(IInstrumentView view) {
		
		
	}
}