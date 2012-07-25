package se.purestyle.beatr.model.editors;

import se.purestyle.beatr.helpers.beatplayer.Beat;

import com.purestyle.amvc.model.AbstractModel;

public class AbstractEditorModel extends AbstractModel {

	public Beat getBeat() {
		
		throw new RuntimeException( "AbstractEditorModel: This is an abstract class, do not call this method!" );
	}
	
	public String getInstrumentName() {
		
		throw new RuntimeException( "AbstractEditorModel: This is an abstract class, do not call this method!" );
	}
}