package se.purestyle.beatr.model.editors;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import se.purestyle.beatr.helpers.FileModifier;
import se.purestyle.beatr.helpers.PdConnector;

import com.purestyle.amvc.model.AbstractModel;

public class SynthEditorModel extends AbstractModel {
	
	//Instrument specific settings (stuff that's mirrored in the .pd files)
	private float oscController = 400.0f;
	private String oscControllerName = "osccontroller";
	
	private float vibController = 870.0f;
	private String vibControllerName = "vibcontroller";
	
	private int onoff = 0;
	
	private final String pdInternalInstrumentName;
	
	public SynthEditorModel( String pdInternalInstrumentName ) {
		
		this.pdInternalInstrumentName = pdInternalInstrumentName;
		
		//Prepare a new .pd file that has variable names unique to this model
		Map<String, String> replacementMap = new HashMap<String, String>();
		replacementMap.put( oscControllerName, pdInternalInstrumentName + oscControllerName );
		replacementMap.put( "phascontroller", pdInternalInstrumentName + "phascontroller" );
		replacementMap.put( "vol", pdInternalInstrumentName + "vol" );
		replacementMap.put( "onoff", pdInternalInstrumentName + "onoff" );
		replacementMap.put( vibControllerName, pdInternalInstrumentName + vibControllerName );
		
		File newSynthFile = FileModifier.createIndividualizedFile( replacementMap, "pdfiles/synth.pd" );
		
		FileModifier.traceFile( newSynthFile );
		
		//Tell the pure data environmen to add a new synth
		PdConnector.addPatch( newSynthFile );
		
		PdConnector.sendToPd( pdInternalInstrumentName + "onoff", 0 ); //Turn the instrument off first thing that happens
	}
	
	public float getOscController() {
		
		return oscController;
	}
	
	public void setOscController( float wantedPitch ) {
		
		//Store the 
		oscController = wantedPitch;
		
		//Tell pd to change this individual instruments osxController value
		PdConnector.sendToPd( pdInternalInstrumentName + oscControllerName + "left", oscController );
		PdConnector.sendToPd( pdInternalInstrumentName + oscControllerName + "right", (float) (oscController + .5) ); //(float) (oscController + (vibController / 870))
	}
	
	public float getVibController() {
		
		return vibController;
	}
	
	public void setVibController( float wantedPitch ) {
		
		vibController = wantedPitch;
		
		PdConnector.sendToPd( pdInternalInstrumentName + vibControllerName, vibController );
	}
	
	public void setOnoff( int onoff ) {
		
		this.onoff = onoff;
		
		PdConnector.sendToPd( pdInternalInstrumentName + "onoff", onoff );
	}
	
	public int getOnoff() {
		
		return onoff;
	}
}