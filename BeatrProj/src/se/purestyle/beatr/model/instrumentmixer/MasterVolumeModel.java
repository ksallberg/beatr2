package se.purestyle.beatr.model.instrumentmixer;

import com.purestyle.amvc.model.AbstractModel;

public class MasterVolumeModel extends AbstractModel {
	
	private final float maximumX = 212; //this is also available
	private float drawToX = 100;
	
	public void setDrawToX( float _x ) { drawToX = _x; }
	public float getDrawToX() { return drawToX; }
}