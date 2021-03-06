package se.purestyle.beatr.view;

import java.beans.PropertyChangeEvent;

import se.purestyle.beatr.view.instrumentmixer.StartView;

import android.content.Context;

import com.purestyle.amvc.view.AbstractView;

public class InstrumentMixerView extends AbstractView {

	public static final String	START_VIEW				= "startView";
	public static final String	INSTRUMENT_HOLDER_VIEW	= "instrumentHolderView";
	public static final String	ADD_INSTRUMENT_VIEW		= "addInstrumentView";
	public static final String	MASTER_VOLUME_VIEW		= "masterVolumeView";
	public static final String	METRONOME_VIEW			= "metronomeView";
	public static final String	ADD_INSTRUMENT_BUTTON	= "addInstrumentButton";
	public static final String	PREV_PAGE_BUTTON		= "prevPageButton";
	public static final String	NEXT_PAGE_BUTTON		= "nextPageButton";
	public static final String	CANCEL_ADD_INS_BUTTON	= "cancelAddInsButton";
	
	public static final String	SYNTH_BUTTON			= "synthButton";
	public static final String	DRUM_BUTTON				= "drumButton";
	public static final String	BASS_BUTTON				= "bassButton";
	
	public static final int 	FOOTER_VIEW_HEIGHT		= 60;
	public static final int		INSTRUMENT_VIEW_HEIGHT	= 64;
	
	private StartView startView;
	
	public InstrumentMixerView( Context context ) {
		
		startView = new StartView( context );
		
		views.put( START_VIEW, 				startView );
		views.put( ADD_INSTRUMENT_VIEW,  	startView.findViewWithTag( ADD_INSTRUMENT_VIEW ) );
		views.put( ADD_INSTRUMENT_BUTTON, 	startView.findViewWithTag( ADD_INSTRUMENT_BUTTON ) );
		views.put( PREV_PAGE_BUTTON, 		startView.findViewWithTag( PREV_PAGE_BUTTON ) );
		views.put( NEXT_PAGE_BUTTON, 		startView.findViewWithTag( NEXT_PAGE_BUTTON ) );
		views.put( INSTRUMENT_HOLDER_VIEW,  startView.findViewWithTag( INSTRUMENT_HOLDER_VIEW ) );
		views.put( SYNTH_BUTTON,  			startView.findViewWithTag( SYNTH_BUTTON ) );
		views.put( DRUM_BUTTON,  			startView.findViewWithTag( DRUM_BUTTON ) );
		views.put( MASTER_VOLUME_VIEW,		startView.findViewWithTag( MASTER_VOLUME_VIEW ) );
		views.put( BASS_BUTTON, 			startView.findViewWithTag( BASS_BUTTON ) );
		views.put( METRONOME_VIEW, 			startView.findViewWithTag( METRONOME_VIEW ) );
		views.put( CANCEL_ADD_INS_BUTTON, 	startView.findViewWithTag( CANCEL_ADD_INS_BUTTON ) );
	}
	
	/**
	 * Needed just to allow me to use AbstractView
	 */
	@Override
	public void propertyChange( PropertyChangeEvent event ) {}
	
	public StartView getStartView() { return startView; }
}