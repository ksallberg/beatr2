package se.purestyle.beatr.model.editors;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import se.purestyle.beatr.helpers.FileModifier;
import se.purestyle.beatr.helpers.MetronomePlayer;
import se.purestyle.beatr.helpers.PdConnector;
import se.purestyle.beatr.helpers.beatplayer.Beat;

public class DrumEditorModel extends AbstractEditorModel implements PropertyChangeListener {
	
	private final String pdInternalInstrumentName;
	
	private final String onOffControllerName = "onoff";
	private int onoff = 0;
	
	private final String volControllerName = "vol";
	private final String pulseControllerName = "pulse";
	
	public DrumEditorModel( String pdInternalInstrumentName ) {
		
		this.pdInternalInstrumentName = pdInternalInstrumentName;
		
		//Prepare a new .pd file that has variable names unique to this model
		Map<String, String> replacementMap = new HashMap<String, String>();
		replacementMap.put( volControllerName, pdInternalInstrumentName + volControllerName );
		replacementMap.put( onOffControllerName, pdInternalInstrumentName + onOffControllerName );
		replacementMap.put( pulseControllerName, pdInternalInstrumentName + pulseControllerName );
		
		File newDrumFile = FileModifier.createIndividualizedFile( replacementMap, "pdfiles/drum.pd" );
		
		FileModifier.traceFile( newDrumFile );
		
		//Tell the pure data environmen to add a new synth
		PdConnector.addPatch( newDrumFile );
		
		//Make this class listen to changes in the metronome
		MetronomePlayer.addObserver( this );
	}
	
	/**
	 * Destroy this model!
	 */
	public void destroy() {
		
		//Remove reference to the observer
		MetronomePlayer.removeObserver( this );
	}
	
	/**
	 * Get this instrument's name
	 */
	@Override
	public String getInstrumentName() {
		
		return pdInternalInstrumentName;
	}
	
	/**
	 * For the drum, just return an empty beat
	 */
	@Override
	public Beat getBeat() {
		
		return new Beat();
	}
	
	public void setOnoff( int onoff ) {
		
		this.onoff = onoff;
		
		PdConnector.sendToPd( pdInternalInstrumentName + onOffControllerName, this.onoff );
	}

	@Override
	public void propertyChange( PropertyChangeEvent event ) {
		
		//Tick event dispatched by the metronome
		if( event.getPropertyName().equals( MetronomePlayer.TICK ) ) {
			
			PdConnector.sendToPd( pdInternalInstrumentName + pulseControllerName, 1.0f );
			PdConnector.sendToPd( pdInternalInstrumentName + pulseControllerName, 0.0f );
		}
	}
}