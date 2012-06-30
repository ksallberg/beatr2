package se.purestyle.beatr.view.instrumentmixer.volumeobject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class VolumeDraggerView extends View {

	float drawToX = 100;
	
	public VolumeDraggerView(Context c) {
		
		super( c );
		
		setLayoutParams( new LinearLayout.LayoutParams( 100,100 ) );
		
		setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				drawToX = event.getRawX() - getLeft();
				
				Log.i("BOXVIEWCLICKABLE", "msg" + drawToX );
				
				if ( event.getAction() == MotionEvent.ACTION_UP ) {
					
					return false;
				}
				
				postInvalidate();
				
				return true;
			}
		});
	}
	
	@Override
	public void onDraw( Canvas can ) {
		
		Log.i("BOXVIEWCLICKABLE", "onDraw!" );
		
		Paint p = new Paint();
		p.setColor( 0x00ff00 );
		p.setAlpha( 255 );
		
		can.clipRect( 0, 0, drawToX, 100);
		can.drawRect( 0, 0, drawToX, 100, p );
	}
}