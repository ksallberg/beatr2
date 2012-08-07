package se.purestyle.beatr.model.editors;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import android.util.Log;

import se.purestyle.beatr.helpers.FileModifier;
import se.purestyle.beatr.helpers.MetronomePlayer;
import se.purestyle.beatr.helpers.PdConnector;

public class DrumEditorModel extends AbstractEditorModel implements PropertyChangeListener {
	
	private final String pdInternalInstrumentName;
	
	private final String onOffControllerName 			= "onoff";
	private int onoff 									= 0;
	
	private final String volControllerName 				= "vol";
	private final String pulseControllerName 			= "pulse";
	
	//root
	private final String rootControllerName 			= "root";
	private 	  float root 							= 33.0f;
	private	final float minRoot							= 20.0f;
	private	final float maxRoot							= 100.0f;
	
	//f01
	private final String f01ControllerName 				= "f01";
	private 	  float f01								= 0.0f;
	private final float minF01							= 1.0f;
	private final float maxF01							= 4.0f;
	
	//f02
	private final String f02ControllerName 				= "f02";
	private 	  float f02								= 1.0f;
	private final float minF02							= 1.0f;
	private final float maxF02							= 4.0f;
	
	//clip
	private final String clipControllerName 			= "clip";
	private 	  float clip							= 1.0f;
	private final float minClip							= 0.0f;
	private final float maxClip							= 1.0f;
	
	//shape
	private final String shapeControllerName 			= "shape";
	private 	  float shape							= 0.0f;
	private final float minShape						= 0.0f;
	private final float maxShape						= 0.25f;
	
	//decay
	private final String decayControllerName 			= "decay";
	private 	  float decay							= 1.0f;
	private final float minDecay						= 0.0f;
	private final float maxDecay						= 1.0f;
	
	//mod
	private final String modControllerName 				= "mod";
	private 	  float mod								= 1.0f; //0 -> 1
	private final float minMod							= 0.0f;
	private final float maxMod							= 1.0f;
	
	
//// Scheduling of the drum beats
	private final int 	NUMBER_OF_PADS					= 9;
	private final boolean[] drumIsOn					= new boolean[ NUMBER_OF_PADS ];

	/**
	 * Constructor, needs the name of this instrument.
	 * 
	 * @param pdInternalInstrumentName
	 */
	public DrumEditorModel( String pdInternalInstrumentName ) {
		
		this.pdInternalInstrumentName = pdInternalInstrumentName;
		
		//Set all pads to off
		for( int i = 0; i < NUMBER_OF_PADS; i ++ ) {
			
			drumIsOn[ i ] = false;
		}
		
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
	
	public void setDrumOnOff( int id, boolean on ) {
		
		drumIsOn[ id ] = on;
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
		
		Log.i( "DrumEditorModel", "onoff?" + onoff );
		
		PdConnector.sendToPd( pdInternalInstrumentName + onOffControllerName, this.onoff ); //This. just to avoid unused warning
	}

	/*
	 * Below here, a lot of setters and getters live!
	 * 
	 */
	
//	Root_________________________________________________
	public void setRoot( float pcent ) {
		
		root = minRoot + ( maxRoot - minRoot ) * pcent;
		PdConnector.sendToPd( pdInternalInstrumentName + rootControllerName, root );
	}
	
	public float getRoot() { return root; }

//	f01_________________________________________________
	public void setF01( float pcent ) {
		
		f01 = minF01 + ( maxF01 - minF01 ) * pcent;
		PdConnector.sendToPd( pdInternalInstrumentName + f01ControllerName, f01 );
	}
	
	public float getF01() { return f01; }

//	f02_________________________________________________
	public void setF02( float pcent ) {
		
		f02 = minF02 + ( maxF02 - minF02 ) * pcent;
		PdConnector.sendToPd( pdInternalInstrumentName + f02ControllerName, f02 );
	}
	
	public float getF02() { return f02; }

//	clip_________________________________________________
	public void setClip( float pcent ) {
		
		clip = minClip + ( maxClip - minClip ) * pcent;
		PdConnector.sendToPd( pdInternalInstrumentName + clipControllerName, clip );
	}
	
	public float getClip() { return clip; }

//	shape_________________________________________________
	public void setShape( float pcent ) {
		
		shape = minShape + ( maxShape - minShape ) * pcent;
		PdConnector.sendToPd( pdInternalInstrumentName + shapeControllerName, shape );
	}
	
	public float getShape() { return shape; }
	
//	decay_________________________________________________
	public void setDecay( float pcent ) {
		
		decay = minDecay + ( maxDecay - minDecay ) * pcent;
		PdConnector.sendToPd( pdInternalInstrumentName + decayControllerName, decay );
	}
	
	public float getDecay() { return decay; }

//	mod_________________________________________________
	public void setMod( float pcent ) {
		
		mod = minMod + ( maxMod - minMod ) * pcent;
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