package se.purestyle.beatr.controller;

import java.beans.PropertyChangeEvent;

import se.purestyle.beatr.controller.instrumentmixer.InstrumentHolderController;
import se.purestyle.beatr.controller.instrumentmixer.MasterVolumeController;
import se.purestyle.beatr.model.InstrumentMixerModel;
import se.purestyle.beatr.model.instrumentmixer.volumeobject.InstrumentModel;
import se.purestyle.beatr.view.InstrumentMixerView;
import se.purestyle.beatr.view.instrumentmixer.AddInstrumentView;
import se.purestyle.beatr.view.instrumentmixer.InstrumentHolderView;
import se.purestyle.beatr.view.instrumentmixer.MasterVolumeView;
import se.purestyle.beatr.view.instrumentmixer.volumeobject.InstrumentView;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.purestyle.amvc.controller.AbstractController;

public class InstrumentMixerController extends AbstractController {

	public static final String INSTRUMENT_MIXER_VIEW			= "instrumentMixerView";
	public static final String INSTRUMENT_MIXER_MODEL			= "instrumentMixerModel";
	public static final String INSTRUMENT_HOLDER_CONTROLLER 	= "instrumentHolderController";
	public static final String MASTER_VOLUME_CONTROLLER			= "masterVolumeController";
	
	private InstrumentMixerView insMixView;
	
	@Override
	public void setup() {
		
		//Create and add view object for this MVC triple (InstrumentMixer)
		insMixView = new InstrumentMixerView();
		addView( INSTRUMENT_MIXER_VIEW, insMixView );
		
		//Create and add model object for this MVC triple
		InstrumentMixerModel insMixModel = new InstrumentMixerModel();
		addModel( INSTRUMENT_MIXER_MODEL, insMixModel );
		
		//Create and add subcontroller for the part of this app that actually holds the instruments to mix
		InstrumentHolderController holderSub = new InstrumentHolderController();
		holderSub.setup();
		addSubcontroller( INSTRUMENT_HOLDER_CONTROLLER, holderSub );
		holderSub.addObserver( this );
		
		//Create and add subcontroller for the master volume controller
		MasterVolumeController masterVolumeSub = new MasterVolumeController();
		masterVolumeSub.setup();
		masterVolumeSub.setView( ( MasterVolumeView ) insMixView.getViews().get( InstrumentMixerView.MASTER_VOLUME_VIEW ) );
		addSubcontroller( MASTER_VOLUME_CONTROLLER, masterVolumeSub );
		
		insMixView.getViews().get( InstrumentMixerView.ADD_INSTRUMENT_BUTTON ).setOnClickListener( addInstrumentHandler );
		insMixView.getViews().get( InstrumentMixerView.PREV_PAGE_BUTTON ).setOnClickListener( prevPageRequested );
		insMixView.getViews().get( InstrumentMixerView.NEXT_PAGE_BUTTON ).setOnClickListener( nextPageRequested );
		insMixView.getViews().get( InstrumentMixerView.SYNTH_BUTTON ).setOnClickListener( addSynthButton );
		insMixView.getViews().get( InstrumentMixerView.DRUM_BUTTON ).setOnClickListener( addDrumButton );
		
		//Set instrumentHolderView's model to instrumentholderView from InstrumentHolderController
		
		Log.i( "MODDEEEL??", holderSub.getModels().get(InstrumentHolderController.MODEL ).toString() );
		
		( ( InstrumentHolderView ) insMixView.getViews().get( InstrumentMixerView.INSTRUMENT_HOLDER_VIEW ) ).setModel( holderSub.getModels().get(InstrumentHolderController.MODEL ) );
	}

	@Override
	public void teardown() {
		
		
	}
	
	OnClickListener addInstrumentHandler = new OnClickListener() {
		
		@Override
		public void onClick( View v ) {
			
			Log.i( "InstrumentMixerController", "Add instrument" );
			
			( ( AddInstrumentView ) insMixView.getViews().get( InstrumentMixerView.ADD_INSTRUMENT_VIEW ) ).show();
		}
	};
	
	OnClickListener prevPageRequested = new OnClickListener() {
		
		@Override
		public void onClick( View v ) {
			
			Log.i( "InstrumentMixerController", "Previous Page" );
			
			( (InstrumentHolderView) insMixView.getViews().get( InstrumentMixerView.INSTRUMENT_HOLDER_VIEW ) ).prevPage();
		}
	};
	
	OnClickListener nextPageRequested = new OnClickListener() {
		
		@Override
		public void onClick( View v ) {
			
			Log.i( "InstrumentMixerController", "Next Page" );
			
			( (InstrumentHolderView) insMixView.getViews().get( InstrumentMixerView.INSTRUMENT_HOLDER_VIEW ) ).nextPage();
		}
	};
	
	OnClickListener addDrumButton = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			( ( AddInstrumentView ) insMixView.getViews().get( InstrumentMixerView.ADD_INSTRUMENT_VIEW ) ).hide();
			( ( InstrumentHolderController ) getSubcontollers().get( INSTRUMENT_HOLDER_CONTROLLER ) ).addNewInstrument( InstrumentModel.DRUM );
		}
	};
	
	OnClickListener addSynthButton = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			( ( AddInstrumentView ) insMixView.getViews().get( InstrumentMixerView.ADD_INSTRUMENT_VIEW ) ).hide();
			( ( InstrumentHolderController ) getSubcontollers().get( INSTRUMENT_HOLDER_CONTROLLER ) ).addNewInstrument( InstrumentModel.SYNTH );
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