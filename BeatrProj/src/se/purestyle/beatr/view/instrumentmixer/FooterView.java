package se.purestyle.beatr.view.instrumentmixer;

import android.content.Context;
import android.graphics.Color;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class FooterView extends RelativeLayout {

	public FooterView( Context context ) {
		
		super( context );
		
		init( context );
	}
	
	private void init( Context context ) {
		
		setLayoutParams( new LayoutParams( LayoutParams.FILL_PARENT, 60 ) );
		setBackgroundColor( Color.CYAN );
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		
		Button btn1 = new Button( context );
		btn1.setText( "(+) add instrument" );
		btn1.setLayoutParams( params );
		addView( btn1 );
		
		RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
		params2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		
		LinearLayout arrowKeeper = new LinearLayout( context );
		arrowKeeper.setLayoutParams( params2 );
		addView( arrowKeeper );
		
		Button btn2 = new Button( context );
		btn2.setText( "<" );
		arrowKeeper.addView( btn2 );
		
		Button btn3 = new Button( context );
		btn3.setText( ">" );
		arrowKeeper.addView( btn3 );
		
		
	}
}