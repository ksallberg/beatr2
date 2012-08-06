package se.purestyle.beatr.controller.generic;

import java.beans.PropertyChangeEvent;

import se.purestyle.beatr.model.generic.KnobModel;
import se.purestyle.beatr.view.generic.KnobView;

import android.content.Context;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.purestyle.amvc.controller.AbstractController;

public class KnobController extends AbstractController {

	private KnobView view;
	private KnobModel model;
	
	private Context context;
	
	public KnobController( Context context ) {
		
		this.context = context;
	}
	
	@Override
	public void setup() {
		
		model = new KnobModel();
		model.addObserver( this );
		view = new KnobView( context );
		view.setOnTouchListener( touchListener );
		view.setModel( model );
		view.invalidate();
	}

	@Override
	public void teardown() {
		
		
	}
	
	public View getView() {
		
		return view;
	}
	
	private OnTouchListener touchListener = new OnTouchListener() {
		
		@Override
		public boolean onTouch( View v, MotionEvent event ) {
			
			if( event.getAction() == MotionEvent.ACTION_UP ) {
				
				return false;
			}
			
			model.setPointOfCentre( new PointF( (view.getWidth() / 2), view.getHeight() / 2 ) );
			model.setXYCoords( new PointF( event.getX(), event.getY() ) );
			
			//Redraw view
			view.invalidate();
			
			return true;
		}
	};
	
	/**
	 * Just bubble this event upwards so users of the knob can notice something has happened
	 */
	@Override
	public void propertyChange( PropertyChangeEvent event ) {
		
		if( event.getPropertyName().equals( KnobModel.NEW_PERCENTAGES ) ) {
			
			observers.firePropertyChange( event );
		}
	}
}