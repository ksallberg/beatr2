package se.purestyle.beatr;

import se.purestyle.beatr.controller.generic.DrumPadController;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class WorkBenchActivity extends Activity {

	LinearLayout holder;
	
	@Override
	public void onCreate( Bundle test ) {
		
		super.onCreate( test );
		
		//Remove the standard top bar stating the application name, I use a logo instead
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		
		holder = new LinearLayout( getApplicationContext() );
		setContentView( holder );
		
		LinearLayout insideHolder = new LinearLayout( getApplicationContext() );
		
		LayoutParams params = new LayoutParams( LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT );
		insideHolder.setLayoutParams( params );
		insideHolder.setOrientation( LinearLayout.HORIZONTAL );
		
		holder.addView( insideHolder );
		
		for( int i = 0; i < 10; i ++ ) {
			
			DrumPadController drumPadController = new DrumPadController( getApplicationContext() );
			drumPadController.setup();
			
			LayoutParams subParams = new LayoutParams( 75, 75 );
			
			LinearLayout subHolder = new LinearLayout( getApplicationContext() );
			
			subHolder.setLayoutParams( subParams );
			subHolder.addView( drumPadController.getView() );
			insideHolder.addView( subHolder );
		}
	}
}