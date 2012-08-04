package se.purestyle.beatr;

import se.purestyle.beatr.controller.generic.DrumPadController;
import se.purestyle.beatr.view.generic.ImageAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class WorkBenchActivity extends Activity {

	LinearLayout holder;
	
	@Override
	public void onCreate( Bundle test ) {
		
		super.onCreate( test );
		
		//Remove the standard top bar stating the application name, I use a logo instead
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		
		ImageAdapter adapter = new ImageAdapter( getApplicationContext() );
		
		GridView holder = new GridView( getApplicationContext() );
		holder.setNumColumns( GridView.AUTO_FIT );
		holder.setAdapter( adapter );
		setContentView( holder );
		
		for( int i = 0; i < 10; i ++ ) {
			
			DrumPadController drumPadController = new DrumPadController( getApplicationContext() );
			drumPadController.setup();
			
			LayoutParams subParams = new LayoutParams( 75, 75 );
			
			LinearLayout subHolder = new LinearLayout( getApplicationContext() );
			
			subHolder.setLayoutParams( subParams );
			subHolder.addView( drumPadController.getView() );
		}
	}
}