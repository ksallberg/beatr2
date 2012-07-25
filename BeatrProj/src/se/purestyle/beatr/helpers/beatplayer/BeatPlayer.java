package se.purestyle.beatr.helpers.beatplayer;

import java.util.HashMap;
import java.util.Map;

import se.purestyle.beatr.model.editors.AbstractEditorModel;

public class BeatPlayer {
	
	//Singleton object
	private static BeatPlayer instance = null;
	
	public static BeatPlayer getInstance() {
		
		//If the beatPlayer is null, then create an instance
		if( instance == null ) {
			
			instance = new BeatPlayer();
		}
		
		return instance;
	}
	
//	Fields in this class:
	
	public static final String ALL = "all";
	
	private Map<String, Thread> threads; //The threads that are going to send messages to the synth in a parallel manner
	
	//Private constructor, this is not possible to call from outside of this class
	private BeatPlayer() {
		
		threads = new HashMap<String, Thread>();
	}
	
	public void addEditorModel( String instrumentName, AbstractEditorModel model ) {
		
		Player player = new Player( model );
		Thread th = new Thread( player );
		
		threads.put( instrumentName, th );
	}
	
	/**
	 * This method accepts either BeatPlayer.ALL or a single 
	 * 
	 */
	public void setMode() {
		
		
	}
}