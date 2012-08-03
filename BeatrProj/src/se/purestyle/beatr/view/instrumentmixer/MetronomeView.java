package se.purestyle.beatr.view.instrumentmixer;

import org.puredata.android.service.R;

import se.purestyle.beatr.model.instrumentmixer.MetronomeModel;
import se.purestyle.beatr.view.generic.AbstractVolumeView;

import com.purestyle.amvc.model.IModelUser;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;

public class MetronomeView extends AbstractVolumeView implements IModelUser {

	public static final float TOTAL_WIDTH = 212;
	public static final float TOTAL_HEIGHT = 43;
	
	private Context context;
	
	private MetronomeModel model;
	
	public MetronomeView( Context context ) {
		
		super( context );
		
		this.context = context;
	}
	
	/**
	 * To be able to set the model, so the view can ask the model for data 
	 * 
	 * @param model
	 */
	public void setModel( MetronomeModel model ) {
		
		this.model = model;
	}
	
	@Override
	public void onDraw( Canvas can ) {
		
		//Background
		Bitmap bmp = BitmapFactory.decodeResource( getResources(), R.drawable.mastervolumebg );
		can.drawBitmap( bmp, new Matrix(), null );
		
		can.save();
		
		Paint p = new Paint();
		p.setAntiAlias( true );
		p.setColor( Color.parseColor( "#40e8de" ) );
		
		Path path = new Path();
		path.addRoundRect( new RectF( 0, 0, TOTAL_WIDTH, TOTAL_HEIGHT ), 7, 7, Path.Direction.CW );
		can.clipPath( path );
		can.clipRect(0, 0, ( ( MetronomeModel ) model ).getDrawToX(), TOTAL_HEIGHT );
		
		Bitmap foreGroundBmp = BitmapFactory.decodeResource( getResources(), R.drawable.mastervolumefg );
		Matrix m = new Matrix();
		m.setScale( 7, 1 );
		can.drawBitmap( foreGroundBmp, m, null );
		
		//Clip for the text
		can.restore();
		
		Paint textPaint = new Paint();
		textPaint.setAntiAlias( true );
		textPaint.setColor( Color.parseColor( "#ffffff" ) );
		
		Typeface fvAlmelo = Typeface.createFromAsset( context.getAssets(), "fonts/fvalmelo.ttf" );
		textPaint.setTypeface( fvAlmelo );
		textPaint.setTextSize( 25 );
		textPaint.setMaskFilter( new BlurMaskFilter( 2, Blur.OUTER ) );
		textPaint.setAlpha( 1000 );
		
		can.drawText( "mtrnm bmp: " + model.currentBmp(), 10, 31, textPaint );
		can.drawText( "mtrnm bmp: " + model.currentBmp(), 10, 31, textPaint );
		can.drawText( "mtrnm bmp: " + model.currentBmp(), 10, 31, textPaint );
		can.drawText( "mtrnm bmp: " + model.currentBmp(), 10, 31, textPaint );
	}
}