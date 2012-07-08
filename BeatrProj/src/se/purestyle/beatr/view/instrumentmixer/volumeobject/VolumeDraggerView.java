package se.purestyle.beatr.view.instrumentmixer.volumeobject;

import se.purestyle.beatr.model.instrumentmixer.volumeobject.InstrumentModel;

import com.purestyle.amvc.model.IModel;
import com.purestyle.amvc.model.IModelUser;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
//import android.view.Display;
import android.view.View;
//import android.view.WindowManager;
import android.widget.LinearLayout;

public class VolumeDraggerView extends View implements IModelUser {
	
	private InstrumentModel model;
	
	public VolumeDraggerView( Context c ) {
		
		super( c );}
	
	@Override
	public void init() {
		
		setLayoutParams( new LinearLayout.LayoutParams( (int) model.getDrawToX(), 59 ) );
	}
	
	@Override
	public void onDraw( Canvas can ) {
		
		Paint p = new Paint();
		p.setColor( 0x00ff00 );
		p.setAlpha( 255 );
		
		can.clipRect( 0, 0, model.getDrawToX(), 100 );
		can.drawRect( 0, 0, model.getDrawToX(), 100, p );
	}

	@Override
	public void setModel( IModel model ) {
		
		this.model = (InstrumentModel) model;
	}
}