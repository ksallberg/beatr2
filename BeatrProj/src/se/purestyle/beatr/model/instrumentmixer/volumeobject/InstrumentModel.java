package se.purestyle.beatr.model.instrumentmixer.volumeobject;

import com.purestyle.amvc.model.AbstractModel;

public class InstrumentModel extends AbstractModel {
	
	public static final String SYNTH					= "synth";
	public static final String DRUM						= "drum";
	
	private float 	drawToX = 190;
	private String	instrumentType;
	
	public void setDrawToX( float _x ) { drawToX = _x; }
	public float getDrawToX() { return drawToX; }
	
	public void setInstrumentType( String s ) { instrumentType = s; }
	public String getInstrumentType() { return instrumentType; }
}