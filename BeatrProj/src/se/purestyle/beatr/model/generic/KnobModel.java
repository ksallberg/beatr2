package se.purestyle.beatr.model.generic;

import android.graphics.PointF;

import com.purestyle.amvc.model.AbstractModel;

public class KnobModel extends AbstractModel {

	public static final String NEW_PERCENTAGES = "knobModelNewPercentages";
	
	private float 		currentPercent = 0.0f;
	private PointF 		pointOfCentre = new PointF( 0.0f, 0.0f );
	private PointF 		xyCoords = new PointF( 0.0f, 0.0f );
	private float		currentAngle = 0.0f;
	private String		label;
	
	public void setLabel( String label ) {
		
		this.label = label;
	}
	
	public String getLabel() {
		
		return label;
	}
	
	/**
	 * Set the current percent from outside this model, to be able to initialize the knob to other values than 0
	 * 
	 * @param currentPercent
	 */
	public void setCurrentPercent( float currentPercent ) {
		
		this.currentPercent = currentPercent;
		
		this.currentAngle = this.currentPercent * 270.0f;
	}
	
	public float getCurrentPercent() {
		
		return currentPercent;
	}
	
	public float getCurrentAngle() {
		
		return currentAngle;
	}
	
	public void setXYCoords( PointF xyCoords ) {
		
		this.xyCoords = xyCoords;
		
		//Get the angle of the mouse relative to the center point
		currentAngle = (float) Math.toDegrees( Math.atan2( xyCoords.y - pointOfCentre.y, xyCoords.x - pointOfCentre.x ) );
		
		currentAngle -= 135;
		
		if( currentAngle < 0 ) {
			
			currentAngle = 360 + currentAngle;
		}
		
		if( currentAngle > 270 && currentAngle < 320 ) {
			
			currentAngle = 270.0f;
		}
		
		if( currentAngle > 320 ) {
			
			currentAngle = 0.0f;
		}
		
		//Do calculations
		currentPercent = currentAngle / 270.0f;
		
		observers.firePropertyChange( NEW_PERCENTAGES, null, currentPercent );
	}
	
	public PointF getXYCoords() {
		
		return xyCoords;
	}
	
	public void setPointOfCentre( PointF pointOfCentre ) {
		
		this.pointOfCentre = pointOfCentre;
	}
	
	public PointF getPointOfCentre() {
		
		return pointOfCentre;
	}
}