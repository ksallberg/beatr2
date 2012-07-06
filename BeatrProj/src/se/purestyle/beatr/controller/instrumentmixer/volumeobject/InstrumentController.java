package se.purestyle.beatr.controller.instrumentmixer.volumeobject;

import se.purestyle.beatr.ResourceManager;
import se.purestyle.beatr.model.instrumentmixer.volumeobject.InstrumentModel;
import se.purestyle.beatr.view.instrumentmixer.volumeobject.InstrumentView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

import com.purestyle.amvc.controller.AbstractController;

public class InstrumentController extends AbstractController {

	public static final String VOLUME_DRAGGER_MODEL		= "volumeDraggerModel";
	
	private InstrumentView view;
	private InstrumentModel model;
	
	@Override
	public void setup() {
		
		//Create and add model
		model = new InstrumentModel();
		
		//Create view (android View not IView)
		view = new InstrumentView( ResourceManager.getContext() );
		view.getVolumeDraggerView().setModel( model );
		view.getVolumeDraggerView().init();
		
		view.findViewWithTag( InstrumentView.EDIT_BUTTON ).setOnClickListener( editHandler );
		view.findViewWithTag( InstrumentView.REWIND_BUTTON ).setOnClickListener( rewindHandler );
		view.findViewWithTag( InstrumentView.VOLUME_DRAGGER ).setOnTouchListener( draggerHandler );
	}

	@Override
	public void teardown() {
		
		
	}
	
	public InstrumentView getView() {
		
		return view;
	}
	
	OnTouchListener draggerHandler = new OnTouchListener() {
		
		@Override
		public boolean onTouch( View v, MotionEvent event ) {
			
			model.setDrawToX( event.getRawX() - v.getLeft() );
			
			if ( event.getAction() == MotionEvent.ACTION_UP ) {
				
				return false;
			}
			
			v.postInvalidate();
			
			return true;
		}
	};
	
	OnClickListener editHandler = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			Log.i( "InstrumentController", "Edit!" );
		}
	};
	
	OnClickListener rewindHandler = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			Log.i( "InstrumentController", "Rewind!" );
		}
	};
}