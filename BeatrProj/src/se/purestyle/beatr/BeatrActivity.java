package se.purestyle.beatr;

import se.purestyle.beatr.controller.InstrumentMixerController;
import se.purestyle.beatr.model.FemaleNames;
import se.purestyle.beatr.puredataconnections.PureDataProxy;
import se.purestyle.beatr.view.InstrumentMixerView;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public class BeatrActivity extends Activity {
	
	//Main controller
	InstrumentMixerController mixerController;
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		
		//Super to whatever the android framework does in activity
		super.onCreate( savedInstanceState );
		
		//Remove the standard top bar stating the application name, I use a logo instead
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		
		PureDataProxy.createInstance( getApplicationContext(), this );
		
		//Give ResourceManager a context
		ResourceManager.setContext( getApplicationContext() );
		FemaleNames.populate();
		
		mixerController = new InstrumentMixerController();
		mixerController.setup();
		
		Log.i( "BeatrActivity", "onCreate!" );
		
		setContentView( mixerController.getViews().get( InstrumentMixerController.INSTRUMENT_MIXER_VIEW ).getViews().get( InstrumentMixerView.START_VIEW ) );
	}
	
	@Override
	protected void onDestroy() {
		
		super.onDestroy();
		
		PureDataProxy.getInstance().destroy();
	};
}