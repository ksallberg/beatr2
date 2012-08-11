package se.purestyle.beatr.viewactivites;

import se.purestyle.beatr.R;
import se.purestyle.beatr.view.instrumentmixer.LogoView;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;

/**
 * Works like a stylesheet, extend this class and you'll get the usual backround and logo
 * 
 * @author kristian
 *
 */
public class BeatrTemplate extends Activity {

	public LinearLayout holder;
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		
		//Super to whatever the android framework does in activity
		super.onCreate( savedInstanceState );
		
		//Remove the standard top bar stating the application name, I use a logo instead
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		
		holder = new LinearLayout( getApplicationContext() );
		holder.setOrientation( LinearLayout.VERTICAL );
		
		LogoView logoView = new LogoView( getApplicationContext() );
		logoView.removeBackToMixerButton();
		holder.addView( logoView );
		
		//Repeating Background
		BitmapDrawable tiles = new BitmapDrawable( BitmapFactory.decodeResource( getResources(), R.drawable.illusion ) );
		tiles.setTileModeX( Shader.TileMode.REPEAT );
		tiles.setTileModeY( Shader.TileMode.REPEAT );
		
		holder.setBackgroundDrawable( tiles );
	}
}