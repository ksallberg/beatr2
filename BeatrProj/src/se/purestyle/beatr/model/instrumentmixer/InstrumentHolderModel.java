package se.purestyle.beatr.model.instrumentmixer;

import java.util.ArrayList;
import java.util.List;

import se.purestyle.beatr.view.instrumentmixer.volumeobject.IInstrumentView;

import android.util.Log;

import com.purestyle.amvc.model.AbstractModel;

public class InstrumentHolderModel extends AbstractModel {
	
	private List<Integer> instruments = new ArrayList<Integer>();
	
	private int pages = 1;
	private int currentPage = pages;
	
	public void createInstrument() {
		
		instruments.add( 3 );
		Log.i( "InstrumentHolderModel", "Model: create view!" );
	}
	
	public void createPage() {
		
		pages ++;
	}
	
	public void removePage() {
		
		pages --;
	}
	
	public int getNumberOfInstruments() {
		
		return instruments.size();
	}
}