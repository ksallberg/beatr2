package se.purestyle.beatr.view.instrumentmixer;

import se.purestyle.beatr.view.instrumentmixer.volumeobject.IInstrumentView;
import se.purestyle.beatr.view.instrumentmixer.volumeobject.InstrumentView;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

public class InstrumentHolderView extends LinearLayout implements IInstrumentMixer {

	public InstrumentHolderView( Context context ) {
	
		super( context );
		
		setOrientation( VERTICAL );
		
		setBackgroundColor( Color.RED );
		
		setLayoutParams( new LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.FILL_PARENT ) );
		
		init( context );
	}
	
	private void init( Context context ) {
		
		for( int i = 0; i < 6; i ++ ) {
			
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