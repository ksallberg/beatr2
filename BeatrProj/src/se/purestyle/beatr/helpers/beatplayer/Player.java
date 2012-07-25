package se.purestyle.beatr.helpers.beatplayer;

import android.util.Log;
import android.util.Pair;
import se.purestyle.beatr.helpers.PdConnector;
import se.purestyle.beatr.model.editors.AbstractEditorModel;

/**
 * Tells the synth to actually play something.
 * 
 * It loops between 0ms and a Beat objects length
 * 
 * At each ms, hopefully(?), it will ask the Beat object what to play, and if something is there
 * it will play it
 * 
 * TODO: Consider a tip from a friend on how to do this:
 * kanske vore nŒtt att sortera pŒ tid och bara kolla nŠr nŠsta Šr istŠllet
 * [2012-07-25 12:22:59] Daniel: sŒ nŒn triple klass med inbyggd komperator
 * 
 * @author kristian
 *
 */
public class Player implements Runnable, IPlayer {

	private AbstractEditorModel model;
	
	private boolean running = true;
	
	private boolean paused = true;
	
	private long currentlyAt = 0;
	
	public Player( AbstractEditorModel model ) {
		
		this.model = model;
	}
	
	@Override
	public void run() {
		
		while( running ) {
			
			while( paused ) {
				
				try {
					
					currentlyAt = 0; //When this is paused, reset currentlyAt
					Thread.sleep( 1 );
					
				} catch( InterruptedException e ) {
					
					e.printStackTrace();
				}
			}
			
			//This is what happens when the 
			currentlyAt ++;
			
			//If there's a recorded beat
			if( model.getBeat() != null ) {
				
				//If we have gone further back in the Beat than it is long, reset currentlyAt
				if( currentlyAt > model.getBeat().getLength() ) {
					
					currentlyAt = 0;
				}
				
				//If there's something to be played at this point in time
				if( model.getBeat().getCommand( currentlyAt ) != null ) {
					
					Pair<String,Float>[] messages = model.getBeat().getCommand( currentlyAt );
					
					//Loop through all messages
					for( Pair<String,Float> p : messages ) {
						
						PdConnector.sendToPd( p.first, p.second );
					}
				}
			}
			
			try {
				
				Thread.sleep( 1 );
				
			} catch( InterruptedException e ) {
				
				e.printStackTrace();
			}
		}
	}

	@Override
	public void pause() {
		
		Log.i( "BeatPlayer.java", "pause" );
		
		paused = true;
	}

	@Override
	public void play() {
		
		Log.i( "BeatPlayer.java", "play" );
		
		paused = false;
	}
	
	//TODO: Find a better way to mute all instruments
	public void setInstrumentOff() {
		
		PdConnector.sendToPd( model.getInstrumentName() + "onoff", 0 );
	}
}