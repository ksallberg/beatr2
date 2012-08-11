package se.purestyle.beatr.infoactivities;

import se.purestyle.beatr.view.info.HelpView;
import android.os.Bundle;
import android.widget.LinearLayout;

public class HelpActivity extends BeatrTemplate {

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		
		//Super to whatever the android framework does in activity
		super.onCreate( savedInstanceState );
		
		setContentView( holder );
		
		//Add the helpLayout to holder
		
		LinearLayout helpView = new HelpView( getApplicationContext() );
		
		holder.addView( helpView );
	}
}