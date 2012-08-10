package se.purestyle.beatr.view.instrumentmixer;

import org.puredata.android.service.R;

import se.purestyle.beatr.view.InstrumentMixerView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class LogoView extends RelativeLayout {

	public static final String BACK_TO_MIXER_BUTTON = "backToMixerButton";
	
	public LogoView( Context context ) {
		
		super( context );
		
		init( context );
	}
	
	private void init( Context context ) {
		
		setLayoutParams( new LayoutParams( LayoutParams.FILL_PARENT, 60 ) );
		
		//Create typeface
		Typeface LHLine1Sans = Typeface.createFromAsset( context.getAssets(), "fonts/lhine1sansthin.ttf" );
		
		RelativeLayout.LayoutParams backToMixerParams = new RelativeLayout.LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT );
		backToMixerParams.addRule( RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE );
		backToMixerParams.setMargins( 6, 6, 0, 0 );
		
		Button backToMixerButton = new Button( context );
		backToMixerButton.setTypeface( LHLine1Sans );
		backToMixerButton.setTextColor( Color.parseColor( "#F6FF00" ) );
		backToMixerButton.setBackgroundResource( R.drawable.backtomixer );
		backToMixerButton.setText( "     back to mixer" );
		backToMixerButton.setTag( InstrumentMixerView.ADD_INSTRUMENT_BUTTON );
		backToMixerButton.setLayoutParams( backToMixerParams );
		backToMixerButton.setTag( BACK_TO_MIXER_BUTTON );
		addView( backToMixerButton );
		
		RelativeLayout.LayoutParams logoParams = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
		logoParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		logoParams.setMargins( 0, 3, 0, 0 );
		
		ImageView logo = new ImageView( context );
		logo.setBackgroundResource( R.drawable.logo );
		logo.setLayoutParams( logoParams );
		addView( logo );
	}
}