package se.purestyle.beatr.infoactivities;

import se.purestyle.beatr.view.info.HelpView;
import se.purestyle.beatr.view.instrumentmixer.LogoView;
import android.os.Bundle;
import android.widget.LinearLayout;

public class HelpActivity extends BeatrTemplate {

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		
		//Super to whatever the android framework does in activity
		super.onCreate( savedInstanceState );
		
		logoView.setButtonText( "   back to menu" );
		
		setContentView( holder );
		
		//Add the helpLayout to holder
		
		LinearLayout helpView = new HelpView( getApplicationContext() );
		
		holder.addView( helpView );
		
		//Add listener to the back button
		holder.findViewWithTag( LogoView.BACK_TO_MIXER_BUTTON ).setOnClickListener( onBackToMenuClicked );
	}
}