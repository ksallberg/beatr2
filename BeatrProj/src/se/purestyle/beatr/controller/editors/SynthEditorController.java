package se.purestyle.beatr.controller.editors;

import java.beans.PropertyChangeEvent;

import se.purestyle.beatr.controller.generic.SliderTwoDirectionsController;
import se.purestyle.beatr.helpers.InstrumentTracker;
import se.purestyle.beatr.helpers.beatplayer.Beat;
import se.purestyle.beatr.helpers.beatplayer.Recorder;
import se.purestyle.beatr.model.editors.SynthEditorModel;
import se.purestyle.beatr.view.editors.SynthEditorView;
import se.purestyle.beatr.view.generic.SliderTwoDirectionsView;

import android.app.Activity;
import android.content.Context;
import android.graphics.PointF;
import android.view.View;
import android.view.View.OnClickListener;

import com.purestyle.amvc.controller.AbstractController;

public class SynthEditorController extends AbstractController {
	
	private String pdInternalInstrumentName; //Keep it here just to be able to pass it to the model
	
	private SynthEditorModel model;
	private SynthEditorView view;
	
	//The view I'm working on
	private SliderTwoDirectionsController slider;
	
	private Context context;
	private Activity activity;
	
	private Recorder recorder;
	private Thread recThread;
	
	private final SynthEditorController _this = this;
	
	public SynthEditorController( Context context, String pdInternalInstrumentName, Activity activity ) {
		
		this.context = context;
		this.pdInternalInstrumentName = pdInternalInstrumentName;
		this.activity = activity;
	}
	
	@Override
	public void setup() {
		
		recorder = new Recorder();
		
		slider = new SliderTwoDirectionsController( context );
		slider.setup();
		
		model = (SynthEditorModel) InstrumentTracker.getModel( pdInternalInstrumentName );
		model.registerRecorder( recorder );
		
		view = new SynthEditorView( context, activity );
		view.addTwoDimensionalSlider( slider.getView() );
		view.getRecordButton().setOnClickListener( recordButtonPressed );
		view.getStopRecordingButton().setOnClickListener( stopRecordingButtonPressed );
		
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
			
			model.setOscController( percentages.x * 800 );
			model.setVibController( percentages.y * 800 );
		
		} else if ( event.getPropertyName().equals( SliderTwoDirectionsController.EVENT_DOWN ) ) {
			
			model.setOnoff( 1 );
			
		} else if ( event.getPropertyName().equals( SliderTwoDirectionsController.EVENT_UP ) ) {
			
			model.setOnoff( 0 );
		
		} else if ( event.getPropertyName().equals( Recorder.TIME_UPDATED )  ) {
			
			view.setTimeDisplayContext( (Long) event.getNewValue() );
		}
	}
}