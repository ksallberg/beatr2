package se.purestyle.beatr.controller.editors;

import com.purestyle.amvc.controller.AbstractController;

public class DrumEditorController extends AbstractController {
	
	private String pdInternalInstrumentName;
	
	public DrumEditorController( String pdInternalInstrumentName ) {
		
		this.pdInternalInstrumentName = pdInternalInstrumentName;
	}
	
	@Override
	public void setup() {
		
		
	}

	@Override
	public void teardown() {
		
		
	}
}