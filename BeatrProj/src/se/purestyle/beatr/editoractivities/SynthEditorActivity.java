package se.purestyle.beatr.editoractivities;

import com.purestyle.amvc.controller.AbstractController;

import se.purestyle.beatr.controller.editors.SynthEditorController;
import se.purestyle.beatr.controller.generic.SliderTwoDirectionsController;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SynthEditorActivity extends Activity {
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		
		//Super to whatever the android framework does in activity
		super.onCreate( savedInstanceState );
		
		//Remove the standard top bar stating the application name, I use a logo instead
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		
		LinearLayout holder = new LinearLayout( getApplicationContext() );
		holder.setOrientation( LinearLayout.VERTICAL );
		
		LinearLayout insideHolder = new LinearLayout( getApplicationContext() );
		insideHolder.setLayoutParams( new LayoutParams( LayoutParams.FILL_PARENT, 275 ) );
		
		TextView txt = new TextView( getApplicationContext() );
		txt.setTextColor( Color.WHITE );
		txt.setText( "Synth editor " );
		
		holder.addView( insideHolder );
		
		
		
		
		holder.addView( txt );
		
		setContentView( holder );
		
		//Get the name of this instrument
		Bundle extras = getIntent().getExtras();
		
		SynthEditorController instrumentEditorController = new SynthEditorController( getApplicationContext(), extras.getString( "INSTRUMENT_NAME" ) );
		instrumentEditorController.setup();
		
		insideHolder.addView( instrumentEditorController.getSliderView() );
	}
}