package se.purestyle.beatr.controller;

import java.beans.PropertyChangeEvent;

import se.purestyle.beatr.controller.instrumentmixer.InstrumentHolderController;
import se.purestyle.beatr.model.InstrumentMixerModel;
import se.purestyle.beatr.view.InstrumentMixerView;
import se.purestyle.beatr.view.instrumentmixer.InstrumentHolderView;
import se.purestyle.beatr.view.instrumentmixer.volumeobject.InstrumentView;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.purestyle.amvc.controller.AbstractController;

public class InstrumentMixerController extends AbstractController {

	public static final String INSTRUMENT_MIXER_VIEW			= "instrumentMixerView";
	public static final String INSTRUMENT_MIXER_MODEL			= "instrumentMixerModel";
	public static final String INSTRUMENT_HOLDER_CONTROLLER 	= "instrumentHolderController";
	
	@Override
	public void setup() {
		
		//Create and add view object for this MVC triple (InstrumentMixer)
		InstrumentMixerView insMixView = new InstrumentMixerView();
		addView( INSTRUMENT_MIXER_VIEW, insMixView );
		
		//Create and add model object for this MVC triple
		InstrumentMixerModel insMixModel = new InstrumentMixerModel();
		addModel( INSTRUMENT_MIXER_MODEL, insMixModel );
		
		//Create and add subcontroller for the part of this app that actually holds the instruments to mix
		InstrumentHolderController holderSub = new InstrumentHolderController();
		holderSub.setup();
		addSubcontroller( INSTRUMENT_HOLDER_CONTROLLER, holderSub );
		holderSub.addObserver( this );
		
		insMixView.getViews().get( InstrumentMixerView.ADD_INSTRUMENT_BUTTON ).setOnClickListener( addInstrumentHandler );
		insMixView.getViews().get( InstrumentMixerView.PREV_PAGE_BUTTON ).setOnClickListener( prevPageRequested );
		insMixView.getViews().get( InstrumentMixerView.NEXT_PAGE_BUTTON ).setOnClickListener( nextPageRequested );
		
		//Set instrumentHolderView's model to instrumentholderView from InstrumentHolderController
		
		Log.i( "MODDEEEL??", holderSub.getModels().get(InstrumentHolderController.MODEL ).toString() );
		
		( (InstrumentHolderView) insMixView.getViews().get( InstrumentMixerView.INSTRUMENT_HOLDER_VIEW ) ).setModel( holderSub.getModels().get(InstrumentHolderController.MODEL ) );
	}

	@Override
	public void teardown() {
		
		
	}
	
	OnClickListener addInstrumentHandler = new OnClickListener() {
		
		@Override
		public void onClick( View v ) {
			
			
			Log.i( "InstrumentMixerController", "Add instrument" );
			
			( ( InstrumentHolderController ) getSubcontollers().get( INSTRUMENT_HOLDER_CONTROLLER ) ).addNewInstrument();
		}
	};
	
	OnClickListener prevPageRequested = new OnClickListener() {
		
		@Override
		public void onClick( View v ) {
			
			Log.i( "InstrumentMixerController", "Previous Page" );
			
			( (InstrumentHolderView) getViews().get( InstrumentMixerView.INSTRUMENT_HOLDER_VIEW ) ).prevPage();
		}
	};
	
	OnClickListener nextPageRequested = new OnClickListener() {
		
		@Override
		public void onClick( View v ) {
			
			Log.i( "InstrumentMixerController", "Next Page" );
			
			( (InstrumentHolderView) getViews().get( InstrumentMixerView.INSTRUMENT_HOLDER_VIEW ) ).nextPage();
		}
	};
	
	//PropertyListeners
	@Override
	public void propertyChange( PropertyChangeEvent event ) {
		
		if( event.getPropertyName().equals( InstrumentHolderController.NEW_INSTRUMENT_ADDED ) ) {
			
			InstrumentHolderView holder = ( InstrumentHolderView ) getViews().get( INSTRUMENT_MIXER_VIEW ).getViews().get( InstrumentMixerView.INSTRUMENT_HOLDER_VIEW );
			
			holder.addInstrumentView( (InstrumentView) event.getNewValue() );
		}
	}
}