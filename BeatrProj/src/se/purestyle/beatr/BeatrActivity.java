package se.purestyle.beatr;

import java.util.HashMap;
import java.util.Map;

import se.purestyle.beatr.controller.InstrumentMixerController;
import se.purestyle.beatr.helpers.FileModifier;
import se.purestyle.beatr.helpers.PdConnector;
import se.purestyle.beatr.model.FemaleNames;
import se.purestyle.beatr.view.InstrumentMixerView;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class BeatrActivity extends Activity {
	
	//Main controller
	InstrumentMixerController mixerController;
	
	private static PdConnector conn;
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		
		//Super to whatever the android framework does in activity
		super.onCreate( savedInstanceState );
		
		//Remove the standard top bar stating the application name, I use a logo instead
		requestWindowFeature( Window.FEATURE_NO_TITLE );
		
		Map<String, String> replacementMap = new HashMap<String, String>();
		replacementMap.put( "right", "heheheh" );
		
		//Before starting the pd connection
		FileModifier.createIndividualizedFile( this, getApplicationContext(), replacementMap, "pdfiles/smalletst.pd" );
		
		conn = new PdConnector( getApplicationContext(), this );
		
		//Give ResourceManager a context
		ResourceManager.setContext( getApplicationContext() );
		
		FemaleNames.populate();
		
		mixerController = new InstrumentMixerController();
		mixerController.setup();
		
		setContentView( mixerController.getViews().get( InstrumentMixerController.INSTRUMENT_MIXER_VIEW ).getViews().get( InstrumentMixerView.START_VIEW ) );
	}
	
	public static void addDrum() {
		
		conn.addDrum();
	}
	
	public static void addSynth() {
		
		conn.addSynth();
	}
	
	@Override
	protected void onDestroy() {
		
		super.onDestroy();
		
		conn.cleanup();
	};
}