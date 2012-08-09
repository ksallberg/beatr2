package se.purestyle.beatr.model.instrumentmixer;

import se.purestyle.beatr.helpers.MetronomePlayer;

import com.purestyle.amvc.model.AbstractModel;

public class MetronomeModel extends AbstractModel {
	
	private final float maximumX = 212; //this is also available
	private float drawToX = 79.5f; //default to 125 beats per minute
	
	private final int minBmp = MetronomePlayer.MIN_BPM;
	private final int maxBmp = MetronomePlayer.MAX_BPM;
	
	private int currentBmp = 125;
	
	/**
	 * 
	 * 
	 * @param _x
	 * @param updateMetronome 	Only update the metronome itself when the slider has met it's ACTION_UP, because otherwise
	 * 							the metronome will go crazy and play super fast
	 */
	public void setDrawToX( float _x, boolean updateMetronome ) {
		
		drawToX = _x > maximumX ? maximumX : _x; //Do not let it go over max
		drawToX = drawToX < 0 ? 0 : drawToX; //Do not let it go below 0
		
		float currentPercent = drawToX / maximumX;
		
		currentBmp = Math.round( minBmp + ( maxBmp - minBmp ) * currentPercent );
		
		if( updateMetronome ) {
			
			MetronomePlayer.updateBpm( currentBmp );
		}
	}
	
	public float getDrawToX() { return drawToX; }
	
	public int currentBmp() {
		
		return currentBmp;
	}
}