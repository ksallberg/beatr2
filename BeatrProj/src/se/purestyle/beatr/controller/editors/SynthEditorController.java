package se.purestyle.beatr.controller.editors;

import java.beans.PropertyChangeEvent;

import se.purestyle.beatr.controller.generic.SliderTwoDirectionsController;
import se.purestyle.beatr.helpers.InstrumentTracker;
import se.purestyle.beatr.model.editors.SynthEditorModel;
import se.purestyle.beatr.view.generic.SliderTwoDirectionsView;

import android.content.Context;
import android.graphics.PointF;
import android.view.View;

import com.purestyle.amvc.controller.AbstractController;

public class SynthEditorController extends AbstractController {
	
	private String pdInternalInstrumentName; //Keep it here just to be able to pass it to the model
	
	private SynthEditorModel model;
	
	//The view I'm working on
	private SliderTwoDirectionsController slider;
			
	
	public SynthEditorController( Context context, String pdInternalInstrumentName ) {
		
		this.pdInternalInstrumentName = pdInternalInstrumentName;
		
		slider = new SliderTwoDirectionsController( context );
		slider.setup();
	}
	
	@Override
	public void setup() {
		
		model = (SynthEditorModel) InstrumentTracker.getModel( pdInternalInstrumentName );
		
		slider.addObserver( this );
	}

	@Override
	public void teardown() {
		
		
	}
	
	public View getSliderView() {
		
		return slider.getView();
	}
	
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
		}
	}
}