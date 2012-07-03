package se.purestyle.beatr.view.instrumentmixer;

import org.puredata.android.service.R;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class StartView extends RelativeLayout {

	private LinearLayout holder;
	
	public StartView( Context context ) {
		
		super( context );
		
		setLayoutParams( new LayoutParams( LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT ) );
		
		setBackgroundResource( R.drawable.bg );
		
		init( context );
	}
	
	private void init( Context context ) {
		
		holder = new LinearLayout( context );
		holder.setOrientation( LinearLayout.VERTICAL );
		addView( holder );
		
		HeaderView headerView = new HeaderView( context );
		holder.addView( headerView );
		
		View ins = new InstrumentMixerView( context );
		holder.addView( ins );
		
		FooterView footerView = new FooterView( context );
		holder.addView( footerView );
		
		// 行行行行行行行 Overlay, add instruments!
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(new MarginLayoutParams(100, 100));
		params.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
		
		//Add overlay
		AddInstrumentView addInstrumentView = new AddInstrumentView( context );
		addInstrumentView.setLayoutParams( params );
		addView( addInstrumentView );
	}
}