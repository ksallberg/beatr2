package se.purestyle.beatr.editoractivities;

import com.purestyle.amvc.controller.AbstractController;

import se.purestyle.beatr.controller.editors.DrumEditorController;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DrumEditorActivity extends Activity {
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		
		super.onCreate( savedInstanceState );
		
		LinearLayout holder = new LinearLayout( getApplicationContext() );
		holder.setOrientation( LinearLayout.VERTICAL );
		
		LinearLayout insideHolder = new LinearLayout( getApplicationContext() );
		insideHolder.setLayoutParams( new LayoutParams( LayoutParams.FILL_PARENT, 300 ) );
		
		TextView txt = new TextView( getApplicationContext() );
		txt.setTextColor( Color.WHITE );
		txt.setText( "Drum editor " );
		
		holder.addView( insideHolder );
		holder.addView( txt );
		
		setContentView( holder );
		
		//Get the name of this instrument
		Bundle extras = getIntent().getExtras();
		
		AbstractController instrumentEditorController = new DrumEditorController( extras.getString( "INSTRUMENT_NAME" ), getApplicationContext() );
		instrumentEditorController.setup();
		
		insideHolder.addView( ( ( DrumEditorController ) instrumentEditorController ).getView() );
	}
}