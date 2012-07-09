package se.purestyle.beatr.view.instrumentmixer;

import org.puredata.android.service.R;

import se.purestyle.beatr.view.InstrumentMixerView;
import se.purestyle.beatr.view.generic.IOverlay;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class AddInstrumentView extends LinearLayout implements IOverlay {

	public AddInstrumentView( Context context ) {
		
		super( context );
		
		setOrientation( VERTICAL );
		
		init( context );
	}

	private void init( Context context ) {
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
		params.addRule( RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE );
		
		Typeface LHLine1Sans = Typeface.createFromAsset( context.getAssets(), "fonts/lhine1sansthin.ttf" );
		
		Button synthButton = new Button( context );
		synthButton.setTypeface( LHLine1Sans );
		synthButton.setTextColor( Color.parseColor("#F6FF00") );
		synthButton.setBackgroundResource( R.drawable.addinstrumentbutton );
		synthButton.setText( "add synth" );
		synthButton.setTag( InstrumentMixerView.SYNTH_BUTTON );
		synthButton.setLayoutParams( params );
		addView( synthButton );
		
		Button drumButton = new Button( context );
		drumButton.setTypeface( LHLine1Sans );
		drumButton.setTextColor( Color.parseColor("#F6FF00") );
		drumButton.setBackgroundResource( R.drawable.addinstrumentbutton );
		drumButton.setText( "add drum" );
		drumButton.setTag( InstrumentMixerView.DRUM_BUTTON );
		drumButton.setLayoutParams( params );
		addView( drumButton );
		
		View v = new View( context );
		v.setLayoutParams( new LayoutParams( 0, 49 ) );
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