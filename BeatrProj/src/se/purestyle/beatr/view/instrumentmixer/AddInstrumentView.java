package se.purestyle.beatr.view.instrumentmixer;

import se.purestyle.beatr.view.InstrumentMixerView;
import se.purestyle.beatr.view.generic.IOverlay;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class AddInstrumentView extends LinearLayout implements IOverlay {

	public AddInstrumentView( Context context ) {
		
		super( context );
		
		setOrientation( VERTICAL );
		
		init( context );
	}

	private void init( Context context ) {
		
		Button synthButton = new Button( context );
		synthButton.setText("Synth");
		synthButton.setTag( InstrumentMixerView.SYNTH_BUTTON );
		
		Button drumButton = new Button( context );
		drumButton.setText("Drum");
		drumButton.setTag( InstrumentMixerView.DRUM_BUTTON );
		
		addView( synthButton );
		addView( drumButton );
		
		View v = new View( context );
		v.setLayoutParams( new LayoutParams( 0, 50 ) );
		addView( v );
	}
	
	@Override
	public void show() {
		
		setVisibility( VISIBLE );
	}

	@Override
	public void hide() {
		
		setVisibility( INVISIBLE );
	}
}