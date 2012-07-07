package se.purestyle.beatr.view.instrumentmixer;

import com.purestyle.amvc.model.IModel;

import se.purestyle.beatr.model.instrumentmixer.InstrumentHolderModel;
import se.purestyle.beatr.view.InstrumentMixerView;
import se.purestyle.beatr.view.instrumentmixer.volumeobject.IInstrumentView;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class InstrumentHolderView extends LinearLayout implements IInstrumentMixer {

	private InstrumentHolderModel model;
	
	private int instrumentsPerPage;
	private int currentInstrumentsOnScreen;
	
	private float holderHeight;
	
	public InstrumentHolderView( Context context ) {
	
		super( context );
		
		setOrientation( VERTICAL );
		
		setBackgroundColor( Color.RED );
		
		setLayoutParams( new LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.FILL_PARENT ) );
		
		setTag( InstrumentMixerView.INSTRUMENT_HOLDER_VIEW );
		
		init( context );
	}
	
	private void init( Context context ) {
		
		
	}

	public void setModel( IModel model ) {
		
		this.model = (InstrumentHolderModel) model;
	}
	
	/**
	 * Work as
	 */
	@Override
	public void addInstrumentView(IInstrumentView view) {
		
		//If the combined height of all instruments is higher than the holder view, create a new page instead
		if( getHeight() - InstrumentMixerView.FOOTER_VIEW_HEIGHT < model.getNumberOfInstruments() * InstrumentMixerView.INSTRUMENT_VIEW_HEIGHT ) {
			
			Log.i( "HOHOHOHOOH", "NU €R DET F…R MNGA; SKAPA EN NY SIDA!" );
		
		} else {
			
			addView( (View) view );
		}
	}
	
	@Override
	public void removeInstrumentView(IInstrumentView view) {
		
		
	}
}