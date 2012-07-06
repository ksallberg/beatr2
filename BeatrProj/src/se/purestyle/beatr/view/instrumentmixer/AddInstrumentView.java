package se.purestyle.beatr.view.instrumentmixer;

import se.purestyle.beatr.view.generic.IOverlay;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class AddInstrumentView extends LinearLayout implements IOverlay {

	public AddInstrumentView( Context context ) {
		
		super( context );
		
		setOrientation( VERTICAL );
		setBackgroundColor( Color.BLUE );
		
		init( context );
	}

	private void init( Context context ) {
		
		Button btn1 = new Button( context );
		btn1.setText("Button1!");
		
		Button btn2 = new Button( context );
		btn2.setText("Button2!");
		
		Button btn3 = new Button( context );
		btn3.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				hide();
			}
		});
		btn3.setText("Button3!");
		
		Button btn4 = new Button( context );
		btn4.setText("Button4!");
		
		Button btn5 = new Button( context );
		btn5.setText("Button5!");
		
		addView( btn1 );
		addView( btn2 );
		addView( btn3 );
		addView( btn4 );
		addView( btn5 );
	}
	
	@Override
	public void show() {
		
		setVisibility( VISIBLE );
	}

	@Override
	public void hide() {
		
		setVisibility( INVISIBLE );
	}
}