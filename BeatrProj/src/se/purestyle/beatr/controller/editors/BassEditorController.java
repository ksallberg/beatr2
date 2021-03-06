package se.purestyle.beatr.controller.editors;

import java.beans.PropertyChangeEvent;

import se.purestyle.beatr.controller.generic.KnobController;
import se.purestyle.beatr.controller.generic.SliderTwoDirectionsController;
import se.purestyle.beatr.helpers.InstrumentTracker;
import se.purestyle.beatr.helpers.beatplayer.Beat;
import se.purestyle.beatr.helpers.beatplayer.Recorder;
import se.purestyle.beatr.model.editors.BassEditorModel;
import se.purestyle.beatr.model.generic.KnobModel;
import se.purestyle.beatr.view.editors.BassEditorView;
import se.purestyle.beatr.view.generic.SliderTwoDirectionsView;

import android.app.Activity;
import android.content.Context;
import android.graphics.PointF;
import android.view.View;
import android.view.View.OnClickListener;

import com.purestyle.amvc.controller.AbstractController;

public class BassEditorController extends AbstractController {
	
	private String pdInternalInstrumentName; //Keep it here just to be able to pass it to the model
	
	private BassEditorModel model;
	private BassEditorView view;
	
	//Subcontroller
	private KnobController knobController;
	
	private SliderTwoDirectionsController slider;
	
	private Context context;
	private Activity activity;
	
	private Recorder recorder;
	private Thread recThread;
	
	private final BassEditorController _this = this;
	
	public BassEditorController( Context context, String pdInternalInstrumentName, Activity activity ) {
		
		this.context = context;
		this.pdInternalInstrumentName = pdInternalInstrumentName;
		this.activity = activity;
	}
	
	@Override
	public void setup() {
		
		recorder = new Recorder();
		
		slider = new SliderTwoDirectionsController( context );
		slider.setup();
		
		model = (BassEditorModel) InstrumentTracker.getModel( pdInternalInstrumentName );
		
		//If nothing has been recorded yet, add the new recorder as the default recorder
		if( model.getRecorder() == null ) {
			
			model.registerRecorder( recorder );
		}
		
		view = new BassEditorView( context, activity );
		view.init();
		view.addTwoDimensionalSlider( slider.getView() );
		view.getRecordButton().setOnClickListener( recordButtonPressed );
		view.getStopRecordingButton().setOnClickListener( stopRecordingButtonPressed );
		
		knobController = new KnobController( context );
		knobController.addObserver( this );
		knobController.setup();
		knobController.getModel().setCurrentPercent( model.getAttack() / model.getMaxAttack() );
		knobController.getView().invalidate(); //required to redraw the view after the model was changed
		
		view.addAttackKnob( knobController.getView() );
		
		slider.addObserver( this );
	}

	@Override
	public void teardown() {
		
		
	}
	
	public View getView() {
		
		return view;
	}
	
	public OnClickListener recordButtonPressed = new OnClickListener() {
		
		@Override
		public void onClick( View v ) {
			
			view.toggleRecord();
			
			recorder = new Recorder();
			recorder.addObserver( _this );
			model.registerRecorder( recorder );
			
			recThread = new Thread( recorder );
			recThread.start();
		}
	};
	
	public OnClickListener stopRecordingButtonPressed = new OnClickListener() {
		
		@Override
		public void onClick( View v ) {
			
			view.toggleRecord();
			
			model.registerRecorder( recorder );
			
			recorder.stopRecording();
			recorder.removeObserver( _this );
			
			Beat b = recorder.getBeat();
			
			b.toString();
					
			recThread = null;
		}
	};
	
	@Override
	public void propertyChange( PropertyChangeEvent event ) {
		
		if( event.getPropertyName().equals( SliderTwoDirectionsView.NEW_PERCENTAGES ) ) {
			
			PointF percentages = (PointF) event.getNewValue();
			
			model.setOscController( percentages.x * 200 );
			model.setVibController( percentages.y * 800 );
		
		} else if ( event.getPropertyName().equals( SliderTwoDirectionsController.EVENT_DOWN ) ) {
			
			model.setOnoff( 1 );
			
		} else if ( event.getPropertyName().equals( SliderTwoDirectionsController.EVENT_UP ) ) {
			
			model.setOnoff( 0 );
		
		} else if ( event.getPropertyName().equals( Recorder.TIME_UPDATED )  ) {
			
			view.setTimeDisplayContext( (Long) event.getNewValue() );
		
		} else if ( event.getPropertyName().equals( KnobModel.NEW_PERCENTAGES ) ) {
			
			model.setAttack( (Float) event.getNewValue() );
		}
	}
}