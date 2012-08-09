package se.purestyle.beatr.editoractivities;

import org.puredata.android.service.R;

import com.purestyle.amvc.controller.AbstractController;

import se.purestyle.beatr.controller.editors.DrumEditorController;
import se.purestyle.beatr.helpers.beatplayer.BeatPlayer;
import se.purestyle.beatr.view.instrumentmixer.LogoView;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class DrumEditorActivity extends Activity {
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		
		super.onCreate( savedInstanceState );
		
		//Remove the standard top bar stating the application name, I use a logo instead
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		
		LinearLayout holder = new LinearLayout( getApplicationContext() );
		holder.setOrientation( LinearLayout.VERTICAL );
		
		//Create and add the logo to this activity
		LogoView logoView = new LogoView( getApplicationContext() );
		holder.addView( logoView );
		
		LinearLayout insideHolder = new LinearLayout( getApplicationContext() );
		insideHolder.setLayoutParams( new LayoutParams( LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT ) );
		
		holder.addView( insideHolder );
		
		BitmapDrawable tiles = new BitmapDrawable( BitmapFactory.decodeResource( getResources(), R.drawable.illusion ) );
		tiles.setTileModeX( Shader.TileMode.REPEAT );
		tiles.setTileModeY( Shader.TileMode.REPEAT );
		
		holder.setBackgroundDrawable( tiles );
		
		setContentView( holder );
		
		//Get the name of this instrument
		Bundle extras = getIntent().getExtras();
		
		AbstractController instrumentEditorController = new DrumEditorController( extras.getString( "INSTRUMENT_NAME" ), getApplicationContext(), this );
		instrumentEditorController.setup();
		
		insideHolder.addView( ( ( DrumEditorController ) instrumentEditorController ).getView() );
		
		//Play only this instance
		BeatPlayer.getInstance().setMode( BeatPlayer.NONE );
		BeatPlayer.getInstance().setMode( extras.getString( "INSTRUMENT_NAME" ) );
	}
}