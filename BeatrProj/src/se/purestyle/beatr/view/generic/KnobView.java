package se.purestyle.beatr.view.generic;

import se.purestyle.beatr.model.generic.KnobModel;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;
import android.widget.RelativeLayout;

public class KnobView extends View {
	
	private KnobModel model;
	
	public KnobView( Context context ) {
		
		super( context );
		
		setLayoutParams( new RelativeLayout.LayoutParams(100,100) );
	}
	
	public void setModel( KnobModel model ) {
		
		this.model = model;
	}
	
	@Override
	public void onDraw( Canvas canvas ) {
		
		Paint p = new Paint();
		p.setColor( Color.parseColor( "#434343" ) );
		p.setAntiAlias( true );
		
		canvas.drawCircle( getWidth() / 2, getHeight() / 2, 50, p ); //( 0, 0, getWidth(), getHeight(), p );
		
		Paint linePaint = new Paint();
		linePaint.setColor( Color.parseColor( "#00ffe4" ) );
		linePaint.setAlpha( 200 );
		linePaint.setAntiAlias( true );
		
		//Draw a line from the center to where the mouse is
//		canvas.drawLine( model.getPointOfCentre().x, model.getPointOfCentre().y, model.getXYCoords().x, model.getXYCoords().y, linePaint );
		
		canvas.save();
		
		Matrix m = new Matrix();
		m.reset();
		m.preTranslate( getWidth() * 0.75f, getHeight() * 1.5f );
		m.preRotate( model.getCurrentAngle() + 45 );
		
		canvas.setMatrix( m );
		
		canvas.drawCircle( 0, getHeight() / 2, 10, linePaint );
		
		//TODO: Remove debuglines
//		canvas.drawLine( 0, 0, 0, 100, linePaint );
		
		canvas.restore();
	}
}