package se.purestyle.beatr.helpers.beatplayer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import android.util.Pair;

public class Recorder implements Runnable {

	public static final String TIME_UPDATED = "timeUpdated";
	
	private PropertyChangeSupport eventFirer;
	
	private long startedAt = 0;
	private boolean _running = false;
	
	private Beat beat;
	
	public Recorder() {
		
		eventFirer = new PropertyChangeSupport( this );
		beat = new Beat();
	}
	
	/**
	 * Always wait for 1 millisecond, to be able to measure time
	 */
	@Override
	public void run() {
		
		startRecording();
		
		while( _running ) {
			
			try {
				
				eventFirer.firePropertyChange( TIME_UPDATED, null, getTimeNow() );
				Thread.sleep( 1 );
				
			} catch( InterruptedException e ) {
				
				e.printStackTrace();
			}
		}
	}
	
	private void startRecording() {
		
		_running = true;
		startedAt = System.currentTimeMillis();
	}
	
	public void stopRecording() {
		
		_running = false;
		beat.setLength( getTimeNow() );
	}
	
	public void recordList( Pair<String, Float>[] messageList ) {
		
		beat.addTimeStamp( getTimeNow(), messageList );
	}
	
	public void addObserver( PropertyChangeListener listener ) {
		
		eventFirer.addPropertyChangeListener( listener );
	}
	
	public void removeObserver( PropertyChangeListener listener ) {
		
		eventFirer.removePropertyChangeListener( listener );
	}
	
	public Beat getBeat() {
		
		return beat;
	}
	
	private long getTimeNow() {
		
		return System.currentTimeMillis() - startedAt;
	}
	
	public boolean isRecording() {
		
		return _running;
	}
}