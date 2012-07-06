package se.purestyle.beatr;

import se.purestyle.beatr.controller.InstrumentMixerController;
import se.purestyle.beatr.view.InstrumentMixerView;
import android.app.Activity;
import android.util.Log;
import android.view.Window;

public class BeatrActivity extends Activity {
	
	//Main controller
	InstrumentMixerController mixerController;
	
	@Override
	protected void onCreate( android.os.Bundle savedInstanceState ) {
		
		super.onCreate( savedInstanceState );
		
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		
		Log.i( "getApplicationContext(): ", "" + getApplicationContext() );
		
		//Give ResourceManager a context
		ResourceManager.setContext( getApplicationContext() );
		
		mixerController = new InstrumentMixerController();
		mixerController.setup();
		
		Log.i( "BeatrActivity", "onCreate!" );
		
		setContentView( mixerController.getViews().get( InstrumentMixerController.INSTRUMENT_MIXER_VIEW ).getViews().get( InstrumentMixerView.START_VIEW ) );
	}
}