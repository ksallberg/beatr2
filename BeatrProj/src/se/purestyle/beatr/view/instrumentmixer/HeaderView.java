package se.purestyle.beatr.view.instrumentmixer;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;

public class HeaderView extends LinearLayout {

	public HeaderView( Context context ) {
		
		super( context );
		
		init( context );
	}
	
	private void init( Context context ) {
		
		setLayoutParams( new LayoutParams( 300, 60 ) );
		setBackgroundColor( Color.MAGENTA );
	}
}