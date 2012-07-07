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
	private int currentFirstInstrument = 0; //The current first instrument of this page
	
	public InstrumentHolderView( Context context ) {
	
		super( context );
		
		setOrientation( VERTICAL );
		
		setBackgroundColor( Color.RED );
		
		setLayoutParams( new LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.FILL_PARENT ) );
		
		setTag( InstrumentMixerView.INSTRUMENT_HOLDER_VIEW );
	}

	/**
	 * This method is called when 
	 * 
	 * @param context
	 * @param layoutHeight
	 */
	public void init( Context context, int footerViewHeight ) {

		instrumentsPerPage = (int) Math.floor( Math.round( ( getHeight() - footerViewHeight ) / InstrumentMixerView.INSTRUMENT_VIEW_HEIGHT ) );
		
		Log.i( "instrumentsPerPage", "" + instrumentsPerPage );
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
	
	public void nextPage() {
		
		removeAllViews();
		
		model.getInstruments();
	}
	
	public void prevPage() {
		
		
	}
}