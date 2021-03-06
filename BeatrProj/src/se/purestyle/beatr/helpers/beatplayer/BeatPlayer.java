package se.purestyle.beatr.helpers.beatplayer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import se.purestyle.beatr.model.editors.AbstractEditorModel;
import se.purestyle.beatr.model.editors.DrumEditorModel;

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
	
	private Map<String, Player> threads; //The threads that are going to send messages to the synth in a parallel manner
	private Map<String, DrumEditorModel> drums;
	
	//Private constructor, this is not possible to call from outside of this class
	private BeatPlayer() {
		
		//Create the "thread pool"
		threads = new HashMap<String, Player>();
		drums	= new HashMap<String, DrumEditorModel>();
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
		player.pause();
		
		threads.put( instrumentName, player );
	}
	
	/**
	 * Separate way to add the drums, because they are not going to be recorded and run in separate threads
	 * 
	 * @param drum
	 */
	public void addDrum( String instrumentName, DrumEditorModel drum ) {
		
		drums.put( instrumentName, drum );
	}
	
	/**
	 * Rewind a given instrument (assign 0 to it's currentlyAt)
	 * 
	 * @param message
	 */
	public void rewind( String message ) {
		
		if( isDrum( message ) ) {
			
			//Rewind drum
			drums.get( message ).rewind();
		
		} else {
			
			//Rewind Player (of type runnable)
			threads.get( message ).rewind();
		}
	}
	
	/**
	 * Returns yes if the message starts with "drum"
	 * 
	 * @param message
	 * @return
	 */
	private boolean isDrum( String message ) {
		
		return message.substring( 0, 4 ).equals( "drum" );
	}
	
	/**
	 * This method accepts either BeatPlayer.ALL, BeatPlayer.NONE or a single instrument to be played
	 * 
	 * @param message ALL, NONE or an instrument name
	 */
	public void setMode( String message ) {
		
		//Turn all instruments on
		if( message.equals( ALL ) ) {
			
			//Turn all recorded instruments on
			Iterator<Entry<String, Player>> it = threads.entrySet().iterator();
			
			while( it.hasNext() ) {
				
				it.next().getValue().play();
			}
			
		//	Turn all drums on
			Iterator<Entry<String, DrumEditorModel>> itDrums = drums.entrySet().iterator();
			
			while( itDrums.hasNext() ) {
				
				itDrums.next().getValue().setOnoff( 1 );
			}
			
		//Turn all instruments off
		} else if ( message.equals( NONE ) ) {
			
			//Turn all recorded instruments on
			Iterator<Entry<String, Player>> it = threads.entrySet().iterator();
			
			while( it.hasNext() ) {
				
				Entry<String, Player> elem = it.next(); 
				
				elem.getValue().pause();
				elem.getValue().setInstrumentOff(); //Just a quick fix to mute all instruments before 
													//some instrument enters its editor mode
			}
			
//			Turn all drums on
			Iterator<Entry<String, DrumEditorModel>> itDrums = drums.entrySet().iterator();
			
			while( itDrums.hasNext() ) {
				
				itDrums.next().getValue().setOnoff( 0 );
			}
		
		//Turn a single instrument on
		} else {
			
			//This only works for drums, as of now, they are the only instrument that should sound
			//when editing it. No need to make the synth and bass sound when in the edit mode
			
			if( isDrum( message ) ) {
				
				if( drums.get( message ) != null ) {
					
					drums.get( message ).setOnoff( 1 );
				} 
				
			} else {
				
				if( threads.get( message ) != null ) {
					
					threads.get( message ).play();
				}
			}
		}
	}
}