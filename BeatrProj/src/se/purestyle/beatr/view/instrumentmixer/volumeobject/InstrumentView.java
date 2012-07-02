package se.purestyle.beatr.view.instrumentmixer.volumeobject;

import org.puredata.android.service.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class InstrumentView extends LinearLayout {

	private Button 	rewindInstrumentBtn;
	private View	volumeDragger;
	private Button 	editInstrumentBtn;
	
	public InstrumentView( Context context ) {
	
		super( context );
		
		//This LinearLayout holds 3 objects and they should be aligned after each other, horizontally
		setOrientation( HORIZONTAL );
		setLayoutParams( new LayoutParams( LayoutParams.FILL_PARENT, 100 ) );
		
		init( context );
	}
	
	/**
	 * Create views and add them as  
	 * 
	 * @param context
	 */
	private void init( Context context ) {
		
		setBackgroundColor( Color.YELLOW ); //debug
		setLayoutParams( new LayoutParams( LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT ) );
		
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