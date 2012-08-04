package se.purestyle.beatr.view.generic;

import se.purestyle.beatr.model.generic.DrumPadModel;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class DrumPadView extends View {

	private DrumPadModel model;
	
	public DrumPadView( Context context ) {
		
		super( context );
	}
	
	public void setModel( DrumPadModel model ) {
		
		this.model = model;
	}
	
	@Override
	public void onDraw( Canvas canvas ) {
		
		Paint bgPaint = new Paint();
		bgPaint.setAntiAlias( true );
		
		if( model.getIsActive() ) {
			
			bgPaint.setColor( Color.parseColor( "#f6ff00" ) );
		
		} else {
			
			bgPaint.setColor( Color.parseColor( "#434343" ) );
		}
		
		canvas.drawRoundRect( new RectF( 0, 0, getWidth(), getHeight() ), 10, 10, bgPaint );
		
		Paint dotPaint = new Paint();
		dotPaint.setAntiAlias( true );
		
		//If should play, draw a yellow dot in the left top corner
		if( model.getShouldPlay() ) {
			
			dotPaint.setColor( Color.parseColor( "#f6ff00" ) );
			
		//Draw the dot black
		} else {
			
			dotPaint.setColor( Color.parseColor( "#000000" ) );
		}
		
		canvas.drawCircle( getWidth() - 20, 20, 7, dotPaint );
	}
}