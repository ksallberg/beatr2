package se.purestyle.beatr.view.generic;

import se.purestyle.beatr.model.generic.KnobModel;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class KnobView extends View {
	
	private KnobModel model;
	
	public KnobView( Context context ) {
		
		super( context );
		
		setLayoutParams( new LayoutParams( LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT ) );
	}
	
	public void setModel( KnobModel model ) {
		
		this.model = model;
	}
	
	@Override
	public void onDraw( Canvas canvas ) {
		
		Paint p = new Paint();
		p.setColor( Color.parseColor( "#434343" ) );
		p.setAntiAlias( true );
		
		canvas.drawCircle( getWidth() / 2, getHeight() / 2, getWidth() / 2, p );
		
		Paint linePaint = new Paint();
		linePaint.setColor( Color.parseColor( "#00ffe4" ) );
		linePaint.setAntiAlias( true );
		linePaint.setAlpha( 200 );
		
		//Save the current matrix state to be able to modify and go back here
		canvas.save();
		
		//The matrix to be able to rotate through the centerpoint of the view rather than (0,0)
		Matrix m = new Matrix();
		m.reset();
		m.preTranslate( (getWidth() / 2 ), (getHeight() / 2 ) );
		m.preRotate( model.getCurrentAngle() + 45 );
		canvas.concat(m);
		canvas.drawCircle( 0, getHeight() / 3, getWidth() / 15, linePaint );
		
		//Go back to the state 
		canvas.restore();
	}
}