package se.purestyle.beatr.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import se.purestyle.beatr.controller.instrumentmixer.InstrumentHolderController;
import se.purestyle.beatr.controller.instrumentmixer.MasterVolumeController;
import se.purestyle.beatr.controller.instrumentmixer.volumeobject.InstrumentController;
import se.purestyle.beatr.helpers.InstrumentTracker;
import se.purestyle.beatr.model.InstrumentMixerModel;
import se.purestyle.beatr.model.editors.BassEditorModel;
import se.purestyle.beatr.model.editors.DrumEditorModel;
import se.purestyle.beatr.model.editors.SynthEditorModel;
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
import com.purestyle.amvc.model.AbstractModel;

public class InstrumentMixerController extends AbstractController {

	public static final String INSTRUMENT_MIXER_VIEW			= "instrumentMixerView";
	public static final String INSTRUMENT_MIXER_MODEL			= "instrumentMixerModel";
	public static final String INSTRUMENT_HOLDER_CONTROLLER 	= "instrumentHolderController";
	public static final String MASTER_VOLUME_CONTROLLER			= "masterVolumeController";
	
	public static final String NEW_INSTRUMENT_ADDED				= "newInstrumentAdded"; //This also exists in InstrumentHolderController, 
																						//but I want it here as well to make this class more independent
	
	private InstrumentMixerView 		insMixView;
	private PropertyChangeSupport eventFirer = new PropertyChangeSupport( this );
	
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
		holderSub.addListener( this );
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
		insMixView.getViews().get( InstrumentMixerView.BASS_BUTTON ).setOnClickListener( addBassButton );
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
	
	OnClickListener addBassButton = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			( ( AddInstrumentView ) insMixView.getViews().get( InstrumentMixerView.ADD_INSTRUMENT_VIEW ) ).hide();
			( ( InstrumentHolderController ) getSubcontollers().get( INSTRUMENT_HOLDER_CONTROLLER ) ).addNewInstrument( InstrumentModel.BASS );
		}
	};
	
	OnClickListener addSynthButton = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			( ( AddInstrumentView ) insMixView.getViews().get( InstrumentMixerView.ADD_INSTRUMENT_VIEW ) ).hide();
			( ( InstrumentHolderController ) getSubcontollers().get( INSTRUMENT_HOLDER_CONTROLLER ) ).addNewInstrument( InstrumentModel.SYNTH );
		}
	};
	
	OnClickListener addDrumButton = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			( ( AddInstrumentView ) insMixView.getViews().get( InstrumentMixerView.ADD_INSTRUMENT_VIEW ) ).hide();
			( ( InstrumentHolderController ) getSubcontollers().get( INSTRUMENT_HOLDER_CONTROLLER ) ).addNewInstrument( InstrumentModel.DRUM );
		}
	};
	
	public void addListener( PropertyChangeListener listener ) {
		
		eventFirer.addPropertyChangeListener( listener );
	}
	
	/**
	 * event.getNewValue() is an InstrumentView
	 * 
	 * event.getSource() is the callee, an InstrumentController
	 */
	//TODO: Behöver nåt snyggare creation pattern
	@Override
	public void propertyChange( PropertyChangeEvent event ) {
		
		if( event.getPropertyName().equals( InstrumentHolderController.NEW_INSTRUMENT_ADDED ) ) {
			
			//Create the mixer/volume part 
			InstrumentHolderView holder = ( InstrumentHolderView ) getViews().get( INSTRUMENT_MIXER_VIEW ).getViews().get( InstrumentMixerView.INSTRUMENT_HOLDER_VIEW );
			
			holder.addInstrumentView( (InstrumentView) event.getNewValue() );
			
			//Get a unique instrument name, so that it can be stored in the pd system
			String newInstrumentName = null; //Will be set according to the type of instrument it is
			
			//Get a ref to the volumecontroller, to be able to tell it the name of what it's going to be modifying
			InstrumentModel modelRef = (InstrumentModel) ( ( InstrumentController ) event.getSource() ).getModels().get( InstrumentController.MODEL );
			
			//Create a new editorModel, the one that's gonna start the sound and then modify and store settings about it
			AbstractModel editorModel = null; //Will be set according to the type of instrument it is
			
			if( modelRef.getInstrumentType().equals( InstrumentModel.SYNTH ) ) {
				
				newInstrumentName 	= InstrumentTracker.getNextSynthName();
				editorModel 		= new SynthEditorModel( newInstrumentName );
			
			//If a drum was added
			} else if( modelRef.getInstrumentType().equals( InstrumentModel.BASS ) ) {
				
				newInstrumentName 	= InstrumentTracker.getNextBassName();
				editorModel 		= new BassEditorModel( newInstrumentName );
				
			} else if( modelRef.getInstrumentType().equals( InstrumentModel.DRUM ) ) {
				
				newInstrumentName 	= InstrumentTracker.getNextDrumName();
				editorModel 		= new DrumEditorModel( newInstrumentName );
				
			} else {
				
				Log.e( "InstrumentMixerController", "Error: No instrument matching!" );
			}
			
			//Pass the name to the model of the InstrumentController
			modelRef.setPdInternalName( newInstrumentName );
			
			//Register the model to InstrumentTracker so it's reachable in other activities
			// (the modelRef is only for controlling volume and does not need to be included to InstrumenTracker)
			//Instrument tracker is only used for storing stuff outside of the BeatrActivity
			InstrumentTracker.registerModel( newInstrumentName, editorModel );
		}
		
		if( event.getPropertyName().equals( InstrumentController.EDIT_EVENT ) ) {
			
			//Bubble
			eventFirer.firePropertyChange( InstrumentController.EDIT_EVENT, null, event.getNewValue() );
		}
	}
}