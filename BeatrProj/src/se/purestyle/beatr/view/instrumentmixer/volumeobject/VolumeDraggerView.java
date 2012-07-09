package se.purestyle.beatr.view.instrumentmixer.volumeobject;

import se.purestyle.beatr.model.instrumentmixer.volumeobject.InstrumentModel;
import se.purestyle.beatr.view.generic.AbstractVolumeView;

import com.purestyle.amvc.model.IModelUser;

import android.content.Context;
import android.graphics.Canvas;
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
		bg.setColor( 0x333333 );
		bg.setAlpha( 255 );
		
		can.clipRect( 0, 0, getWidth(), 100 );
		can.drawRect( 0, 0, getWidth(), 100, bg );
		
		can.clipRect( 0, 0, ((InstrumentModel) model).getDrawToX(), 100 );
		can.drawRect( 0, 0, ((InstrumentModel) model).getDrawToX(), 100, p );
	}
}