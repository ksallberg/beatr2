package se.purestyle.beatr.view.instrumentmixer.volumeobject;

import org.puredata.android.service.R;

import com.purestyle.amvc.model.IModelUser;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class InstrumentView extends LinearLayout implements IInstrumentView {

	public static final String REWIND_BUTTON = "rewindButton";
	public static final String VOLUME_DRAGGER = "volumeDragger";
	public static final String EDIT_BUTTON = "editButton";
	
	private Button 	rewindInstrumentBtn;
	private IModelUser	volumeDragger;
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
		
		LayoutParams params = new LayoutParams( LayoutParams.WRAP_CONTENT, 59 + 5 );
		params.gravity = Gravity.CENTER;
		
		setLayoutParams( params );
		
		setHorizontalGravity( HORIZONTAL );
		
		rewindInstrumentBtn = new Button( context );
		rewindInstrumentBtn.setTag( REWIND_BUTTON );
		rewindInstrumentBtn.setBackgroundResource( R.drawable.rewindinstrument );
		
		volumeDragger = new VolumeDraggerView( context );
		((VolumeDraggerView) volumeDragger).setTag( VOLUME_DRAGGER );
		
		editInstrumentBtn = new Button( context );
		editInstrumentBtn.setTag( EDIT_BUTTON );
		editInstrumentBtn.setBackgroundResource( R.drawable.editinstrument );
		
		addView( rewindInstrumentBtn );
		addView( (VolumeDraggerView) volumeDragger );
		addView( editInstrumentBtn );
	}
	
	public VolumeDraggerView getVolumeDraggerView() {
		
		return (VolumeDraggerView) volumeDragger;
	}
}