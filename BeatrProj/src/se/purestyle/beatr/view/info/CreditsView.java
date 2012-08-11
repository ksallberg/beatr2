package se.purestyle.beatr.view.info;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CreditsView extends LinearLayout {

	private Context context;
	
	public CreditsView( Context context ) {
		
		super( context );
		
		this.context = context;
		
		setLayoutParams( new LayoutParams( LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT ) );
		setPadding( 50, 10, 40, 0 );
		setOrientation( LinearLayout.VERTICAL );
		
		TextView synthCreds = getTextView( "Software synthesizer for the Android platform by Pure Data.",
											Gravity.CENTER,
											0	);
		addView( synthCreds );
		
		TextView fontCreds = getTextView( "Font: Line 1 Sans by Lufthamn Studio.",
				Gravity.CENTER,
				0	);
		addView( fontCreds );
		
		TextView bgPatternCreds = getTextView( "Background pattern by subtlepatterns.com",
				Gravity.CENTER,
				0	);
		addView( bgPatternCreds );
		
		TextView appificationCreds = getTextView( "Appification by pure style.",
				Gravity.CENTER,
				0	);
		addView( appificationCreds );
	}
	
	private TextView getTextView( String text, int gravity, int paddingLeft ) {
		
		//Create typeface
		Typeface LHLine1Sans = Typeface.createFromAsset( context.getAssets(), "fonts/lhine1sansthin.ttf" );
		
		TextView welcomeText = new TextView( context );
		welcomeText.setPadding( paddingLeft, 45, 0, 0 );
		welcomeText.setTextColor( Color.parseColor( "#F6FF00" ) );
		welcomeText.setTypeface( LHLine1Sans );
		welcomeText.setText( text );
		welcomeText.setGravity( gravity );
		
		return welcomeText;
	}
}