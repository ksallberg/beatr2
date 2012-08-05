package se.purestyle.beatr.view.editors;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

public class DrumEditorView extends LinearLayout {

	public DrumEditorView( Context context ) {
		
		super(context);
	}
	
	public void addKnob( View knob ) {
		
		addView( knob );
	}
}