package se.purestyle.beatr;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class WorkBenchActivity extends Activity {

	LinearLayout holder;
	
	@Override
	public void onCreate( Bundle test ) {
		
		super.onCreate( test );
		
		holder = new LinearLayout( getApplicationContext() );
		
		setContentView( holder );
		
		holder.addView( new Button(getApplicationContext()));
	}
}