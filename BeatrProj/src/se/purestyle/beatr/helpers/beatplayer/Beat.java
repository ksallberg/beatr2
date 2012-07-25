package se.purestyle.beatr.helpers.beatplayer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import android.util.Pair;

/**
 * Data structure to keep what happens  
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
	
	/**
	 * 
	 * 
	 * @param key
	 * @return
	 */
	public Pair<String, Float>[] getCommand( long key ) {
		
		return record.get( key );
	}
	
	/**
	 * Iterate through the data structure and return a long string that states all keys and values
	 */
	@Override
	public String toString() {
		
		StringBuilder str = new StringBuilder();
		
		Iterator<Entry<Long, Pair<String, Float>[]>> it = record.entrySet().iterator();
		
		while( it.hasNext() ) {
			
			Map.Entry<Long, Pair<String,Float>[]> entrySet = it.next();
			
			str.append( "KEY: " + entrySet.getKey() );
			str.append( "VAL: " + entrySet.getValue() );
		}
		
		return str.toString();
	}
}