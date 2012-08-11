package se.purestyle.beatr;

import se.purestyle.beatr.infoactivities.BeatrTemplate;
import se.purestyle.beatr.infoactivities.CreditsActivity;
import se.purestyle.beatr.infoactivities.HelpActivity;
import se.purestyle.beatr.view.info.MainMenuView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;

public class BeatrActivity extends BeatrTemplate {

	private Intent mixerIntent;
	private Intent helpIntent;
	private Intent creditsIntent;
	
	@Override
	public void onCreate( Bundle bundle ) {
		
		super.onCreate( bundle );
		
		logoView.removeBackToMixerButton();
		
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
		menuView3.setRadius( 50 );
		menuView3.init();
		menuView3.setOnClickListener( creditsClicked );
		holder.addView( menuView3 );
		
		setContentView( holder );
		
		//Create the mixer intent
		mixerIntent = new Intent( this, MixerActivity.class );
		helpIntent = new Intent( this, HelpActivity.class );
		creditsIntent = new Intent( this, CreditsActivity.class );
	}
	
	private OnClickListener mixerClicked = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			startActivityIfNeeded( mixerIntent, 0 );
		}
	};
	
	private OnClickListener helpClicked = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			startActivity( helpIntent );
		}
	};
	
	private OnClickListener creditsClicked = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			startActivity( creditsIntent );
		}
	};
}