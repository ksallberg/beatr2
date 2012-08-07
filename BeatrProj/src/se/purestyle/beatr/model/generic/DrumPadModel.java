package se.purestyle.beatr.model.generic;

//import se.purestyle.beatr.view.generic.DrumPadView;

import com.purestyle.amvc.model.AbstractModel;

public class DrumPadModel extends AbstractModel {
	
	private boolean isActive = false;
	private boolean shouldPlay = false;
	
//	private DrumPadView view;
	
	public void setIsActive( boolean isActive ) {
		
		this.isActive = isActive;
		
//		view.invalidate();
	}
	
	public boolean getIsActive() {
		
		return isActive;
	}
	
	public void toggleShouldPlay() {
		
		shouldPlay = !shouldPlay;
	}
	
	public boolean getShouldPlay() {
		
		return shouldPlay;
	}
	
	/*
	public void setView( DrumPadView view ) {
		
		this.view = view;
	}*/
}