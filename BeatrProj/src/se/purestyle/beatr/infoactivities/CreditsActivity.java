package se.purestyle.beatr.infoactivities;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class CreditsActivity extends BeatrTemplate {

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		
		//Super to whatever the android framework does in activity
		super.onCreate( savedInstanceState );
		
		Button btn = new Button( getApplicationContext() );
		
		btn.setText( "Credits!" );
		
		holder.addView( btn );
		
		Log.i( "CreditsActivity", "Credits" );
		
		setContentView( holder );
	}
}