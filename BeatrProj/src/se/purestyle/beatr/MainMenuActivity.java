package se.purestyle.beatr;

import se.purestyle.beatr.view.information.MainMenuView;
import se.purestyle.beatr.view.instrumentmixer.LogoView;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

public class MainMenuActivity extends Activity {

	@Override
	public void onCreate( Bundle bundle ) {
		
		super.onCreate( bundle );
		
		//Remove the standard top bar stating the application name, I use a logo instead
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		
		LinearLayout holder = new LinearLayout( getApplicationContext() );
		holder.setOrientation( LinearLayout.VERTICAL );
		
		LogoView logoView = new LogoView( getApplicationContext() );
		logoView.removeBackToMixerButton();
		holder.addView( logoView );
		
		//Repeating Background
		BitmapDrawable tiles = new BitmapDrawable( BitmapFactory.decodeResource( getResources(), R.drawable.illusion ) );
		tiles.setTileModeX( Shader.TileMode.REPEAT );
		tiles.setTileModeY( Shader.TileMode.REPEAT );
		
		holder.setBackgroundDrawable( tiles );
		
		MainMenuView menuView = new MainMenuView( getApplicationContext() );
		menuView.setName( "mixer" );
		menuView.setRadius( 200 );
		menuView.init();
		menuView.setOnClickListener( mixerClicked );
		holder.addView( menuView );
		
		MainMenuView menuView2 = new MainMenuView( getApplicationContext() );
		menuView2.setName( "help" );
		menuView2.setRadius( 125 );
		menuView2.init();
		menuView2.setOnClickListener( helpClicked );
		holder.addView( menuView2 );
		
		MainMenuView menuView3 = new MainMenuView( getApplicationContext() );
		menuView3.setName( "credits" );
		menuView3.setRadius( 70 );
		menuView3.init();
		menuView3.setOnClickListener( creditsClicked );
		holder.addView( menuView3 );
		
		setContentView( holder );
	}
	
	private OnClickListener mixerClicked = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			Log.i( "MainMenuActivity" , "Mixer clicked" );
		}
	};
	
	private OnClickListener helpClicked = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			Log.i( "MainMenuActivity" , "Help clicked" );
		}
	};
	
	private OnClickListener creditsClicked = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			Log.i( "MainMenuActivity" , "Credits clicked" );
		}
	};
}