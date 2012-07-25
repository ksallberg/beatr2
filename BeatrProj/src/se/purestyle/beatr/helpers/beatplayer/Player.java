package se.purestyle.beatr.helpers.beatplayer;

import android.util.Log;
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
 * kanske vore nått att sortera på tid och bara kolla när nästa är istället
 * [2012-07-25 12:22:59] Daniel: så nån triple klass med inbyggd komperator
 * 
 * @author kristian
 *
 */
public class Player implements Runnable, IPlayer {

	private AbstractEditorModel model;
	
	private boolean running = true;
	
	private boolean paused = true;
	
	public Player( AbstractEditorModel model ) {
		
		this.model = model;
	}
	
	@Override
	public void run() {
		
		while( running ) {
			
			while( paused ) {
				
				try {
					
					Thread.sleep( 1 );
					
				} catch( InterruptedException e ) {
					
					e.printStackTrace();
				}
				
//				Log.i( "Player.java", "Paused" );
			}
			
			//Do something
			Log.i( "Player.java", "Now I'm running" );
			
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
}