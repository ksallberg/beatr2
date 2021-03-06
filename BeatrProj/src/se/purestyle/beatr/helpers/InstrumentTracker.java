package se.purestyle.beatr.helpers;

import java.util.HashMap;
import java.util.Map;

import com.purestyle.amvc.model.AbstractModel;

public class InstrumentTracker {
	
	private static Map<String, AbstractModel> editorModels = new HashMap<String, AbstractModel>();
	
	//Normal synth
	private static volatile int SYNTH_COUNTER = 0; //This is supposed to be reachable from all synth models, to give unique names to all separate .pd files
	private static final String SYNTH_BASE_NAME = "synth-";
	
	//Drums
	private static volatile int DRUMS_COUNTER = 0; //This is supposed to be reachable from all synth models, to give unique names to all separate .pd files
	private static final String DRUMS_BASE_NAME = "drums-"; 
	
	//Bass
	private static volatile int BASS_COUNTER = 0; //This is supposed to be reachable from all synth models, to give unique names to all separate .pd files
	private static final String BASS_BASE_NAME = "bass-"; 
	
	
	/**
	 * Returns in the format NAME-NUMBER, for example "synth-3", the idea is to then add the separate
	 * setting names somewhere else, so the final result would be something like "synth-3-attack", which
	 * is a name that is unique within the pure data environment
	 * 
	 * @return
	 */
	public static synchronized String getNextSynthName() {
		
		return SYNTH_BASE_NAME + (SYNTH_COUNTER ++) + "-";
	}
	
	public static synchronized String getNextDrumName() {
		
		return DRUMS_BASE_NAME + (DRUMS_COUNTER ++) + "-";
	}
	
	public static synchronized String getNextBassName() {
		
		return BASS_BASE_NAME + (BASS_COUNTER ++) + "-";
	}
	
	public static void registerModel( String key, AbstractModel value ) {
		
		editorModels.put( key, value );
	}
	
	public static AbstractModel getModel( String key ) {
		
		return editorModels.get( key );
	}
	
	public static AbstractModel unregisterModel( String key ) {
		
		return editorModels.remove( key );
	}
}