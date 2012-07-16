package se.purestyle.beatr;

import se.purestyle.beatr.controller.generic.SliderTwoDirectionsController;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class TestActivity extends Activity {
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		
		super.onCreate( savedInstanceState );
		
		LinearLayout holder = new LinearLayout( getApplicationContext() );
		
		LinearLayout insideHolder = new LinearLayout( getApplicationContext() );
		insideHolder.setLayoutParams( new LayoutParams( LayoutParams.FILL_PARENT, 300 ) );
		
		holder.addView( insideHolder );
		
		//The view I'm working on
		SliderTwoDirectionsController controller = new SliderTwoDirectionsController( getApplicationContext() );
		controller.setup();
		
		insideHolder.addView( controller.getView() );
		
		setContentView( holder );		
	}
}