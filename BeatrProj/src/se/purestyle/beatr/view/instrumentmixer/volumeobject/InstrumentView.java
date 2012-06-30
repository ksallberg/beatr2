package se.purestyle.beatr.view.instrumentmixer.volumeobject;


import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class InstrumentView extends LinearLayout {

	public InstrumentView( Context context ) {
	
		super( context );
		
		setOrientation( HORIZONTAL );
		
		Button btn1 = new Button( context );
		btn1.setText("oooooo");
		View bv = new VolumeDraggerView( context );
		Button btn2 = new Button( context );
		btn2.setText("uuuuuu");
		
		addView( btn1 );
		addView( bv );
		addView( btn2 );
	}
}