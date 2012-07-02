package se.purestyle.beatr.view.instrumentmixer;

import org.puredata.android.service.R;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

public class StartView extends LinearLayout {

	public StartView( Context context ) {
		
		super( context );
		
		setLayoutParams( new LayoutParams( LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT ) );
		
		
		
		setOrientation( VERTICAL );
		
		setBackgroundResource( R.drawable.bg );
		
		init( context );
	}
	
	private void init( Context context ) {
		
		HeaderView headerView = new HeaderView( context );
		addView( headerView );
		
		View ins = new InstrumentMixerView( context );
		addView( ins );
		
		FooterView footerView = new FooterView( context );
		addView( footerView );
	}
}