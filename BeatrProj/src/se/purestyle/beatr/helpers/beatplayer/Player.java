package se.purestyle.beatr.helpers.beatplayer;

import se.purestyle.beatr.model.editors.AbstractEditorModel;

public class Player implements Runnable {

	private AbstractEditorModel model;
	
	public Player( AbstractEditorModel model ) {
		
		this.model = model;
	}
	
	@Override
	public void run() {
		
		
	}
}