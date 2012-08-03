package se.purestyle.beatr.model.instrumentmixer;

import se.purestyle.beatr.helpers.MetronomePlayer;

import com.purestyle.amvc.model.AbstractModel;

public class MetronomeModel extends AbstractModel {
	
	private final float maximumX = 212; //this is also available
	private float drawToX = 212;
	
	private final int minBmp = MetronomePlayer.MIN_BPM;
	private final int maxBmp = MetronomePlayer.MAX_BPM;
	
	private int currentBmp = maxBmp;
	
	public void setDrawToX( float _x ) {
		
		drawToX = _x > maximumX ? maximumX : _x; //Do not let it go over max
		drawToX = drawToX < 0 ? 0 : drawToX; //Do not let it go below 0
		
		float currentPercent = drawToX / maximumX;
		
		currentBmp = Math.round( minBmp + ( maxBmp - minBmp ) * currentPercent );
		
		MetronomePlayer.updateBpm( currentBmp );
	}
	
	public float getDrawToX() { return drawToX; }
	
	public int currentBmp() {
		
		return currentBmp;
	}
}