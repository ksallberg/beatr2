package se.purestyle.beatr.model.instrumentmixer;

import com.purestyle.amvc.model.AbstractModel;

public class MasterVolumeModel extends AbstractModel {
	
	private final float maximumX = 212; //this is also available
	private float drawToX = 212;
	
	public void setDrawToX( float _x ) {
		
		drawToX = _x > maximumX ? maximumX : _x; //Do not let it go over max
		drawToX = drawToX < 0 ? 0 : drawToX; //Do not let it go below 0
	}
	
	public float getDrawToX() { return drawToX; }
	
	/**
	 * Returns mastervolume as a percentage between 0 and 1
	 * 
	 * @return
	 */
	public float getPercentage() {
		
		return drawToX / maximumX;
	}
}