package se.purestyle.beatr.controller.generic;

import java.beans.PropertyChangeEvent;

import se.purestyle.beatr.model.generic.SliderTwoDirectionsModel;
import se.purestyle.beatr.view.generic.SliderTwoDirectionsView;

import android.content.Context;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.purestyle.amvc.controller.AbstractController;

public class SliderTwoDirectionsController extends AbstractController {
	
	private final Context context; 
	
	public static final String MAIN_VIEW = "mainView";
	
	public static final String EVENT_UP 			= "eventUp";
	public static final String EVENT_DOWN 			= "eventDown";
	
	private SliderTwoDirectionsView view;
	private SliderTwoDirectionsModel model;
	
	public SliderTwoDirectionsController( Context context ) {
		
		this.context = context;
	}
	
	@Override
	public void setup() {
		
		model = new SliderTwoDirectionsModel();
		
		view = new SliderTwoDirectionsView( context );
		view.setOnTouchListener( touchListener );
		view.addListener( this );
	}

	@Override
	public void teardown() {
		
		
	}
	
	public SliderTwoDirectionsView getView() {
		
		return view;
	}
	
	OnTouchListener touchListener = new OnTouchListener() {
		
		@Override
		public boolean onTouch( View v, MotionEvent event ) {
			
			view.setNewMousePos( new PointF( event.getX(), event.getY() ) );
			
			view.invalidate();
			
			if( event.getAction() == MotionEvent.ACTION_DOWN ) {
				
				observers.firePropertyChange( EVENT_DOWN, 1, null );
			}
			
			if( event.getAction() == MotionEvent.ACTION_UP ) {
				
				observers.firePropertyChange( EVENT_UP, 1, null );
				return false;
			}
			
			return true;
		}
	};
	
	@Override
	public void propertyChange( PropertyChangeEvent event ) {
		
		if( event.getPropertyName().equals( SliderTwoDirectionsView.NEW_PERCENTAGES ) ) {
			
			model.setXPercentage( ( ( PointF )event.getNewValue() ).x );
			model.setYPercentage( ( ( PointF )event.getNewValue() ).y );
			
			observers.firePropertyChange( event );
		}
	}
}