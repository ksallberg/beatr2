package se.purestyle.beatr.view.generic;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

/**
 * 
 * 
 * @author kristian
 *
 */
public class SliderTwoDirectionsView extends View {

	public static final String NEW_PERCENTAGES = "newPercentages";
	
	private PointF mousePos = null;
	
	private final int leftMargin = 10;
	private final int topMargin = 10;
	
	private final float circleSize = 8.0f;
	
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport( this );
	
	public SliderTwoDirectionsView( Context context ) {
		
		super( context );
		
		LayoutParams params = new LayoutParams( LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT );
		
		setLayoutParams( params );
	}
	
	/**
	 * Enable setting of the mousePos from some other class, for instance the controller
	 * 
	 * @param newPos
	 */
	public void setNewMousePos( PointF newPos ) {
		
		//Guard the point from becoming to large to the right side
		if( newPos.x > getWidth() - circleSize - leftMargin ) {
			
			newPos.set( getWidth() - circleSize - leftMargin, newPos.y );
		}
		
		//Guard the point from becoming to small to the left side
		if( newPos.x < leftMargin + circleSize ) {
			
			newPos.set( leftMargin + circleSize, newPos.y );
		}
		
		//Guard the point from becoming to large in the y axis
		if( newPos.y > getHeight() - circleSize - topMargin ) {
			
			newPos.set( newPos.x, getHeight() - circleSize - topMargin );
		}
		
		//Guard the point from becoming to small in the y axis
		if( newPos.y < topMargin + circleSize ) {
			
			newPos.set( newPos.x , topMargin + circleSize );
		}
		
		mousePos = newPos;
		
		float xPcent = ( mousePos.x - circleSize - leftMargin ) / ( getWidth() - leftMargin * 2 - circleSize * 2 );
		float yPcent = ( mousePos.y - circleSize - topMargin ) / ( getHeight() - topMargin * 2 - circleSize * 2 );
		
		//Tell the world this property has changed
		PointF newPercentages = new PointF( xPcent, yPcent );
		propertyChangeSupport.fireIndexedPropertyChange( NEW_PERCENTAGES, 0, null, newPercentages );
	}
	
	@Override
	public void onDraw( Canvas can ) {
		
		//if mousePos is null, then create one in the middle of this component
		if( mousePos == null ) {
			
			mousePos = new PointF( getWidth() / 2, getHeight() / 2 );
		}
		
		Paint newPaint = new Paint();
		newPaint.setColor( Color.parseColor( "#434343" ) );
		newPaint.setAntiAlias( true );
		
		can.drawRoundRect( new RectF( leftMargin, topMargin, getWidth() - leftMargin, getHeight() - topMargin ), 10.0f, 10.0f, newPaint );
		
		Paint circlePaint = new Paint();
		circlePaint.setColor( Color.parseColor( "#00ffe4" ) );
		circlePaint.setAntiAlias( true );
		circlePaint.setAlpha( 200 );
		
		//Draw horizontal line
		can.drawLine( leftMargin, mousePos.y, getWidth() - leftMargin, mousePos.y, circlePaint );
		
		//Draw vertical line
		can.drawLine( mousePos.x, topMargin, mousePos.x, getHeight() - topMargin, circlePaint );
		
		can.drawCircle( mousePos.x, mousePos.y, circleSize, circlePaint );
	}
	
	public void addListener( PropertyChangeListener listener ) {
		
		propertyChangeSupport.addPropertyChangeListener( listener );
	}
}