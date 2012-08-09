package se.purestyle.beatr.view.instrumentmixer;

import org.puredata.android.service.R;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class LogoView extends RelativeLayout {

	public LogoView( Context context ) {
		
		super( context );
		
		init( context );
	}
	
	private void init( Context context ) {
		
		setLayoutParams( new LayoutParams( LayoutParams.FILL_PARENT, 60 ) );
		
		RelativeLayout.LayoutParams logoParams = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
		logoParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		logoParams.setMargins( 0, 3, 0, 0 );
		
		ImageView logo = new ImageView( context );
		logo.setBackgroundResource( R.drawable.logo );
		logo.setLayoutParams( logoParams );
		addView( logo );
	}
}