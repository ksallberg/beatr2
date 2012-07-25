package se.purestyle.beatr.helpers.beatplayer;

import se.purestyle.beatr.model.editors.AbstractEditorModel;

/**
 * Tells the synth to actually play something.
 * 
 * It loops between 0ms and a Beat objects length
 * 
 * At each ms, hopefully(?), it will ask the Beat object what to play, and if something is there
 * it will play it
 * 
 * TODO: Consider a tip from a friend on how to do this:
 * kanske vore n�tt att sortera p� tid och bara kolla n�r n�sta �r ist�llet
 * [2012-07-25 12:22:59] Daniel: s� n�n triple klass med inbyggd komperator
 * 
 * @author kristian
 *
 */
public class Player implements Runnable {

	private AbstractEditorModel model;
	
	private boolean running = true;
	
	public Player( AbstractEditorModel model ) {
		
		this.model = model;
	}
	
	@Override
	public void run() {
		
		while( running ) {
			
			
		}
	}
}