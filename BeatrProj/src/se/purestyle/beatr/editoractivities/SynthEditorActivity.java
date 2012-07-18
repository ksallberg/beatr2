package se.purestyle.beatr.editoractivities;

import com.purestyle.amvc.controller.AbstractController;

import se.purestyle.beatr.controller.editors.SynthEditorController;
import se.purestyle.beatr.controller.generic.SliderTwoDirectionsController;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class SynthEditorActivity extends Activity {
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		
		super.onCreate( savedInstanceState );
		
		LinearLayout holder = new LinearLayout( getApplicationContext() );
		
		LinearLayout insideHolder = new LinearLayout( getApplicationContext() );
		insideHolder.setLayoutParams( new LayoutParams( LayoutParams.FILL_PARENT, 300 ) );
		
		holder.addView( insideHolder );
		
		//The view I'm working on
		SliderTwoDirectionsController controller = new SliderTwoDirectionsController( getApplicationContext() );
		controller.setup();
		
		insideHolder.addView( controller.getView() );
		
		setContentView( holder );
		
		//Get the name of this instrument
		Bundle extras = getIntent().getExtras();
		
		AbstractController instrumentEditorController = new SynthEditorController( extras.getString( "INSTRUMENT_NAME" ) );
		instrumentEditorController.setup();
	}
}