package se.purestyle.beatr.view.instrumentmixer.volumeobject;

import org.puredata.android.service.R;

import se.purestyle.beatr.model.instrumentmixer.volumeobject.InstrumentModel;
import se.purestyle.beatr.view.generic.AbstractVolumeView;

import com.purestyle.amvc.model.IModelUser;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.widget.LinearLayout;

public class VolumeDraggerView extends AbstractVolumeView implements IModelUser {
	
	public VolumeDraggerView( Context c ) {
		
		super( c );
	}
	
	@Override
	public void init() {
		
		setLayoutParams( new LinearLayout.LayoutParams( (int) ((InstrumentModel) model).getDrawToX(), 59 ) );
	}
	
	@Override
	public void onDraw( Canvas can ) {
		
		Paint p = new Paint();
		p.setColor( 0x00ff00 );
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
	}
}