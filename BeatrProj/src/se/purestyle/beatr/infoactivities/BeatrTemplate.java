package se.purestyle.beatr.infoactivities;

import se.purestyle.beatr.R;
import se.purestyle.beatr.view.instrumentmixer.LogoView;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

/**
 * Works like a stylesheet, extend this class and you'll get the usual backround and logo
 * 
 * @author kristian
 *
 */
public class BeatrTemplate extends Activity {

	public LinearLayout holder;
	protected LogoView logoView;
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		
		//Super to whatever the android framework does in activity
		super.onCreate( savedInstanceState );
		
		//Remove the standard top bar stating the application name, I use a logo instead
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		
		holder = new LinearLayout( getApplicationContext() );
		holder.setOrientation( LinearLayout.VERTICAL );
		
		logoView = new LogoView( getApplicationContext() );
//		logoView.removeBackToMixerButton();
		holder.addView( logoView );
		
		//Repeating Background
		BitmapDrawable tiles = new BitmapDrawable( BitmapFactory.decodeResource( getResources(), R.drawable.illusion ) );
		tiles.setTileModeX( Shader.TileMode.REPEAT );
		tiles.setTileModeY( Shader.TileMode.REPEAT );
		
		holder.setBackgroundDrawable( tiles );
	}
	
	protected OnClickListener onBackToMenuClicked = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			finish();
		}
	};
}