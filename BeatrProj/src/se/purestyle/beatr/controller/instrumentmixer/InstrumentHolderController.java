package se.purestyle.beatr.controller.instrumentmixer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import se.purestyle.beatr.controller.instrumentmixer.volumeobject.InstrumentController;
import se.purestyle.beatr.model.instrumentmixer.InstrumentHolderModel;

import android.content.Context;

import com.purestyle.amvc.controller.AbstractController;

public class InstrumentHolderController extends AbstractController {
	
	public static String MODEL = "model";
	public static String NEW_INSTRUMENT_ADDED = "newInstrumentAdded";
	
	private InstrumentHolderModel model;
	
	private PropertyChangeSupport eventFirer = new PropertyChangeSupport( this );
	
	private Context context;
	
	public InstrumentHolderController( Context context ) {
		
		this.context = context;
	}
	
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
		
		InstrumentController newInstrument = new InstrumentController( instrumentType, context );
		newInstrument.addEditEventListener( this );
		newInstrument.setup();
		
		model.storeInstrument( newInstrument );
		
		observers.firePropertyChange( new PropertyChangeEvent( newInstrument, NEW_INSTRUMENT_ADDED, null, newInstrument.getView() ) );
	}
	
	public void addListener( PropertyChangeListener listener ) {
		
		eventFirer.addPropertyChangeListener( listener );
	}
	
	@Override
	public void propertyChange( PropertyChangeEvent event ) {
		
		if( event.getPropertyName().equals( InstrumentController.EDIT_EVENT ) ) {
			
			eventFirer.firePropertyChange( InstrumentController.EDIT_EVENT, null, event.getNewValue() );
		}
	}
}