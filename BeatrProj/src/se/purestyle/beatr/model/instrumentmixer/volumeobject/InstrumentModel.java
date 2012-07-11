package se.purestyle.beatr.model.instrumentmixer.volumeobject;

import se.purestyle.beatr.BeatrActivity;
import se.purestyle.beatr.model.FemaleNames;

import com.purestyle.amvc.model.AbstractModel;

public class InstrumentModel extends AbstractModel {
	
	public static final String SYNTH					= "synth";
	public static final String DRUM						= "drum";
	
	private float 	drawToX = 190;
	private String	instrumentType;
	private String	name;
	
	public InstrumentModel() {
		
		name = FemaleNames.getRandomName();
	}
	
	public void setDrawToX( float _x ) { drawToX = _x; }
	public float getDrawToX() { return drawToX; }
	
	public void setInstrumentType( String s ) { 
		
		instrumentType = s;
		
		if( s.equals( DRUM ) ) {
			
			BeatrActivity.addDrum();
		
		} else if ( s.equals( SYNTH ) ) {
			
			BeatrActivity.addSynth();
		}
	}
	
	public String getInstrumentType() { return instrumentType; }
	
	public String getName() { return name; }
}