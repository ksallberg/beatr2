package se.purestyle.beatr.controller.editors;

import android.util.Log;

import com.purestyle.amvc.controller.AbstractController;

public class SynthEditorController extends AbstractController {
	
	private String pdInternalInstrumentName; //Keep it here just to be able to pass it to the model
	
	public SynthEditorController( String pdInternalInstrumentName ) {
		
		this.pdInternalInstrumentName = pdInternalInstrumentName;
	}
	
	@Override
	public void setup() {
		
		Log.i( "SynthEditorController", "setup~~~~" + pdInternalInstrumentName );
	}

	@Override
	public void teardown() {
		
		
	}
}