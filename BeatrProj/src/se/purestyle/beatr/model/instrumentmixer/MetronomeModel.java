package se.purestyle.beatr.model.instrumentmixer;

import com.purestyle.amvc.model.AbstractModel;

public class MetronomeModel extends AbstractModel {
	
	private final float maximumX = 212; //this is also available
	private float drawToX = 212;
	
	private final int minBmp = 80;
	private final int maxBmp = 140;
	
	private int currentBmp = maxBmp;
	
	public void setDrawToX( float _x ) {
		
		drawToX = _x > maximumX ? maximumX : _x; //Do not let it go over max
		drawToX = drawToX < 0 ? 0 : drawToX; //Do not let it go below 0
		
		float currentPercent = drawToX / maximumX;
		
		currentBmp = Math.round( minBmp + ( maxBmp - minBmp ) * currentPercent );
	}
	
	public float getDrawToX() { return drawToX; }
	
	public int currentBmp() {
		
		return currentBmp;
	}
}