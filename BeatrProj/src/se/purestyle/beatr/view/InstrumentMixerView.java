package se.purestyle.beatr.view;

import java.beans.PropertyChangeEvent;

import se.purestyle.beatr.ResourceManager;
import se.purestyle.beatr.view.instrumentmixer.AddInstrumentView;
import se.purestyle.beatr.view.instrumentmixer.StartView;

import com.purestyle.amvc.view.AbstractView;

public class InstrumentMixerView extends AbstractView {

	public static final String 	START_VIEW				= "startView";
	public static final String 	INSTRUMENT_HOLDER_VIEW	= "instrumentHolderView";
	public static final String 	ADD_INSTRUMENT_BUTTON	= "addInstrumentButton";
	public static final String 	PREV_PAGE_BUTTON		= "prevPageButton";
	public static final String 	NEXT_PAGE_BUTTON		= "nextPageButton";
	
	public static final int 	FOOTER_VIEW_HEIGHT		= 60;
	public static final int		INSTRUMENT_VIEW_HEIGHT	= 64;
	
	private StartView startView;
	
	public InstrumentMixerView() {
		
		startView = new StartView( ResourceManager.getContext() );
		
		AddInstrumentView adder = new AddInstrumentView( ResourceManager.getContext() );
		
		startView.addView( adder );
		
		views.put( START_VIEW, 				startView );
		views.put( ADD_INSTRUMENT_BUTTON, 	startView.findViewWithTag( ADD_INSTRUMENT_BUTTON ) );
		views.put( PREV_PAGE_BUTTON, 		startView.findViewWithTag( PREV_PAGE_BUTTON ) );
		views.put( NEXT_PAGE_BUTTON, 		startView.findViewWithTag( NEXT_PAGE_BUTTON ) );
		views.put( INSTRUMENT_HOLDER_VIEW,  startView.findViewWithTag( INSTRUMENT_HOLDER_VIEW ) );
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		
		
	}
	
	
	public StartView getStartView() {
		
		return startView;
	}
}