package se.purestyle.beatr.editoractivities;

import se.purestyle.beatr.controller.editors.BassEditorController;
import se.purestyle.beatr.helpers.beatplayer.BeatPlayer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;

public class BassEditorActivity extends Activity {
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		
		//Super to whatever the android framework does in activity
		super.onCreate( savedInstanceState );
		
		//Remove the standard top bar stating the application name, I use a logo instead
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		
		LinearLayout holder = new LinearLayout( getApplicationContext() );		
		setContentView( holder );
		
		//Get the name of this instrument
		Bundle extras = getIntent().getExtras();
		
		BassEditorController instrumentEditorController = new BassEditorController( getApplicationContext(), extras.getString( "INSTRUMENT_NAME" ), this );
		instrumentEditorController.setup();
		
		holder.addView( instrumentEditorController.getView() );
		
		//Stop all players
		BeatPlayer.getInstance().setMode( BeatPlayer.NONE );
	}
}