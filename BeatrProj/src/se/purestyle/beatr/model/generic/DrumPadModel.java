package se.purestyle.beatr.model.generic;

import com.purestyle.amvc.model.AbstractModel;

public class DrumPadModel extends AbstractModel {
	
	private boolean isActive = false;
	private boolean shouldPlay = false;
	
	public void setIsActive( boolean isActive ) {
		
		this.isActive = isActive;
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
}