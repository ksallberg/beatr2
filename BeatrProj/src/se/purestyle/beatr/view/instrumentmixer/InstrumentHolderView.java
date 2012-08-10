package se.purestyle.beatr.view.instrumentmixer;

import com.purestyle.amvc.model.IModel;

import se.purestyle.beatr.controller.instrumentmixer.volumeobject.InstrumentController;
import se.purestyle.beatr.model.instrumentmixer.InstrumentHolderModel;
import se.purestyle.beatr.view.InstrumentMixerView;
import se.purestyle.beatr.view.instrumentmixer.volumeobject.IInstrumentView;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

public class InstrumentHolderView extends LinearLayout implements IInstrumentMixer {

	private InstrumentHolderModel model;
	
	private int instrumentsPerPage;
	private int currentFirstInstrument = 0; //The current first instrument of this page
	
	public InstrumentHolderView( Context context ) {
	
		super( context );
		
		setOrientation( VERTICAL );
		
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
		if( !((model.getInstruments().size() - 1) - currentFirstInstrument >= instrumentsPerPage) ) {
			
			addView( (View) view );
		}
	}
	
	@Override
	public void removeInstrumentView(IInstrumentView view) {
		
		
	}
	
	/**
	 * Show the previous @instrumentsPerPage number of instruments, but don't go behind the instruments in the list
	 */
	public void nextPage() {
		
		if( currentFirstInstrument + instrumentsPerPage > model.getInstruments().size() - 1 ) {
			
			return;
		}
		
		currentFirstInstrument += instrumentsPerPage;
		
		addInstrumentGroup();
	}
	
	/**
	 * Show the previous @instrumentsPerPage number of instruments, but don't go behind 0
	 */
	public void prevPage() {
		
		if( currentFirstInstrument <= 0 ) {
			
			return;
		}
		
		currentFirstInstrument -= instrumentsPerPage;
		
		addInstrumentGroup();
	}
	
	/**
	 * Remove all current instruments from this view
	 * 
	 */
	private void addInstrumentGroup() {
		
		removeAllViews();
		
		//Add new instruments
		for( int i = currentFirstInstrument; i < currentFirstInstrument + instrumentsPerPage; i ++ ) {
			
			if( i > model.getInstruments().size() - 1 ) {
				
				break;
			}
			
			InstrumentController c = model.getInstruments().get( i );
			
			if( c != null ) {
				
				addView( c.getView() );
			}
		}
	}
}