package se.purestyle.beatr.view.info;

import se.purestyle.beatr.R;
import se.purestyle.beatr.helpers.VersionKeeper;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HelpView extends LinearLayout {

	private Context context;
	
	public HelpView( Context context ) {
		
		super( context );
		
		this.context = context;
		
		setLayoutParams( new LayoutParams( LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT ) );
		setPadding( 20, 10, 20, 0 );
		setOrientation( LinearLayout.VERTICAL );
		
		//Add header and the knob
		TextView welcomeText = getTextView( "This is version " + VersionKeeper.VERSION + " of the beatr app. We strive to use RERO, release early, relase often. Please submit your ideas for how to improve this app, it is work in progress. Right now, it's not possible to delete individual instruments. Just go back to the main menu and then enter the mixer again."
											,Gravity.CENTER
											,0 );
		addView( welcomeText );
		
	//	Add an explanation of the rewind button
		LinearLayout rewindButtonDesctiption = getPictureDescription( R.drawable.rewindinstrument, "Click this button to rewind the associated instrument's recorded beat." );
		addView( rewindButtonDesctiption );
		
	//	Add an explanation of the edit button
		LinearLayout editButtonDesctiption = getPictureDescription( R.drawable.editinstrument, "Click this button to edit the associated instrument and record a beat." );
		addView( editButtonDesctiption );
		
//		Add an explanation of the edit button
		LinearLayout nextButtonDesctiption = getPictureDescription( R.drawable.next, "When having a long list of instruments, navigate the list with the next and previous–buttons." );
		addView( nextButtonDesctiption );
	}
	
	private TextView getTextView( String text, int gravity, int paddingLeft ) {
		
		//Create typeface
		Typeface LHLine1Sans = Typeface.createFromAsset( context.getAssets(), "fonts/lhine1sansthin.ttf" );
		
		TextView welcomeText = new TextView( context );
		welcomeText.setPadding( paddingLeft, 0, 0, 0 );
		welcomeText.setTextColor( Color.parseColor( "#F6FF00" ) );
		welcomeText.setTypeface( LHLine1Sans );
		welcomeText.setText( text );
		welcomeText.setGravity( gravity );
		
		return welcomeText;
	}
	
	private LinearLayout getPictureDescription( int resId, String desc ) {
		
		LinearLayout picDesc = new LinearLayout( context );
		picDesc.setPadding( 0, 15, 0, 15 );
		ImageView rewindButtonSample = new ImageView( context );
		rewindButtonSample.setImageResource( resId );
		picDesc.addView( rewindButtonSample );
		picDesc.addView( getTextView( desc, Gravity.LEFT, 10 ) );
		
		return picDesc;
	}
}