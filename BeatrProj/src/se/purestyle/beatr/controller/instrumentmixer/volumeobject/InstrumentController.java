package se.purestyle.beatr.controller.instrumentmixer.volumeobject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import se.purestyle.beatr.helpers.PdConnector;
import se.purestyle.beatr.model.instrumentmixer.volumeobject.InstrumentModel;
import se.purestyle.beatr.view.instrumentmixer.volumeobject.InstrumentView;
import android.content.Context;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

import com.purestyle.amvc.controller.AbstractController;

public class InstrumentController extends AbstractController {

	public static final String MODEL					= "model";
	public static final String VOLUME_DRAGGER_MODEL		= "volumeDraggerModel";
	
	public static final String EDIT_EVENT				= "editEvent";
	public static final String REWIND_EVENT				= "rewindEvent";
	
	private InstrumentView view;
	private InstrumentModel model;

	private PropertyChangeSupport eventFirer = new PropertyChangeSupport( this );
	private String instrumentType;
	
	private Context context;
	
	public InstrumentController( String instrumentType, Context context ) {
		
		this.instrumentType = instrumentType;
		this.context = context;
	}
	
	@Override
	public void setup() {
		
		//Create and add model
		model = new InstrumentModel();
		model.setInstrumentType( instrumentType );
		addModel( MODEL, model );
		
		//Create view (android View not IView)
		view = new InstrumentView( context );
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
			
			//Set the local volume
			PdConnector.sendToPd( model.getPdInternalName() + "vol", model.getPercentage() );
			
			v.postInvalidate();
			
			return true;
		}
	};
	
	public void addEditEventListener( PropertyChangeListener listener ) {
		
		eventFirer.addPropertyChangeListener( listener );
	}
	
	OnClickListener editHandler = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			Pair<String, String> infoStorer = new Pair<String, String>( instrumentType, model.getPdInternalName() ); //Where first = instrumentType, second = instrumentName
			
			eventFirer.firePropertyChange( EDIT_EVENT, null, infoStorer );
		}
	};
	
	OnClickListener rewindHandler = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			Pair<String, String> infoStorer = new Pair<String, String>( instrumentType, model.getPdInternalName() ); //Where first = instrumentType, second = instrumentName
			
			eventFirer.firePropertyChange( REWIND_EVENT, null, infoStorer );
		}
	};
}