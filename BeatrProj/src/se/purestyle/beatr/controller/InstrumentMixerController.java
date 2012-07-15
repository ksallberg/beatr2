package se.purestyle.beatr.controller;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import se.purestyle.beatr.controller.editors.SynthEditorController;
import se.purestyle.beatr.controller.instrumentmixer.InstrumentHolderController;
import se.purestyle.beatr.controller.instrumentmixer.MasterVolumeController;
import se.purestyle.beatr.controller.instrumentmixer.volumeobject.InstrumentController;
import se.purestyle.beatr.helpers.InstrumentTracker;
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
	
	public static final String NEW_INSTRUMENT_ADDED				= "newInstrumentAdded"; //This also exists in InstrumentHolderController, 
																						//but I want it here as well to make this class more independent
	
	private InstrumentMixerView 		insMixView;
	private List<AbstractController>	instrumentEditors;
	
	@Override
	public void setup() {
		
		instrumentEditors = new ArrayList<AbstractController>();
		
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
		
		( ( InstrumentHolderView ) insMixView.getViews().get( InstrumentMixerView.INSTRUMENT_HOLDER_VIEW ) ).setModel( holderSub.getModels().get(InstrumentHolderController.MODEL ) );
	}

	@Override
	public void teardown() {
		
		
	}
	
	OnClickListener addInstrumentHandler = new OnClickListener() {
		
		@Override
		public void onClick( View v ) {
			
			( ( AddInstrumentView ) insMixView.getViews().get( InstrumentMixerView.ADD_INSTRUMENT_VIEW ) ).show();
		}
	};
	
	OnClickListener prevPageRequested = new OnClickListener() {
		
		@Override
		public void onClick( View v ) {
			
			( (InstrumentHolderView) insMixView.getViews().get( InstrumentMixerView.INSTRUMENT_HOLDER_VIEW ) ).prevPage();
		}
	};
	
	OnClickListener nextPageRequested = new OnClickListener() {
		
		@Override
		public void onClick( View v ) {
			
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
	
	/**
	 * event.getNewValue() is an InstrumentView
	 * 
	 * event.getSource() is the callee, an InstrumentController
	 */
	@Override
	public void propertyChange( PropertyChangeEvent event ) {
		
		if( event.getPropertyName().equals( InstrumentHolderController.NEW_INSTRUMENT_ADDED ) ) {
			
			//Create the mixer/volume part 
			InstrumentHolderView holder = ( InstrumentHolderView ) getViews().get( INSTRUMENT_MIXER_VIEW ).getViews().get( InstrumentMixerView.INSTRUMENT_HOLDER_VIEW );
			
			holder.addInstrumentView( (InstrumentView) event.getNewValue() );
			
			//Get a unique instrument name, so that it can be stored in the pd system
			String newInstrumentName = null;
			
			//InstrumentController
			AbstractController instrumentEditorController = null;
			
			//Create the editor/instrument part
			//If a synth was added
			InstrumentModel modelRef = (InstrumentModel) ( ( InstrumentController ) event.getSource() ).getModels().get( InstrumentController.MODEL );
			
			Log.i( "InstrumentMixerController: instrumenttype:", modelRef.getInstrumentType() );
			
			if( modelRef.getInstrumentType().equals( InstrumentModel.SYNTH ) ) {
				
				newInstrumentName = InstrumentTracker.getNextSynthName();
				instrumentEditorController = new SynthEditorController( newInstrumentName );
			
			//If a drum was added
			} else if( modelRef.getInstrumentType().equals( InstrumentModel.DRUM ) ) {
				
				newInstrumentName = InstrumentTracker.getNextSynthName();
			
			} else {
				
				Log.e( "InstrumentMixerController", "Error: No instrument matching!" );
			}
			
			//Pass the name to the model of the InstrumentController
			( (InstrumentModel) 
					( ( InstrumentController ) event.getSource() ).getModels().get(InstrumentController.MODEL )
			).setPdInternalName( newInstrumentName );
			
			//Setup the new controller
			instrumentEditorController.setup();
			
			//Add this editor to the list of all editors
			instrumentEditors.add( instrumentEditorController );
		}
	}
}