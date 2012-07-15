package se.purestyle.beatr.model.editors;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import se.purestyle.beatr.helpers.FileModifier;
import se.purestyle.beatr.helpers.PdConnector;

import com.purestyle.amvc.model.AbstractModel;

public class BassEditorModel extends AbstractModel {
	
	//Instrument specific settings (stuff that's mirrored in the .pd files)
	private float oscController = 200.0f;
	
	private final String pdInternalInstrumentName;
	
	public BassEditorModel( String pdInternalInstrumentName ) {
		
		this.pdInternalInstrumentName = pdInternalInstrumentName;
		
		//Prepare a new .pd file that has variable names unique to this model
		Map<String, String> replacementMap = new HashMap<String, String>();
		replacementMap.put( "osccontroller", pdInternalInstrumentName + "osccontroller-right" );
		replacementMap.put( "vol", pdInternalInstrumentName + "vol" );
		
		File newSynthFile = FileModifier.createIndividualizedFile( replacementMap, "pdfiles/smallright.pd" );
		
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
		PdConnector.sendToPd( pdInternalInstrumentName + "osccontroller-right", oscController );
	}
}