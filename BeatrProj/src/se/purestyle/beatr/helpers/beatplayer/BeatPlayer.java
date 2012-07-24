package se.purestyle.beatr.helpers.beatplayer;

public class BeatPlayer {
	
	//Singleton object
	private static BeatPlayer instance = null;
	private long currentlyAt = 0; //The time this player is currently playing
	
	public static BeatPlayer getInstance() {
		
		//If the beatPlayer is null, then create an instance
		if( instance == null ) {
			
			instance = new BeatPlayer();
		}
		
		return instance;
	}
	
//	Fields in this class:
	
	public static final String ALL = "all";
	
	private Thread runner;
	
	//Private constructor, this is not possible to call from outside of this class
	private BeatPlayer() {
		
		runner = new Thread( new Runner() );
		runner.start();
	}
	
	public void addEvent(  ) {
		
		
	}
	
	/**
	 * This method accepts either BeatPlayer.ALL or a single 
	 * 
	 */
	public void setMode() {
		
		
	}
	
	class Runner implements Runnable {

		@Override
		public void run() {
			
			
		}
	}
}