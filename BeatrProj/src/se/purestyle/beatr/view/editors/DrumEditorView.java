package se.purestyle.beatr.view.editors;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

public class DrumEditorView extends LinearLayout {

	private Context context;
	
	public DrumEditorView( Context context ) {
		
		super( context );
		
		this.context = context;
	}
	
	public void addKnob( View knob ) {
		
		LinearLayout wrapper = new LinearLayout( context );
		wrapper.setLayoutParams( new LayoutParams( 50, 50 ) );
		wrapper.addView( knob );
		
		addView( wrapper );
	}
}