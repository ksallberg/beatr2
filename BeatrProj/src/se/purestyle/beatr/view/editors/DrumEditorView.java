package se.purestyle.beatr.view.editors;

import se.purestyle.beatr.view.generic.ViewAdapter;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
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
		
		this.setBackgroundColor( Color.GREEN );
		
		//Create the drum pad holder
		drumPadHolder = new GridView( context ); //(GridView) findViewById( R.id.gridview );
		//drumPadHolder.setLayoutParams( new ViewGroup.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT ) );
		drumPadHolder.setBackgroundColor( Color.WHITE );
		
		//Create the knob holder
		knobHolder = new GridView( context ); //(GridView) findViewById( R.id.gridview );
		knobHolder.setLayoutParams( new ViewGroup.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT ) );
		knobHolder.setBackgroundColor( Color.MAGENTA );
		knobHolder.setColumnWidth(90);
		knobHolder.setNumColumns( GridView.AUTO_FIT );
		knobHolder.setGravity( Gravity.CENTER );
		
//		addView( drumPadHolder );
		addView( knobHolder );
	}
	
	public void addKnobs( View[] knobs ) {
		
		Log.i( "DrumEditorView", "addKnobs!" );
		
		ViewAdapter adapter = new ViewAdapter();
		
		LinearLayout layout = new LinearLayout( context );
    	layout.setLayoutParams( new AbsListView.LayoutParams( 75, 75 ) );
    	layout.setBackgroundColor( Color.RED );
    	
    	LinearLayout layout2 = new LinearLayout( context );
    	layout2.setLayoutParams( new AbsListView.LayoutParams( 75, 75 ) );
    	layout2.setBackgroundColor( Color.BLUE );
    	
    	LinearLayout layout3 = new LinearLayout( context );
    	layout3.setLayoutParams( new AbsListView.LayoutParams( 75, 75 ) );
    	layout3.setBackgroundColor( Color.CYAN );
    	
    	LinearLayout layout4 = new LinearLayout( context );
    	layout4.setLayoutParams( new AbsListView.LayoutParams( 75, 75 ) );
    	layout4.setBackgroundColor( Color.YELLOW );
    	
    	View[] knobssss = new View[] { layout, layout2, layout3, layout4 };
		
	    adapter.setViewArray( knobssss );
	    
	    knobHolder.setAdapter( adapter );
	}
	
	public void addDrumPads( View[] pads ) {
		
		ViewAdapter adapter = new ViewAdapter();
	    adapter.setViewArray( pads );
	    
	    knobHolder.setAdapter( adapter );
	}
}