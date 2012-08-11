package se.purestyle.beatr.view.info;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;

public class MainMenuButtonView extends View {

	private String name;
	
	private Context context;
	
	public MainMenuButtonView( Context context ) {
		
		super( context );
		
		this.context = context;
	}
	
	public void setName( String name ) {
		
		this.name = name;
	}
	
	@Override
	public void onDraw( Canvas canvas ) {
		
		Paint bgColor = new Paint();
		bgColor.setColor( Color.parseColor( "#434343" ) );
		bgColor.setAlpha( 200 );
		bgColor.setAntiAlias( true );
		
		canvas.drawCircle( getWidth() / 2, getHeight() / 2, getWidth() / 2, bgColor );
		
		//Create typeface
		Typeface LHLine1Sans = Typeface.createFromAsset( context.getAssets(), "fonts/lhine1sansthin.ttf" );
		
		Paint textColor = new Paint();
		textColor.setColor( Color.parseColor( "#f6ff00" ) );
		textColor.setAntiAlias( true );
		textColor.setTypeface( LHLine1Sans );
		textColor.setTextSize( getWidth() / 5 );
		
		canvas.drawText( name, getWidth() / 2 - textColor.measureText( name ) / 2, Math.round( getHeight() / 1.75 ), textColor );
	}
}