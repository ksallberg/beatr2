package se.purestyle.beatr;

import se.purestyle.beatr.helpers.VersionKeeper;
import se.purestyle.beatr.infoactivities.BeatrTemplate;
import se.purestyle.beatr.infoactivities.CreditsActivity;
import se.purestyle.beatr.infoactivities.HelpActivity;
import se.purestyle.beatr.view.info.MainMenuView;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class BeatrActivity extends BeatrTemplate {

	private Intent mixerIntent;
	private Intent helpIntent;
	private Intent creditsIntent;
	
	@Override
	public void onCreate( Bundle bundle ) {
		
		super.onCreate( bundle );
		
		logoView.removeBackToMixerButton();
		
		//Add versions text to the logoView
		RelativeLayout.LayoutParams versionsTextParams = new RelativeLayout.LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT );
		versionsTextParams.addRule( RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE );
		versionsTextParams.setMargins( 10, 6, 0, 0 );
		
		//Create typeface
		Typeface LHLine1Sans = Typeface.createFromAsset( getApplicationContext().getAssets(), "fonts/lhine1sansthin.ttf" );
				
		TextView versionsText = new TextView( getApplicationContext() );
		versionsText.setTextColor( Color.parseColor( "#F6FF00" ) );
		versionsText.setTypeface( LHLine1Sans );
		versionsText.setText( "beatr v" + VersionKeeper.VERSION );
		versionsText.setLayoutParams( versionsTextParams );
		logoView.addView( versionsText );
		
		MainMenuView menuView = new MainMenuView( getApplicationContext() );
		menuView.setName( "mixr" );
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
		menuView3.setRadius( 40 );
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