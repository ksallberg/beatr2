package se.purestyle.beatr.view.instrumentmixer.volumeobject;

import org.puredata.android.service.R;

import se.purestyle.beatr.model.instrumentmixer.volumeobject.InstrumentModel;
import se.purestyle.beatr.view.generic.AbstractVolumeView;

import com.purestyle.amvc.model.IModelUser;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.BlurMaskFilter.Blur;
import android.widget.LinearLayout;

public class VolumeDraggerView extends AbstractVolumeView implements IModelUser {
	
	private Context context;
	
	public VolumeDraggerView( Context context ) {
		
		super( context );
		
		this.context = context;
	}
	
	@Override
	public void init() {
		
		setLayoutParams( new LinearLayout.LayoutParams( (int) ((InstrumentModel) model).getDrawToX(), 59 ) );
	}
	
	@Override
	public void onDraw( Canvas can ) {
		
		//TODO: Make the bar-color selection in a nicer way, UGLY so far
		Paint p = new Paint();
		if( ( ( InstrumentModel ) model ).getInstrumentType().equals( InstrumentModel.DRUM ) ) {
			
			p.setColor( 0xe94700 );
		
		} else if ( ( ( InstrumentModel ) model ).getInstrumentType().equals( InstrumentModel.SYNTH ) ) {
			
			p.setColor( 0x642974 );
		}
		p.setAlpha( 255 );
		
		Paint bg = new Paint();
		bg.setColor( 0xd9d9d9 );
		bg.setAlpha( 255 );
		
		can.save();
		
		can.clipRect( 0, 0, getWidth(), 100 );
		can.drawRect( 0, 0, getWidth(), 100, bg );
		
		can.clipRect( 0, 0, ((InstrumentModel) model).getDrawToX(), 100 );
		can.drawRect( 0, 0, ((InstrumentModel) model).getDrawToX(), 100, p );
		
		can.restore();
		
		can.save();
		can.clipRect( 0, 0, getWidth(), 100 );
		
		Bitmap overlay = BitmapFactory.decodeResource( getResources(), R.drawable.volumebaroverlay );
		Matrix overlayMatrix = new Matrix();
		overlayMatrix.setScale( 8, 1 );
		can.drawBitmap( overlay, overlayMatrix, null );
		
		can.restore();
		
		//Draw the text on top of everything
		Paint textPaint = new Paint();
		textPaint.setAntiAlias( true );
		textPaint.setColor( Color.parseColor( "#ffffff" ) );
		
		Typeface fvAlmelo = Typeface.createFromAsset( context.getAssets(), "fonts/fvalmelo.ttf" );
		textPaint.setTypeface( fvAlmelo );
		textPaint.setTextSize( 25 );
		textPaint.setMaskFilter( new BlurMaskFilter( 2, Blur.OUTER ) );
		textPaint.setAlpha( 1000 );
		
		InstrumentModel mod = ((InstrumentModel) model);
		
		for( int i = 0; i < 4; i ++ ) {
			
			can.drawText( mod.getInstrumentType() + ": " + mod.getName(), 10, 39, textPaint );
		}
	}
}