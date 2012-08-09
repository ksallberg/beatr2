package se.purestyle.beatr.view.generic;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class KnobAndHeader extends LinearLayout {

	private Context context;
	
	public KnobAndHeader( Context context ) {
		
		super( context );
		
		this.context = context;
		
		setLayoutParams( new AbsListView.LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT ) );
		setOrientation( LinearLayout.VERTICAL );
	}
	
	public void setContent( String header, View knob ) {
		
		//Create typeface
		Typeface LHLine1Sans = Typeface.createFromAsset( context.getAssets(), "fonts/lhine1sansthin.ttf" );
		
		//Add header and the knob
		TextView attackFilterHeader = new TextView( context );
		attackFilterHeader.setTextColor( Color.parseColor( "#F6FF00" ) );
		attackFilterHeader.setTypeface( LHLine1Sans );
		attackFilterHeader.setText( header );
		attackFilterHeader.setGravity( Gravity.CENTER );
		addView( attackFilterHeader );
		
		//Add the knob
		addView( knob );
	}
}