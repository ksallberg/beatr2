package se.purestyle.beatr.model.editors;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import se.purestyle.beatr.helpers.FileModifier;
import se.purestyle.beatr.helpers.PdConnector;
import se.purestyle.beatr.helpers.beatplayer.Beat;
import se.purestyle.beatr.helpers.beatplayer.Recorder;

import android.util.Pair;

@SuppressWarnings("unchecked")
public class BassEditorModel extends AbstractEditorModel {
	
	//Instrument specific settings (stuff that's mirrored in the .pd files)
	private float oscController = 400.0f;
	private final String oscControllerName = "osccontroller";
	
	private float vibController = 870.0f;
	private final String vibControllerName = "vibcontroller";
	
	private final float maxAttack = 5.0f;
	private float attackController = maxAttack / 3;
	private final String attackControllerName = "attack";	
	
	private final String phasControllerName = "phascontroller";
	private final String volControllerName = "vol";
	private final String onOffControllerName = "onoff";
	
	private int onoff = 0;
	
	private final String pdInternalInstrumentName;
	
	private Recorder recorder;
	
	public BassEditorModel( String pdInternalInstrumentName ) {
		
		this.pdInternalInstrumentName = pdInternalInstrumentName;
		
		//Prepare a new .pd file that has variable names unique to this model
		Map<String, String> replacementMap = new HashMap<String, String>();
		replacementMap.put( oscControllerName, pdInternalInstrumentName + oscControllerName );
		replacementMap.put( phasControllerName, pdInternalInstrumentName + phasControllerName );
		replacementMap.put( volControllerName, pdInternalInstrumentName + volControllerName );
		replacementMap.put( onOffControllerName, pdInternalInstrumentName + onOffControllerName );
		replacementMap.put( vibControllerName, pdInternalInstrumentName + vibControllerName );
		replacementMap.put( attackControllerName, pdInternalInstrumentName + attackControllerName );
		
		File newBassFile = FileModifier.createIndividualizedFile( replacementMap, "pdfiles/bass.pd" );
		
		//Tell the pure data environmen to add a new synth
		PdConnector.addPatch( newBassFile );
		
		PdConnector.sendToPd( pdInternalInstrumentName + onOffControllerName, 0 ); //Turn the instrument off first thing that happens
	}
	
	public float getOscController() {
		
		return oscController;
	}
	
	public void setOscController( float wantedPitch ) {
		
		//Store the 
		oscController = wantedPitch;
		
		//Tell pd to change this individual instruments osxController value
		PdConnector.sendToPd( pdInternalInstrumentName + oscControllerName + "left", oscController );
		PdConnector.sendToPd( pdInternalInstrumentName + oscControllerName + "right", (float) (oscController + .5) );
		
		if( recorder.isRecording() ) {
			
			Pair<String, Float> p = new Pair<String, Float>( pdInternalInstrumentName + oscControllerName + "left", oscController );
			Pair<String, Float> p2 = new Pair<String, Float>( pdInternalInstrumentName + oscControllerName + "right", (float) (oscController + .5) );
			
			Pair<String, Float>[] ls = (Pair<String, Float>[]) new Pair[] {p, p2};
			
			recorder.recordList( ls );
		}
	}
	
	public float getVibController() {
		
		return vibController;
	}
	
	public void setVibController( float wantedPitch ) {
		
		vibController = wantedPitch;
		
		PdConnector.sendToPd( pdInternalInstrumentName + vibControllerName, vibController );
		
		if( recorder.isRecording() ) {
			
			Pair<String, Float> p = new Pair<String, Float>( pdInternalInstrumentName + vibControllerName, vibController );
			
			Pair<String, Float>[] ls = (Pair<String, Float>[]) new Pair[] {p};
			
			recorder.recordList( ls );
		}
	}
	
	public void setOnoff( int onoff ) {
		
		this.onoff = onoff;
		
		PdConnector.sendToPd( pdInternalInstrumentName + onOffControllerName, onoff );
		
		if( recorder.isRecording() ) {
			
			Pair<String, Float> p = new Pair<String, Float>( pdInternalInstrumentName + onOffControllerName, (float) onoff );
			
			Pair<String, Float>[] ls = (Pair<String, Float>[]) new Pair[] {p};
			
			recorder.recordList( ls );
		}
	}
	
	public void setAttack( float wantedPercent ) {
		
		attackController = wantedPercent * maxAttack;
		
		PdConnector.sendToPd( pdInternalInstrumentName + attackControllerName, attackController );
	}
	
	public float getAttack() {
		
		return attackController;
	}
	
	public float getMaxAttack() {
		
		return maxAttack;
	}
	
	public int getOnoff() {
		
		return onoff;
	}
	
	public void registerRecorder( Recorder recorder ) {
		
		this.recorder = recorder;
	}
	
	public Beat getBeat() {
		
		return recorder.getBeat();
	}
	
	@Override
	public String getInstrumentName() {
		
		return pdInternalInstrumentName;
	}
}