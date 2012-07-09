package se.purestyle.beatr.view.instrumentmixer;

import org.puredata.android.service.R;

import se.purestyle.beatr.view.InstrumentMixerView;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class FooterView extends RelativeLayout {
	
	public FooterView( Context context ) {
		
		super( context );
		
		init( context );
	}
	
	private void init( Context context ) {
		
		//TODO: This v is just an ugly fix to get the bottom margins working.
		View v = new View(context);
		v.setLayoutParams( new LayoutParams( 0, 49 ));
		addView( v );
		
		LayoutParams footerParams = new LayoutParams( LayoutParams.FILL_PARENT, InstrumentMixerView.FOOTER_VIEW_HEIGHT + 10 );
		footerParams.setMargins( 5, 5, 5, 5 );
		
		setLayoutParams( footerParams );
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
		params.addRule( RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE );
		params.setMargins( 5, 0, 0, 5 );
		
		Typeface LHLine1Sans = Typeface.createFromAsset( context.getAssets(), "fonts/lhine1sansthin.ttf" );
		
		Button addInstrumentButton = new Button( context );
		addInstrumentButton.setTypeface( LHLine1Sans );
		addInstrumentButton.setTextColor( Color.parseColor("#F6FF00") );
		addInstrumentButton.setBackgroundResource( R.drawable.addinstrumentbutton );
		addInstrumentButton.setText( "     add instrument" );
		addInstrumentButton.setTag( InstrumentMixerView.ADD_INSTRUMENT_BUTTON );
		addInstrumentButton.setLayoutParams( params );
		addView( addInstrumentButton );
		
		RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
		params2.addRule( RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE );
		params2.setMargins( 0, 0, 5, 5 );
		
		
		LinearLayout arrowKeeper = new LinearLayout( context );
		arrowKeeper.setLayoutParams( params2 );
		addView( arrowKeeper );
		
		Button previousButton = new Button( context );
		previousButton.setTag( InstrumentMixerView.PREV_PAGE_BUTTON );
		previousButton.setBackgroundResource( R.drawable.prev );
		arrowKeeper.addView( previousButton );
		
		//TODO: This arrowSpacer is just an ugly hack to get a spacing between the arrows, figure out how to fix with margins!
		View arrowSpacer = new View( context );
		arrowSpacer.setLayoutParams( new LayoutParams( 6, 0 ) );

		arrowKeeper.addView( arrowSpacer );
		
		Button nextButton = new Button( context );
		nextButton.setTag( InstrumentMixerView.NEXT_PAGE_BUTTON );
		nextButton.setBackgroundResource( R.drawable.next );
		arrowKeeper.addView( nextButton );
	}
}