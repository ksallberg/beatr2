package se.purestyle.beatr;

import se.purestyle.beatr.view.information.MainMenuView;
import se.purestyle.beatr.viewactivites.BeatrTemplate;
import se.purestyle.beatr.viewactivites.MixerActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.View;

public class BeatrActivity extends BeatrTemplate {

	private Intent mixerIntent;
	
	@Override
	public void onCreate( Bundle bundle ) {
		
		super.onCreate( bundle );
		
		MainMenuView menuView = new MainMenuView( getApplicationContext() );
		menuView.setName( "mixer" );
		menuView.setRadius( 200 );
		menuView.init();
		menuView.setOnClickListener( mixerClicked );
		holder.addView( menuView );
		
		MainMenuView menuView2 = new MainMenuView( getApplicationContext() );
		menuView2.setName( "help" );
		menuView2.setRadius( 110 );
		menuView2.init();
		menuView2.setOnClickListener( helpClicked );
		holder.addView( menuView2 );
		
		MainMenuView menuView3 = new MainMenuView( getApplicationContext() );
		menuView3.setName( "credits" );
		menuView3.setRadius( 65 );
		menuView3.init();
		menuView3.setOnClickListener( creditsClicked );
		holder.addView( menuView3 );
		
		setContentView( holder );
		
		//Create the mixer intent
		mixerIntent = new Intent( this, MixerActivity.class );
		
		
	}
	
	private OnClickListener mixerClicked = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			Log.i( "MainMenuActivity" , "Mixer clicked" );
			startActivityIfNeeded( mixerIntent, 0 );
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