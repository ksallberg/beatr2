package se.purestyle.beatr.view.instrumentmixer;

import android.content.Context;
import android.widget.Button;
import android.widget.RelativeLayout;

public class HeaderView extends RelativeLayout {

	public HeaderView( Context context ) {
		
		super( context );
		
		init( context );
	}
	
	private void init( Context context ) {
		
		setLayoutParams( new LayoutParams( LayoutParams.FILL_PARENT, 60 ) );
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		
		Button btn1 = new Button( context );
		btn1.setText( "mstr volume" );
		btn1.setLayoutParams( params );
		addView( btn1 );
		
		RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
		params2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		
		Button btn2 = new Button( context );
		btn2.setText( "logo" );
		btn2.setLayoutParams( params2 );
		addView( btn2 );
	}
}