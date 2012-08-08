package se.purestyle.beatr.view.instrumentmixer;

//import org.puredata.android.service.R;

import org.puredata.android.service.R;

import se.purestyle.beatr.view.InstrumentMixerView;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class StartView extends RelativeLayout {
	
	private LinearLayout holder;
	private FooterView footerView;
	private AddInstrumentView addInstrumentView;
	
	public StartView( Context context ) {
		
		super( context );
		
		setLayoutParams( new LayoutParams( LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT ) );
		
		BitmapDrawable tiles = new BitmapDrawable( BitmapFactory.decodeResource( getResources(), R.drawable.illusion ) );
		tiles.setTileModeX( Shader.TileMode.REPEAT );
		tiles.setTileModeY( Shader.TileMode.REPEAT );
		
		setBackgroundDrawable( tiles );
		
		init( context );
	}
	
	private void init( final Context context ) {
		
		holder = new LinearLayout( context );
		holder.setOrientation( LinearLayout.VERTICAL );
		holder.setLayoutParams( new LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.FILL_PARENT ) );
		addView( holder );
		
		HeaderView headerView = new HeaderView( context );
		holder.addView( headerView );
		
		final InstrumentHolderView instrumentHolderView = new InstrumentHolderView( context );
		holder.addView( instrumentHolderView );
		
		// Footer!
		RelativeLayout.LayoutParams footerParams = new RelativeLayout.LayoutParams( new MarginLayoutParams( RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT ) );
		footerParams.addRule( RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE );
		footerParams.addRule( RelativeLayout.ALIGN_LEFT, RelativeLayout.TRUE );
		
		footerView = new FooterView( context );
		footerView.setLayoutParams( footerParams );
		addView( footerView );
		
		// 行行行行行行行 Overlay, add instruments!
		RelativeLayout.LayoutParams overlayParams = new RelativeLayout.LayoutParams( new MarginLayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT ) );
		overlayParams.addRule( RelativeLayout.ALIGN_PARENT_BOTTOM );
		overlayParams.addRule( RelativeLayout.ALIGN_LEFT );
		overlayParams.setMargins( 5, 0, 0, 0 );
		
		//Add overlay
		addInstrumentView = new AddInstrumentView( context );
		addInstrumentView.setLayoutParams( overlayParams );
		addInstrumentView.setTag( InstrumentMixerView.ADD_INSTRUMENT_VIEW );
		addInstrumentView.hide();
		addView( addInstrumentView );
		
//______ 
		ViewTreeObserver vto = instrumentHolderView.getViewTreeObserver();
		vto.addOnGlobalLayoutListener( new ViewTreeObserver.OnGlobalLayoutListener() {
		  
		    @Override
		    public void onGlobalLayout() {
		    	
		        ViewTreeObserver obs = instrumentHolderView.getViewTreeObserver();
		        obs.removeGlobalOnLayoutListener( this );
		        
		        instrumentHolderView.init( context, footerView.getHeight() );
		    }
		} );
	}
	
	public void showOverlay() {
		
		addInstrumentView.show();
	}
	
	public void hideOverlay() {
		
		addInstrumentView.hide();
	}
}