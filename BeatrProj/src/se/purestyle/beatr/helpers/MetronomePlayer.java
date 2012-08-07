package se.purestyle.beatr.helpers;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Timer;
import java.util.TimerTask;

import org.puredata.android.service.R;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Use the timer to schedule something at a given bpm interval
 * 
 * @author kristian
 *
 */
public class MetronomePlayer {

	public static final int MAX_BPM 						= 200;
	public static final int MIN_BPM 						= 80;
	public static final String TICK							= "newBmpTick";
	
	private static PropertyChangeSupport propertyChange;
	private static Timer timer 								= null;
	private static MediaPlayer player 						= null;
	private static Context context;
	
	/**
	 * Only init if the player is null, do not do it if it's already initialized
	 * 
	 * @param ctx
	 */
	public static void init( Context ctx ) {
		
		if( player == null ) {
			
			propertyChange = new PropertyChangeSupport( ctx );
			
			context = ctx;
			player = MediaPlayer.create( context, R.raw.metronome );
			player.start();
		}
	}
	
	public static void updateBpm( int bpm ) {
		
		if( player == null ) {
			
			throw new RuntimeException( "MetronomePlayer: This class is not initialized, you have to call init" );
		}
		
		if( timer == null ) {
			
			createNewTimer( bpm );
		
		} else {
			
			//Remove the timer and create a new
			timer.cancel();
			timer.purge();
			timer = null;
			
			createNewTimer( bpm );
		}
	}
	
	private static void createNewTimer( int bpm ) {
		
		timer = new Timer();
		timer.scheduleAtFixedRate( new TimerTask() {
			
			@Override
			public void run() {
				
				//Play the metronome sound
				player.start();
				
				//Tell every listening object that it's time for a new metronome tick!
				propertyChange.firePropertyChange( TICK, null, TICK );
			}
			
		}, 0, (int) Math.round( 60000 / bpm ) );
	}
	
	public static void destroy() {
		
		timer.cancel();
		timer.purge();
		timer = null;
		
		player.reset();
		player = null;
	}
	
	public static void addObserver( PropertyChangeListener listener ) {
		
		propertyChange.addPropertyChangeListener( listener );
	}
	
	public static void removeObserver( PropertyChangeListener listener ) {
		
		propertyChange.removePropertyChangeListener( listener );
	}
}