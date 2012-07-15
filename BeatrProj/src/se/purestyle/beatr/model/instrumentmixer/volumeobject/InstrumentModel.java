package se.purestyle.beatr.model.instrumentmixer.volumeobject;

import se.purestyle.beatr.model.FemaleNames;

import com.purestyle.amvc.model.AbstractModel;

public class InstrumentModel extends AbstractModel {
	
	public static final String SYNTH					= "synth";
	public static final String DRUM						= "drum";
	
	private float 	maximumX = 190; //maximumX, used to return the 
	private float 	drawToX = 190;
	private String	instrumentType;
	private String	visibleName;
	private String	pdInternalName;
	
	public InstrumentModel() {
		
		visibleName = FemaleNames.getRandomName();
	}
	
	public void setDrawToX( float _x ) {
		
		drawToX = _x > maximumX ? maximumX : _x; //Do not let it go over max
		drawToX = drawToX < 0 ? 0 : drawToX; //Do not let it go below 0
	}
	
	public float getDrawToX() { return drawToX; }
	
	public void setInstrumentType( String s ) { instrumentType = s; }
	public String getInstrumentType() { return instrumentType; }
	
	public String getVisibleName() { return visibleName; }
	
	public void		setPdInternalName( String name ) { pdInternalName = name; }
	public String 	getPdInternalName() { return pdInternalName; }
	
	public float	getPercentage() { return drawToX / maximumX; }
}