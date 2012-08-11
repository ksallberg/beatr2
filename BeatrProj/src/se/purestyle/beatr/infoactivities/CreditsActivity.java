package se.purestyle.beatr.infoactivities;

import android.os.Bundle;

public class CreditsActivity extends BeatrTemplate {

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		
		//Super to whatever the android framework does in activity
		super.onCreate( savedInstanceState );
		
		logoView.setButtonText( "   back to menu" );
		
		setContentView( holder );
		
		
	}
}