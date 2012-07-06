package se.purestyle.beatr.controller.instrumentmixer;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import se.purestyle.beatr.controller.instrumentmixer.volumeobject.InstrumentController;
import se.purestyle.beatr.model.instrumentmixer.InstrumentHolderModel;

import android.util.Log;

import com.purestyle.amvc.controller.AbstractController;
import com.purestyle.amvc.view.IView;

public class InstrumentHolderController extends AbstractController {

	public static String MODEL = "model";
	public static String NEW_INSTRUMENT_ADDED = "newInstrumentAdded";
	
	private List<InstrumentController> instruments = new ArrayList<InstrumentController>();
	
	private InstrumentHolderModel model;
	
	@Override
	public void setup() {
		
		Log.i( "InstrumentHolderController", "INIT!!!" );
		
		//addModel( MODEL, new InstrumentHolderModel() );
		model = new InstrumentHolderModel();
	}

	@Override
	public void teardown() {
		
		
	}
	
	public void addNewInstrument() {
		
		InstrumentController newInstrument = new InstrumentController();
		newInstrument.setup();
		
		instruments.add( newInstrument );
		
		//TODO: Is this the best way to notify the model that a new instrument has been created?
		model.createInstrument();
		
		observers.firePropertyChange( new PropertyChangeEvent( this, NEW_INSTRUMENT_ADDED, null, newInstrument.getView() ) );
	}
}