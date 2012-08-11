package se.purestyle.beatr.view.editors;

import se.purestyle.beatr.R;
import se.purestyle.beatr.view.generic.KnobAndHeader;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SynthEditorView extends LinearLayout {

	private LinearLayout twoDimensionalSliderHolder;
	
	private Button recordButton;
	private Button stopRecordingButton;
	
	private LinearLayout recordHolder;
	private TextView timeDisplay;
	
	private Activity 	activity;
	private Context 	context;
	
	private boolean recordButtonShowing = true;
	
	private LinearLayout 	knobHolder;
	private LinearLayout 	filterHolder;
	
	public static final String RECORD_BUTTON = "recordButton";
	public static final String STOP_RECORDING_BUTTON = "stopRecordingButton";
	
	public SynthEditorView( Context context, Activity activity ) {
		
		super( context );
		
		setLayoutParams( new LayoutParams( LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT ) );
		setOrientation( LinearLayout.VERTICAL );
		
		this.activity 	= activity;
		this.context 	= context;
	}
	
	public void init() {
		
		//Create typeface
		Typeface LHLine1Sans = Typeface.createFromAsset( context.getAssets(), "fonts/lhine1sansthin.ttf" );
		
		recordButton = new Button( context );
		recordButton.setTag( RECORD_BUTTON );
		recordButton.setBackgroundResource( R.drawable.record );
		
		stopRecordingButton = new Button( context );
		stopRecordingButton.setTag( STOP_RECORDING_BUTTON );
		stopRecordingButton.setBackgroundResource( R.drawable.stoprecording );
		
		//Params for the view header 
		LayoutParams headerParams = new LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT );
		headerParams.setMargins( 10, 0, 0, 0 );
		
		//View header
		TextView header = new TextView( context );
		header.setTextColor( Color.parseColor( "#F6FF00" ) );
		header.setTypeface( LHLine1Sans );
		header.setText( "synth : edit mode" );
		header.setLayoutParams( headerParams );
		addView( header );
		
		//Two dimensional slider
		twoDimensionalSliderHolder = new LinearLayout( context );
		twoDimensionalSliderHolder.setLayoutParams( new LayoutParams( LayoutParams.FILL_PARENT, 210 ) );
		addView( twoDimensionalSliderHolder );
		
		//Holder to hold the record btn and the time display
		RelativeLayout subHolder = new RelativeLayout( context );
		subHolder.setPadding( 10, 0, 10, 0 );
		addView( subHolder );
		
		recordHolder = new LinearLayout( context );
		subHolder.addView( recordHolder );
		
		recordHolder.addView( recordButton );
		
		RelativeLayout.LayoutParams timeDisplayParams = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
		timeDisplayParams.addRule( RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE );
		
		timeDisplay = new TextView( context );
		timeDisplay.setTextColor( Color.parseColor( "#F6FF00" ) );
		timeDisplay.setTypeface( LHLine1Sans );
		timeDisplay.setTextSize( 47 );
		timeDisplay.setText( "0:0" );
		timeDisplay.setLayoutParams( timeDisplayParams );
		subHolder.addView( timeDisplay );
		
		LayoutParams filterHeaderParams = new LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT );
		filterHeaderParams.setMargins( 10, 5, 0, 5 );
		
		//Text editor
		TextView filterHeader = new TextView( context );
		filterHeader.setTextColor( Color.parseColor( "#F6FF00" ) );
		filterHeader.setTypeface( LHLine1Sans );
		filterHeader.setText( "synth : filters: " );
		filterHeader.setLayoutParams( filterHeaderParams );
		addView( filterHeader );
		
		filterHolder = new LinearLayout( context );
		filterHolder.setLayoutParams( new LayoutParams( LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT ) );
		filterHolder.setOrientation( LinearLayout.VERTICAL );
		filterHolder.setGravity( Gravity.CENTER );
		addView( filterHolder );
		
		knobHolder = new LinearLayout( context );
		knobHolder.setLayoutParams( new LayoutParams( 65, 65 ) );
		
		KnobAndHeader knobAndHeader = new KnobAndHeader( context );
		knobAndHeader.setContent( "dry/wet", knobHolder );
		filterHolder.addView( knobAndHeader );
	}
	
	public void addAttackKnob( View attackKnob ) {
		
		knobHolder.addView( attackKnob );
	}
	
	/**
	 * This is very adapted and local to this View. I need a slider and I need to add it to this layout
	 * The slider itself is created in the controller
	 */
	public void addTwoDimensionalSlider( View slider ) {
		
		twoDimensionalSliderHolder.addView( slider );
	}
	
	/**
	 * If the record button is showing, toggle and show the stop recording button
	 * otherwise do it the other way around
	 */
	public void toggleRecord() {
		
		if( recordButtonShowing ) {
			
			recordHolder.removeView( recordButton );
			recordHolder.addView( stopRecordingButton );
			recordButtonShowing = false;
		
		} else {
			
			recordHolder.removeView( stopRecordingButton );
			recordHolder.addView( recordButton );
			recordButtonShowing = true;
		}
	}
	
	public View getRecordButton() {

		return recordButton;
	}
	
	public View getStopRecordingButton() {
		
		return stopRecordingButton;
	}
	
	public void setTimeDisplayContext( final long time ) {
		
		activity.runOnUiThread( new Runnable() {
			
				@Override
				public void run() {
					
					int milliseconds = Math.round( time % 1000 );
					int seconds = (int) ( time - milliseconds ) / 1000;
					
					timeDisplay.setText( seconds + ":" + milliseconds );
				}
			}
		);
	}
}