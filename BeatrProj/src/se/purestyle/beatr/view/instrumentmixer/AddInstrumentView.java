package se.purestyle.beatr.view.instrumentmixer;

import se.purestyle.beatr.view.InstrumentMixerView;
import se.purestyle.beatr.view.generic.IOverlay;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class AddInstrumentView extends LinearLayout implements IOverlay {

	public AddInstrumentView( Context context ) {
		
		super( context );
		
		setOrientation( VERTICAL );
		
		init( context );
	}

	private void init( Context context ) {
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams( 169, RelativeLayout.LayoutParams.WRAP_CONTENT );
		params.addRule( RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE );
		
		Typeface LHLine1Sans = Typeface.createFromAsset( context.getAssets(), "fonts/lhine1sansthin.ttf" );
		
		Drawable canvas = new Drawable() {
			
			@Override
			public void setColorFilter(ColorFilter cf) {
				
				
			}
			
			@Override
			public void setAlpha(int alpha) {
				
				
			}
			
			@Override
			public int getOpacity() {
				
				return 0;
			}
			
			@Override
			public void draw(Canvas canvas) {
				
				Paint bgPaint = new Paint();
				bgPaint.setColor( Color.parseColor( "#434343" ) );
				bgPaint.setAntiAlias( true );
				bgPaint.setAlpha( 150 );
				
				canvas.drawRoundRect( new RectF( 0, 0, 169, getHeight() - 10 ), 10, 10, bgPaint );
			}
		};
		
		setBackgroundDrawable( canvas );
		
		Button cancelButton = new Button( context );
		cancelButton.setTypeface( LHLine1Sans );
		cancelButton.setTextColor( Color.parseColor( "#F6FF00" ) );
		cancelButton.setBackgroundDrawable( null );
		cancelButton.setText( "X cancel" );
		cancelButton.setTag( InstrumentMixerView.CANCEL_ADD_INS_BUTTON );
		cancelButton.setLayoutParams( params );
		addView( cancelButton );
		
		Button synthButton = new Button( context );
		synthButton.setTypeface( LHLine1Sans );
		synthButton.setTextColor( Color.parseColor( "#F6FF00" ) );
		synthButton.setBackgroundDrawable( null );
		synthButton.setText( "synth" );
		synthButton.setTag( InstrumentMixerView.SYNTH_BUTTON );
		synthButton.setLayoutParams( params );
		addView( synthButton );
		
		Button drumButton = new Button( context );
		drumButton.setTypeface( LHLine1Sans );
		drumButton.setTextColor( Color.parseColor( "#F6FF00" ) );
		drumButton.setBackgroundDrawable( null );
		drumButton.setText( "drum" );
		drumButton.setTag( InstrumentMixerView.DRUM_BUTTON );
		drumButton.setLayoutParams( params );
		addView( drumButton );
		
		Button bassButton = new Button( context );
		bassButton.setTypeface( LHLine1Sans );
		bassButton.setTextColor( Color.parseColor( "#F6FF00" ) );
		bassButton.setBackgroundDrawable( null );
		bassButton.setText( "bass" );
		bassButton.setTag( InstrumentMixerView.BASS_BUTTON );
		bassButton.setLayoutParams( params );
		addView( bassButton );
		
		View v = new View( context );
		v.setLayoutParams( new LayoutParams( 0, 49 ) );
		addView( v );
	}
	
	@Override
	public void show() {
		
		setVisibility( VISIBLE );
	}

	@Override
	public void hide() {
		
		setVisibility( INVISIBLE );
	}
}