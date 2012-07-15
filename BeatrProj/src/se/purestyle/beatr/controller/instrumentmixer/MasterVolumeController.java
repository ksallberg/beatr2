package se.purestyle.beatr.controller.instrumentmixer;

import se.purestyle.beatr.helpers.PdConnector;
import se.purestyle.beatr.model.instrumentmixer.MasterVolumeModel;
import se.purestyle.beatr.view.instrumentmixer.MasterVolumeView;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.purestyle.amvc.controller.AbstractController;

public class MasterVolumeController extends AbstractController {

	private MasterVolumeView view;
	private MasterVolumeModel model;
	
	@Override
	public void setup() {
		
		model = new MasterVolumeModel();
	}

	@Override
	public void teardown() {
		

	}
	
	public void setView( MasterVolumeView view ) {
		
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
			( ( MasterVolumeModel ) model ).setDrawToX( event.getRawX() - v.getLeft() );
			
			if ( event.getAction() == MotionEvent.ACTION_UP ) {
				
				return false;
			}
			
			v.postInvalidate();
			
			//The only thing that needs to be done here, is to change the masterVol, something that is in several .pd files
			PdConnector.sendToPd( "masterVol", ( ( MasterVolumeModel ) model ).getPercentage() );
			
			return true;
		}
	};
}