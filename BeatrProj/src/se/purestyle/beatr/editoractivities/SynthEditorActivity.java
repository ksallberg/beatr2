package se.purestyle.beatr.editoractivities;

import se.purestyle.beatr.controller.editors.SynthEditorController;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;

public class SynthEditorActivity extends Activity {
	
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
		
		SynthEditorController instrumentEditorController = new SynthEditorController( getApplicationContext(), extras.getString( "INSTRUMENT_NAME" ), this );
		instrumentEditorController.setup();
		
		holder.addView( instrumentEditorController.getView() );
	}
}