package se.purestyle.beatr.controller.instrumentmixer;

import se.purestyle.beatr.model.instrumentmixer.MetronomeModel;
import se.purestyle.beatr.view.instrumentmixer.MetronomeView;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.purestyle.amvc.controller.AbstractController;

public class MetronomeController extends AbstractController {

	private MetronomeView view;
	private MetronomeModel model;
	
	@Override
	public void setup() {
		
		model = new MetronomeModel();
	}

	@Override
	public void teardown() {
		

	}
	
	public void setView( MetronomeView view ) {
		
		this.view = view;
		view.setModel( model );
		view.setOnTouchListener( draggerHandler );
	}
	
	public View getView() {
		
		return view;
	}
	
	OnTouchListener draggerHandler = new OnTouchListener() {
		
		@Override
		public boolean onTouch( View v, MotionEvent event ) {
			
			//Redraw view
			( ( MetronomeModel ) model ).setDrawToX( event.getRawX() - v.getLeft() );
			
			if ( event.getAction() == MotionEvent.ACTION_UP ) {
				
				return false;
			}
			
			v.postInvalidate();
			
			return true;
		}
	};
}