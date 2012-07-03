package se.purestyle.beatr.view.instrumentmixer;

import android.content.Context;
import android.graphics.Color;
import android.widget.Button;
import android.widget.RelativeLayout;

public class AddInstrumentView extends RelativeLayout {

	public AddInstrumentView( Context context ) {
		
		super( context );
		
		setBackgroundColor( Color.BLUE );
		
		Button btn1 = new Button( context );
		btn1.setText("Button1!");
		Button btn2 = new Button( context );
		btn2.setText("Button2!");
		
		addView( btn1 );
		addView( btn2 );
	}
}
