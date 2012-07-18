package se.purestyle.beatr.editoractivities;

import com.purestyle.amvc.controller.AbstractController;

import se.purestyle.beatr.controller.editors.BassEditorController;
import se.purestyle.beatr.controller.generic.SliderTwoDirectionsController;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BassEditorActivity extends Activity {
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		
		super.onCreate( savedInstanceState );
		
		LinearLayout holder = new LinearLayout( getApplicationContext() );
		holder.setOrientation( LinearLayout.VERTICAL );
		
		LinearLayout insideHolder = new LinearLayout( getApplicationContext() );
		insideHolder.setLayoutParams( new LayoutParams( LayoutParams.FILL_PARENT, 200 ) );
		
		TextView txt = new TextView( getApplicationContext() );
		txt.setTextColor( Color.WHITE );
		txt.setText( "Bass editor" );
		
		holder.addView( insideHolder );
		holder.addView( txt );
		
		//The view I'm working on
		SliderTwoDirectionsController controller = new SliderTwoDirectionsController( getApplicationContext() );
		controller.setup();
		
		insideHolder.addView( controller.getView() );
		
		setContentView( holder );
		
		//Get the name of this instrument
		Bundle extras = getIntent().getExtras();
		
		AbstractController instrumentEditorController = new BassEditorController( extras.getString( "INSTRUMENT_NAME" ) );
		instrumentEditorController.setup();
	}
}