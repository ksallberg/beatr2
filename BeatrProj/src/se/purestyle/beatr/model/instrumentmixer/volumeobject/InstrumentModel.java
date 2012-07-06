package se.purestyle.beatr.model.instrumentmixer.volumeobject;

import com.purestyle.amvc.model.AbstractModel;

public class InstrumentModel extends AbstractModel {
	
	private float drawToX = 190;
	
	public void setDrawToX( float _x ) { drawToX = _x; }
	public float getDrawToX() { return drawToX; }
}