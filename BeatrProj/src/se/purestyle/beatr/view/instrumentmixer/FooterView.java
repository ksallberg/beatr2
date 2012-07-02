package se.purestyle.beatr.view.instrumentmixer;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;

public class FooterView extends LinearLayout {

	public FooterView( Context context ) {
		
		super( context );
		
		init( context );
	}
	
	private void init( Context context ) {
		
		setLayoutParams( new LayoutParams( 300, 50 ) );
		setBackgroundColor( Color.CYAN );
	}
}