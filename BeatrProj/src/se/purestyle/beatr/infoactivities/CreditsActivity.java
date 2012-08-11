package se.purestyle.beatr.infoactivities;

import se.purestyle.beatr.view.info.CreditsView;
import se.purestyle.beatr.view.instrumentmixer.LogoView;
import android.os.Bundle;
import android.widget.LinearLayout;

public class CreditsActivity extends BeatrTemplate {

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		
		//Super to whatever the android framework does in activity
		super.onCreate( savedInstanceState );
		
		logoView.setButtonText( "   back to menu" );
		
		setContentView( holder );
		
		LinearLayout creditsView = new CreditsView( getApplicationContext() );
		holder.addView( creditsView );
		
		//Add listener to the back button
		holder.findViewWithTag( LogoView.BACK_TO_MIXER_BUTTON ).setOnClickListener( onBackToMenuClicked );
	}
}