package se.purestyle.beatr.helpers.beatplayer;

import java.util.HashMap;
import java.util.Map;

import android.util.Pair;

/**
 * The beat class is supposed to contain information about what settings an 
 * instrument has at what points in time and also how long the clip is 
 * 
 * The point is to be able to store commands to the software synth and when they are supposed to be playing
 * 
 * @author kristian
 *
 */
public class Beat {
	
	private long length; //length of this beat in ms
	
	private Map<Long, Pair<String,Float>[]> record = new HashMap<Long, Pair<String,Float>[]>();
	
	public void addTimeStamp( long time, Pair<String, Float>[] commandList ) {
		
		record.put( time, commandList );
	}
	
	public void setLength( long length ) {
		
		this.length = length;
	}
	
	public long getLength() {
		
		return length;
	}
	
	public Pair<String, Float>[] getCommand( long key ) {
		
		return record.get( key );
	}
}