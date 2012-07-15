package se.purestyle.beatr.controller.editors;

import se.purestyle.beatr.model.editors.BassEditorModel;

import com.purestyle.amvc.controller.AbstractController;

public class BassEditorController extends AbstractController {
	
	private String pdInternalInstrumentName; //Keep it here just to be able to pass it to the model
	
	public BassEditorController( String pdInternalInstrumentName ) {
		
		this.pdInternalInstrumentName = pdInternalInstrumentName;
	}
	
	@Override
	public void setup() {
		
		//Create the model and add it
		BassEditorModel model = new BassEditorModel( pdInternalInstrumentName );
		addModel( MODEL, model );
	}

	@Override
	public void teardown() {
		
		
	}
}