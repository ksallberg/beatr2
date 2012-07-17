package se.purestyle.beatr;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import se.purestyle.beatr.controller.InstrumentMixerController;
import se.purestyle.beatr.controller.instrumentmixer.volumeobject.InstrumentController;
import se.purestyle.beatr.helpers.FileModifier;
import se.purestyle.beatr.helpers.PdConnector;
import se.purestyle.beatr.model.FemaleNames;
import se.purestyle.beatr.view.InstrumentMixerView;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public class BeatrActivity extends Activity implements PropertyChangeListener {
	
	//Main controller
	private InstrumentMixerController mixerController;
	
	private static PdConnector conn;
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		
		//Super to whatever the android framework does in activity
		super.onCreate( savedInstanceState );
		
		//Remove the standard top bar stating the application name, I use a logo instead
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		
		conn = new PdConnector( getApplicationContext(), this );
		
		//Initialize the FileModifier, just need to give it an activity and context to work within
		FileModifier.init( this, getApplicationContext() );
		
		//Give ResourceManager a context
		ResourceManager.setContext( getApplicationContext() );
		
		FemaleNames.populate();
		
		mixerController = new InstrumentMixerController();
		mixerController.setup();
		mixerController.addListener( this );
		
		setContentView( mixerController.getViews().get( InstrumentMixerController.INSTRUMENT_MIXER_VIEW ).getViews().get( InstrumentMixerView.START_VIEW ) );
	}
	
	@Override
	protected void onDestroy() {
		
		super.onDestroy();
		
		conn.cleanup();
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		
		if( event.getPropertyName().equals( InstrumentController.EDIT_EVENT ) ) {
			
			
			Log.i( "BeatrActivity", "" + event.getNewValue() );
		}
	}
}