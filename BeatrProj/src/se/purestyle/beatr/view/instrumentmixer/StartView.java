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
		holder.setLayoutParams( new LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.FILL_PARENT ) );
		addView( holder );
		
		HeaderView headerView = new HeaderView( context );
		holder.addView( headerView );
		
		View ins = new InstrumentHolderView( context );
		holder.addView( ins );
		
		AddInstrumentView addInstrumentView = new AddInstrumentView( context );
		
		// Footer!
		RelativeLayout.LayoutParams footerParams = new RelativeLayout.LayoutParams( new MarginLayoutParams( RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT ) );
		footerParams.addRule( RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE );
		footerParams.addRule( RelativeLayout.ALIGN_LEFT, RelativeLayout.TRUE );
		
		FooterView footerView = new FooterView( context );
		footerView.setLayoutParams( footerParams );
		footerView.setAddIn( addInstrumentView );
		addView( footerView );
		
		// 行行行行行行行 Overlay, add instruments!
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams( new MarginLayoutParams( 200, RelativeLayout.LayoutParams.WRAP_CONTENT ) );
		params.addRule( RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE );
		params.addRule( RelativeLayout.ALIGN_LEFT, RelativeLayout.TRUE );
		
		//Add overlay
		addInstrumentView.setLayoutParams( params );
		addView( addInstrumentView );
	}
}