package se.purestyle.beatr.helpers.beatplayer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import se.purestyle.beatr.model.editors.AbstractEditorModel;

/**
 * This is the class that automatically informs the synth what to play when in the mixer view
 * 		Then it plays all the instruments at the same time in parallel
 * 
 * 
 * Also, it can play instruments when in the editor
 * 		Then it pauses everything except the current instrument so it's easy to hear what happens
 * 
 * @author kristian
 *
 */
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
	public static final String NONE = "none";
	
	private Map<String, Thread> threads; //The threads that are going to send messages to the synth in a parallel manner
	
	//Private constructor, this is not possible to call from outside of this class
	private BeatPlayer() {
		
		//Create the "thread pool"
		threads = new HashMap<String, Thread>();
	}
	
	/**
	 * Used to connect an instruments Beat property with this class
	 * 
	 * @param instrumentName The name of the instrument, so that it can be started separately
	 * @param model The model, so this class can access the messages to send to the synth
	 */
	public void addEditorModel( String instrumentName, AbstractEditorModel model ) {
		
		Player player = new Player( model );
		Thread th = new Thread( player );
		th.start();
		
		//Tell the thread to wait
		try {
			
			th.wait();
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		threads.put( instrumentName, th );
	}
	
	/**
	 * This method accepts either BeatPlayer.ALL, BeatPlayer.NONE or a single instrument to be played
	 * 
	 * @param message ALL, NONE or an instrument name
	 */
	public void setMode( String message ) {
		
		//Turn all instruments on
		if( message.equals( ALL ) ) {
			
			Iterator<Entry<String, Thread>> it = threads.entrySet().iterator();
			
			while( it.hasNext() ) {
				
				it.next().getValue().notify();
				it.remove();
			}
			
		//Turn all instruments off
		} else if ( message.equals( NONE ) ) {
			
			Iterator<Entry<String, Thread>> it = threads.entrySet().iterator();
			
			while( it.hasNext() ) {
				
				//Try to make it wait
				try {
					
					it.next().getValue().wait();
					
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
				
				it.remove();
			}
		
		//Turn a single instrument on
		} else {
			
			if( threads.get( message ) != null ) {
				
				threads.get( message ).notify();
			}
		}
	}
}