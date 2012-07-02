package se.purestyle.beatr.view.instrumentmixer.volumeobject;

import org.puredata.android.service.R;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class InstrumentView extends LinearLayout implements IInstrumentView {

	private Button 	rewindInstrumentBtn;
	private View	volumeDragger;
	private Button 	editInstrumentBtn;
	
	public InstrumentView( Context context ) {
	
		super( context );
		
		//This LinearLayout holds 3 objects and they should be aligned after each other, horizontally
		setOrientation( HORIZONTAL );
		
		init( context );
	}
	
	/**
	 * 
	 * 
	 * @param context
	 */
	private void init( Context context ) {
		
		setBackgroundColor( Color.YELLOW ); //debug
		
		LayoutParams params = new LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT );
		params.gravity = Gravity.CENTER;
		
		setLayoutParams( params );
		
		setHorizontalGravity( HORIZONTAL );
		
		rewindInstrumentBtn = new Button( context );
		rewindInstrumentBtn.setBackgroundResource( R.drawable.rewindinstrument );
		
		volumeDragger = new VolumeDraggerView( context );
		
		editInstrumentBtn = new Button( context );
		editInstrumentBtn.setBackgroundResource( R.drawable.editinstrument );
		
		addView( rewindInstrumentBtn );
		addView( volumeDragger );
		addView( editInstrumentBtn );
	}
}