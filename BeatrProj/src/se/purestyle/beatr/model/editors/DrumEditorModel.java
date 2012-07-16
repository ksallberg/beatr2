package se.purestyle.beatr.model.editors;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import se.purestyle.beatr.helpers.FileModifier;
import se.purestyle.beatr.helpers.PdConnector;

import com.purestyle.amvc.model.AbstractModel;

public class DrumEditorModel extends AbstractModel {
	
	public DrumEditorModel( String pdInternalInstrumentName ) {
		
		//Prepare a new .pd file that has variable names unique to this model
		Map<String, String> replacementMap = new HashMap<String, String>();
		replacementMap.put( "vol", pdInternalInstrumentName + "vol" );
		
		File newSynthFile = FileModifier.createIndividualizedFile( replacementMap, "pdfiles/ratios.pd" );
		
		FileModifier.traceFile( newSynthFile );
		
		//Tell the pure data environmen to add a new synth
		PdConnector.addPatch( newSynthFile );
	}
}