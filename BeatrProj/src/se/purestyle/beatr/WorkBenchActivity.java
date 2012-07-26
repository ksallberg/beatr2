package se.purestyle.beatr;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import se.purestyle.beatr.controller.generic.KnobController;
import se.purestyle.beatr.model.generic.KnobModel;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

public class WorkBenchActivity extends Activity implements PropertyChangeListener {

	LinearLayout holder;
	
	@Override
	public void onCreate( Bundle test ) {
		
		super.onCreate( test );
		
		KnobController knobController = new KnobController( getApplicationContext() );
		knobController.setup();
		knobController.addObserver( this );
		
		holder = new LinearLayout( getApplicationContext() );
		holder.addView( knobController.getView() );
		holder.setBackgroundColor( Color.WHITE );
		
		setContentView( holder );
	}
	
	@Override
	public void propertyChange( PropertyChangeEvent event ) {
		
		if( event.getPropertyName().equals( KnobModel.NEW_PERCENTAGES ) ) {
			
			Log.i( "WorkBenchActivity", "Percentage: " + event.getNewValue() );
		}
	}
}