package se.purestyle.beatr.model.editors;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import se.purestyle.beatr.helpers.FileModifier;
import se.purestyle.beatr.helpers.MetronomePlayer;
import se.purestyle.beatr.helpers.PdConnector;
import se.purestyle.beatr.helpers.beatplayer.Beat;

public class DrumEditorModel extends AbstractEditorModel implements PropertyChangeListener {
	
	private final String pdInternalInstrumentName;
	
	private final String onOffControllerName 			= "onoff";
	private int onoff 									= 0;
	
	private final String volControllerName 				= "vol";
	private final String pulseControllerName 			= "pulse";
	
	//root
	private final String rootControllerName 			= "root";
	private 	  float root 							= 33.0f; //20 -> 100
	
	//f01
	private final String f01ControllerName 				= "f01";
	private 	  float f01								= 0.0f; //1 -> 4
	
	//f02
	private final String f02ControllerName 				= "f02";
	private 	  float f02								= 1.0f; //1 -> 4
	
	//clip
	private final String clipControllerName 			= "clip";
	private 	  float clip							= 1.0f; //0 -> 1
	
	//shape
	private final String shapeControllerName 			= "shape";
	private 	  float shape							= 0.0f; //0 -> 0.25
	
	//decay
	private final String decayControllerName 			= "decay";
	private 	  float decay							= 1.0f; //0 -> 1
	
	//mod
	private final String modControllerName 				= "mod";
	private 	  float mod								= 1.0f; //0 -> 1

	/**
	 * Constructor, needs the name of this instrument.
	 * 
	 * @param pdInternalInstrumentName
	 */
	public DrumEditorModel( String pdInternalInstrumentName ) {
		
		this.pdInternalInstrumentName = pdInternalInstrumentName;
		
		//Prepare a new .pd file that has variable names unique to this model
		Map<String, String> replacementMap = new HashMap<String, String>();
		replacementMap.put( volControllerName, pdInternalInstrumentName 	+ volControllerName );
		replacementMap.put( onOffControllerName, pdInternalInstrumentName 	+ onOffControllerName );
		replacementMap.put( pulseControllerName, pdInternalInstrumentName 	+ pulseControllerName );
		
		//Drum settings, make them specific for this instrument
		replacementMap.put( rootControllerName, pdInternalInstrumentName 	+ rootControllerName );
		replacementMap.put( f01ControllerName, pdInternalInstrumentName 	+ f01 );
		replacementMap.put( f02ControllerName, pdInternalInstrumentName 	+ f02ControllerName );
		replacementMap.put( clipControllerName, pdInternalInstrumentName 	+ clipControllerName );
		replacementMap.put( shapeControllerName, pdInternalInstrumentName 	+ shapeControllerName );
		replacementMap.put( decayControllerName, pdInternalInstrumentName 	+ decayControllerName );
		replacementMap.put( modControllerName, pdInternalInstrumentName 	+ modControllerName );
		
		//Load the drum file!
		File newDrumFile = FileModifier.createIndividualizedFile( replacementMap, "pdfiles/drum.pd" );
		
		//Tell the pure data environmen to add a new synth
		PdConnector.addPatch( newDrumFile );
		
		//Make this class listen to changes in the metronome
		MetronomePlayer.addObserver( this );
		
		//set initial values
		setInitialPdValues();
	}
	
	/**
	 * For this instrument, set all the initial values form java, because they're
	 * being forgotten when closing 
	 */
	private void setInitialPdValues() {
		
		PdConnector.sendToPd( pdInternalInstrumentName + rootControllerName, root );
		PdConnector.sendToPd( pdInternalInstrumentName + f01ControllerName, f01 );
		PdConnector.sendToPd( pdInternalInstrumentName + f02ControllerName, f02 );
		PdConnector.sendToPd( pdInternalInstrumentName + clipControllerName, clip );
		PdConnector.sendToPd( pdInternalInstrumentName + shapeControllerName, shape );
		PdConnector.sendToPd( pdInternalInstrumentName + decayControllerName, decay );
		PdConnector.sendToPd( pdInternalInstrumentName + modControllerName, mod );
	}
	
	/**
	 * Destroy this model!
	 */
	public void destroy() {
		
		//Remove reference to the observer
		MetronomePlayer.removeObserver( this );
	}
	
	/**
	 * Get this instrument's name
	 */
	
	/*
	@Override
	public String getInstrumentName() {
		
		return pdInternalInstrumentName;
	}
	*/
	
	/**
	 * For the drum, just return an empty beat
	 */
	
	/*
	@Override
	public Beat getBeat() {
		
		return new Beat();
	}
	*/
	
	public void setOnoff( int onoff ) {
		
		this.onoff = onoff;
		
		PdConnector.sendToPd( pdInternalInstrumentName + onOffControllerName, this.onoff ); //This. just to avoid unused warning
	}

	/*
	 * Below here, a lot of setters and getters live!
	 * 
	 */
	
//	Root_________________________________________________
	public void setRoot( float root ) {
		
		this.root = root;
		PdConnector.sendToPd( pdInternalInstrumentName + rootControllerName, root );
	}
	
	public float getRoot() { return root; }

//	f01_________________________________________________
	public void setF01( float f01 ) {
		
		this.f01 = f01;
		PdConnector.sendToPd( pdInternalInstrumentName + f01ControllerName, f01 );
	}
	
	public float getF01() { return f01; }

//	f02_________________________________________________
	public void setF02( float f02 ) {
		
		this.f02 = f02;
		PdConnector.sendToPd( pdInternalInstrumentName + f02ControllerName, f02 );
	}
	
	public float getF02() { return f02; }

//	clip_________________________________________________
	public void setClip( float clip ) {
		
		this.clip = clip;
		PdConnector.sendToPd( pdInternalInstrumentName + clipControllerName, clip );
	}
	
	public float getClip() { return clip; }

//	shape_________________________________________________
	public void setShape( float shape ) {
		
		this.shape = shape;
		PdConnector.sendToPd( pdInternalInstrumentName + shapeControllerName, shape );
	}
	
	public float getShape() { return shape; }
	
//	decay_________________________________________________
	public void setDecay( float decay ) {
		
		this.decay = decay;
		PdConnector.sendToPd( pdInternalInstrumentName + decayControllerName, decay );
	}
	
	public float getDecay() { return decay; }

//	mod_________________________________________________
	public void setMod( float mod ) {
		
		this.mod = mod;
		PdConnector.sendToPd( pdInternalInstrumentName + modControllerName, mod );
	}
	
	public float getMod() { return mod; }
	
	@Override
	public void propertyChange( PropertyChangeEvent event ) {
		
		//Tick event dispatched by the metronome
		if( event.getPropertyName().equals( MetronomePlayer.TICK ) ) {
			
			//Play the drum!
			PdConnector.sendToPd( pdInternalInstrumentName + pulseControllerName, 1.0f );
			//Make the drum go quiet again
			PdConnector.sendToPd( pdInternalInstrumentName + pulseControllerName, 0.0f );
		}
	}
}