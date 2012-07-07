package se.purestyle.beatr.model.instrumentmixer;

import java.util.ArrayList;
import java.util.List;

import se.purestyle.beatr.controller.instrumentmixer.volumeobject.InstrumentController;

import com.purestyle.amvc.model.AbstractModel;

public class InstrumentHolderModel extends AbstractModel {
	
	private List<InstrumentController> instruments = new ArrayList<InstrumentController>();
	
	public void storeInstrument( InstrumentController instrument ) {
		
		instruments.add( instrument );
	}
	
	public int getNumberOfInstruments() {
		
		return instruments.size();
	}
	
	public List<InstrumentController> getInstruments() {
		
		return instruments;
	}
}