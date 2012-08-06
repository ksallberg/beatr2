package se.purestyle.beatr.view.editors;

import se.purestyle.beatr.view.generic.ViewAdapter;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.LinearLayout;

public class DrumEditorView extends LinearLayout {

	private GridView drumPadHolder;
	private GridView knobHolder;
	
	private Context context;
	
	public DrumEditorView( Context context ) {
		
		super( context );
		
		this.context = context;
		
		setLayoutParams( new LayoutParams( LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT ) );
		
		//Create the drum pad holder
		drumPadHolder = new GridView( context );
		drumPadHolder.setLayoutParams( new ViewGroup.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT ) );
		drumPadHolder.setColumnWidth( 90 );
		drumPadHolder.setVerticalSpacing( 10 );
		drumPadHolder.setNumColumns( GridView.AUTO_FIT );
		drumPadHolder.setBackgroundColor( Color.WHITE );
		
		//Create the knob holder
		knobHolder = new GridView( context );
		knobHolder.setLayoutParams( new ViewGroup.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT ) );
		knobHolder.setColumnWidth( 90 );
		knobHolder.setVerticalSpacing( 10 );
		knobHolder.setNumColumns( GridView.AUTO_FIT );
		knobHolder.setGravity( Gravity.CENTER );

		addView( knobHolder );
		addView( drumPadHolder );
	}
	
	/**
	 * Wrap a list of knobs in linearLayouts 
	 * 
	 * @param knobs
	 * @return
	 */
	private View[] wrapKnobs( View[] knobs ) {
		
		View[] ret = new View[ knobs.length ];
		
		for( int i = 0; i < knobs.length; i ++ ) {
			
			LinearLayout wrapper = new LinearLayout( context );
			wrapper.setLayoutParams( new AbsListView.LayoutParams( 65, 65 ) );
			ret[ i ] = wrapper;
			wrapper.addView( knobs[ i ] ); //Add the view from knobs to the one in wrapper
		}
		
		return ret;
	}
	
	public void addKnobs( View[] knobs ) {
		
		ViewAdapter adapter = new ViewAdapter();
		
	    adapter.setViewArray( wrapKnobs( knobs ) );
	    
	    knobHolder.setAdapter( adapter );
	}
	
	public void addDrumPads( View[] pads ) {
		
		ViewAdapter adapter = new ViewAdapter();
		
	    adapter.setViewArray( pads );
	    
	    drumPadHolder.setAdapter( adapter );
	}
}