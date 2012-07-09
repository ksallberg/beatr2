package se.purestyle.beatr.view.instrumentmixer;

import org.puredata.android.service.R;

import se.purestyle.beatr.model.instrumentmixer.MasterVolumeModel;
import se.purestyle.beatr.view.generic.AbstractVolumeView;

import com.purestyle.amvc.model.IModelUser;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.ViewGroup.LayoutParams;

public class MasterVolumeView extends AbstractVolumeView implements IModelUser {

	private float totalWidth = 212;
	private float totalHeight = 43;
	
	public MasterVolumeView( Context context ) {
		
		super( context );
		
		setLayoutParams( new LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT ) );
	}
	
	@Override
	public void onDraw( Canvas can ) {
		
		//Background
		Bitmap bmp = BitmapFactory.decodeResource( getResources(), R.drawable.mastervolumebg );
		can.drawBitmap( bmp, new Matrix(), null );
		
		Paint p = new Paint();
		p.setColor( Color.parseColor( "#40e8de" ) );
		
		can.clipRect( 0, 0, totalWidth, totalHeight );
		can.drawRect( 0, 0, ( ( MasterVolumeModel ) model ).getDrawToX(), totalHeight, p );
	}
}