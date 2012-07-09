package se.purestyle.beatr.controller.instrumentmixer;

import java.beans.PropertyChangeEvent;

import se.purestyle.beatr.controller.instrumentmixer.volumeobject.InstrumentController;
import se.purestyle.beatr.model.instrumentmixer.InstrumentHolderModel;

import com.purestyle.amvc.controller.AbstractController;

public class InstrumentHolderController extends AbstractController {

	public static String MODEL = "model";
	public static String NEW_INSTRUMENT_ADDED = "newInstrumentAdded";
	
	private InstrumentHolderModel model;
	
	@Override
	public void setup() {
		
		//addModel( MODEL, new InstrumentHolderModel() );
		model = new InstrumentHolderModel();
		
		addModel( MODEL, model );
	}

	@Override
	public void teardown() {
		
		
	}
	
	public void addNewInstrument( String instrumentType ) {
		
		InstrumentController newInstrument = new InstrumentController( instrumentType );
		newInstrument.setup();
		
		model.storeInstrument( newInstrument );
		
		observers.firePropertyChange( new PropertyChangeEvent( this, NEW_INSTRUMENT_ADDED, null, newInstrument.getView() ) );
	}
}