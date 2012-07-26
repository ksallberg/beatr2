package se.purestyle.beatr;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import se.purestyle.beatr.controller.generic.KnobController;
import se.purestyle.beatr.model.generic.KnobModel;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class WorkBenchActivity extends Activity implements PropertyChangeListener {

	LinearLayout holder;
	
	@Override
	public void onCreate( Bundle test ) {
		
		super.onCreate( test );
		
		//Remove the standard top bar stating the application name, I use a logo instead
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		
		KnobController knobController = new KnobController( getApplicationContext() );
		knobController.setup();
		knobController.addObserver( this );
		
		holder = new LinearLayout( getApplicationContext() );
		
		holder.setBackgroundColor( Color.WHITE );
		
		LinearLayout innerHolder = new LinearLayout( getApplicationContext() );
		LayoutParams params = new LayoutParams( 100, 100 );
		params.setMargins( 50, 50, 0, 0 );
		innerHolder.setLayoutParams( params );
		
		innerHolder.addView( knobController.getView() );
		
		holder.addView(innerHolder);
		
		setContentView( holder );
	}
	
	@Override
	public void propertyChange( PropertyChangeEvent event ) {
		
		if( event.getPropertyName().equals( KnobModel.NEW_PERCENTAGES ) ) {
			
			Log.i( "WorkBenchActivity", "Percentage: " + event.getNewValue() );
		}
	}
}