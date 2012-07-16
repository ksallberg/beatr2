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
	private String oscControllerName;
	
	private final String pdInternalInstrumentName;
	
	public SynthEditorModel( String pdInternalInstrumentName ) {
		
		this.pdInternalInstrumentName = pdInternalInstrumentName;
		
		//Prepare a new .pd file that has variable names unique to this model
		Map<String, String> replacementMap = new HashMap<String, String>();
		replacementMap.put( "osccontroller", pdInternalInstrumentName + "osccontroller" );
		replacementMap.put( "vol", pdInternalInstrumentName + "vol" );
		
		File newSynthFile = FileModifier.createIndividualizedFile( replacementMap, "pdfiles/threecosdynamic.pd" );
		
		FileModifier.traceFile( newSynthFile );
		
		//Tell the pure data environmen to add a new synth
		PdConnector.addPatch( newSynthFile );
	}
	
	public float getOscController() {
		
		return oscController;
	}
	
	public void setOscController( float wantedPitch ) {
		
		//Store the 
		oscController = wantedPitch;
		
		//Tell pd to change this individual instruments osxController value
		PdConnector.sendToPd( pdInternalInstrumentName + oscControllerName, oscController );
	}
	
//	public String getInternalInstrumentName() { return pdInternalInstrumentName; }
}