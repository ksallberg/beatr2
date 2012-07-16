package se.purestyle.beatr.model.generic;

import android.util.Log;

import com.purestyle.amvc.model.AbstractModel;

/**
 * Basic model for holding x and y percentage of the twoDirectionsSlider
 * 
 * @author kristian
 *
 */
public class SliderTwoDirectionsModel extends AbstractModel {

	private float xPercentage;
	private float yPercentage;
	
	public void setXPercentage( float xPercentage ) {
		
		this.xPercentage = xPercentage;
		
		Log.i( "SliderTwoModel, xPercentage: ", "" + xPercentage );
	}
	
	public float getXPercentage() {
		
		return xPercentage;
	}
	
	public void setYPercentage( float yPercentage ) {
		
		this.yPercentage = yPercentage;
		
		Log.i( "SliderTwoModel, yPercentage: ", "" + yPercentage );
	}
	
	public float getYPercentage() {
	
		return yPercentage;
	}
}