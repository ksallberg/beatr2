package se.purestyle.beatr.view.editors;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BassEditorView extends LinearLayout {

	private LinearLayout twoDimensionalSliderHolder;
	
	private Button recordButton;
	private Button stopRecordingButton;
	
	private LinearLayout recordHolder;
	private TextView timeDisplay;
	
	private Activity activity;
	
	private boolean recordButtonShowing = true;
	
	public static final String RECORD_BUTTON = "recordButton";
	public static final String STOP_RECORDING_BUTTON = "stopRecordingButton";
	
	public BassEditorView( Context context, Activity activity ) {
		
		super( context );
		
		setLayoutParams( new LayoutParams( LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT ) );
		setOrientation( LinearLayout.VERTICAL );
		
		this.activity = activity;
		
		recordButton = new Button( context );
		recordButton.setTag( RECORD_BUTTON );
		recordButton.setText( "record" );
		
		stopRecordingButton = new Button( context );
		stopRecordingButton.setTag( STOP_RECORDING_BUTTON );
		stopRecordingButton.setText( "stop recording" );
		
		//Text editor
		TextView txt = new TextView( context );
		txt.setTextColor( Color.WHITE );
		txt.setText( "Synth editor" );
		addView( txt );
		
		//Two dimensional slider
		twoDimensionalSliderHolder = new LinearLayout( context );
		twoDimensionalSliderHolder.setLayoutParams( new LayoutParams( LayoutParams.FILL_PARENT, 225 ) );
		addView( twoDimensionalSliderHolder );
		
		recordHolder = new LinearLayout( context );
		addView( recordHolder );
		
		recordHolder.addView( recordButton );
		
		timeDisplay = new TextView( context );
		timeDisplay.setText( "0:00" );
		addView( timeDisplay );
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