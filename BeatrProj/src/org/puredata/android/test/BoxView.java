package org.puredata.android.test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.widget.LinearLayout;

public class BoxView extends View {

	private int color;
	private float atX;
	private float atY;
	
	public BoxView(Context context, int color, float atX, float atY) {
	
		super(context);
		
		this.color = color;
		this.atX = atX;
		this.atY = atY;
		
		setLayoutParams( new LinearLayout.LayoutParams( 100,100 ) );
		
		setBackgroundColor(0xff0000);
	}
	
	@Override
	public void onDraw( Canvas can ) {
		
		Paint p = new Paint();
		p.setColor( color );
		p.setAlpha( 255 );
		
		can.clipRect( 0, 0, 100, 100);
		can.drawRect( 0, 0, 100, 100, p );
	}
}