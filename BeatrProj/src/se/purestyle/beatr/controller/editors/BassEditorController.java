package se.purestyle.beatr.controller.editors;

import com.purestyle.amvc.controller.AbstractController;

/**
 * The corresponding models to this controller are actually not created here, 
 * because this is a shortlived controller that will spawn and be removed when
 * the user is in the controller mode.
 * 
 * Instead the corresponding model is created in the InstrumentMixerController
 * 
 * @author kristian
 *
 */
public class BassEditorController extends AbstractController {
	
	private String pdInternalInstrumentName; //Keep it here just to be able to pass it to the model
	
	public BassEditorController( String pdInternalInstrumentName ) {
		
		this.pdInternalInstrumentName = pdInternalInstrumentName;
	}
	
	@Override
	public void setup() {
		

	}

	@Override
	public void teardown() {
		
		
	}
}