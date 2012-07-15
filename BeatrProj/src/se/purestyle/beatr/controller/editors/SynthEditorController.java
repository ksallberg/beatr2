package se.purestyle.beatr.controller.editors;

import se.purestyle.beatr.model.editors.SynthEditorModel;

import com.purestyle.amvc.controller.AbstractController;

public class SynthEditorController extends AbstractController {
	
	private String pdInternalInstrumentName; //Keep it here just to be able to pass it to the model
	
	public SynthEditorController( String pdInternalInstrumentName ) {
		
		this.pdInternalInstrumentName = pdInternalInstrumentName;
	}
	
	@Override
	public void setup() {
		
		//Create the model and add it
		SynthEditorModel model = new SynthEditorModel( pdInternalInstrumentName );
		addModel( MODEL, model );
	}

	@Override
	public void teardown() {
		
		
	}
}