package se.purestyle.beatr.view.editors;

import se.purestyle.beatr.view.generic.DrumPadView;
import se.purestyle.beatr.view.generic.KnobAndHeader;
import se.purestyle.beatr.view.generic.ViewAdapter;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DrumEditorView extends LinearLayout {

	private GridView drumPadHolder;
	private GridView knobHolder;
	
	private Context context;
	
	private View[] pads;
	
	public DrumEditorView( Context context ) {
		
		super( context );
		
		this.context = context;
		
		setLayoutParams( new LayoutParams( LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT ) );
		
		setOrientation( LinearLayout.VERTICAL );
		
		//Create typeface
		Typeface LHLine1Sans = Typeface.createFromAsset( context.getAssets(), "fonts/lhine1sansthin.ttf" );
		
		//Params of header text
		LayoutParams headerParams = new LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT );
		headerParams.setMargins( 10, 0, 0, 5 );
		
		//Headet text
		TextView header = new TextView( context );
		header.setTextColor( Color.parseColor( "#F6FF00" ) );
		header.setTypeface( LHLine1Sans );
		header.setText( "drum : edit mode" );
		header.setLayoutParams( headerParams );
		addView( header );
		
		//Create the drum pad holder
		drumPadHolder = new GridView( context );
		drumPadHolder.setLayoutParams( new ViewGroup.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT ) );
		drumPadHolder.setColumnWidth( 45 );
		drumPadHolder.setVerticalSpacing( 10 );
		drumPadHolder.setNumColumns( GridView.AUTO_FIT );
		drumPadHolder.setGravity( Gravity.CENTER );
		drumPadHolder.setPadding(10, 0, 10, 0);
		
		//Create the knob holder
		knobHolder = new GridView( context );
		knobHolder.setLayoutParams( new ViewGroup.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT ) );
		knobHolder.setColumnWidth( 90 );
		knobHolder.setVerticalSpacing( 2 );
		knobHolder.setNumColumns( GridView.AUTO_FIT );
		knobHolder.setGravity( Gravity.CENTER );

		addView( drumPadHolder );
		
		//Params of header text
		LayoutParams filterHeaderParams = new LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT );
		filterHeaderParams.setMargins( 10, 10, 0, 5 );
		
		//Create and add the filter view's header
		TextView filterHeader = new TextView( context );
		filterHeader.setTextColor( Color.parseColor( "#F6FF00" ) );
		filterHeader.setTypeface( LHLine1Sans );
		filterHeader.setText( "drum : filters: " );
		filterHeader.setLayoutParams( filterHeaderParams );
		addView( filterHeader );
		
		addView( knobHolder );
	}
	
	/**
	 * Wrap a list of knobs in linearLayouts 
	 * 
	 * @param knobs
	 * @return
	 */
	private View[] wrapKnobs( View[] knobs, String[] headers ) {
		
		View[] ret = new View[ knobs.length ];
		
		for( int i = 0; i < knobs.length; i ++ ) {
			
			LinearLayout wrapper = new LinearLayout( context );
			wrapper.setLayoutParams( new AbsListView.LayoutParams( 54, 54 ) );
			
			KnobAndHeader kAH = new KnobAndHeader( context );
			kAH.setContent( headers[ i ], wrapper );
			
			ret[ i ] = kAH;
			wrapper.addView( knobs[ i ] ); //Add the view from knobs to the one in wrapper
		}
		
		return ret;
	}
	
	public void addKnobs( View[] knobs, String[] headers ) {
		
		ViewAdapter adapter = new ViewAdapter();
		
	    adapter.setViewArray( wrapKnobs( knobs, headers ) );
	    
	    knobHolder.setAdapter( adapter );
	}
	
	public void addDrumPads( View[] pads ) {
		
		this.pads = pads;
		
		ViewAdapter adapter = new ViewAdapter();
		
	    adapter.setViewArray( wrapPads( pads ) );
	    
	    drumPadHolder.setAdapter( adapter );
	}
	
	/**
	 * Wrap a list of knobs in linearLayouts 
	 * 
	 * @param knobs
	 * @return
	 */
	private View[] wrapPads( View[] pads ) {
		
		View[] ret = new View[ pads.length ];
		
		for( int i = 0; i < pads.length; i ++ ) {
			
			LinearLayout wrapper = new LinearLayout( context );
			wrapper.setLayoutParams( new AbsListView.LayoutParams( 40, 40 ) );
			ret[ i ] = wrapper;
			wrapper.addView( pads[ i ] ); //Add the view from knobs to the one in wrapper
		}
		
		return ret;
	}
	
	public void setPadActive( int id ) {
		
		if( pads == null ) {return;}
		
		for( int i = 0; i < pads.length; i ++ ) {
			
			( (DrumPadView) pads[ i ] ).setIsActive( false );
		}
		
		( (DrumPadView) pads[ id ] ).setIsActive( true );
	}
}