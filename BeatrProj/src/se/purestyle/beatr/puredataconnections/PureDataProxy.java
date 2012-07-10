package se.purestyle.beatr.puredataconnections;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.puredata.android.service.PdPreferences;
import org.puredata.android.service.PdService;
import org.puredata.core.PdBase;
import org.puredata.core.PdReceiver;
import org.puredata.core.utils.IoUtils;

import se.purestyle.beatr.BeatrActivity;
import se.purestyle.beatr.R;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

public class PureDataProxy implements SharedPreferences.OnSharedPreferenceChangeListener {

	private static PureDataProxy instance = null;
	
	//Cannot do a normal singletone since this class requires a context and an activity
	public static void createInstance( Context context, Activity activity ) {
		
		if( instance == null ) {
			
			instance = new PureDataProxy( context, activity );
		}
	}
	
	//instance must be instantiated before this method can be run
	public static PureDataProxy getInstance() {
		
		if( instance == null ) {
			
			throw new RuntimeException( "Singleton not instantiated!" );
		
		} else {
			
			return instance; 
		}
	}
	
	private PdService			pdService = null; //instantiated by the ServiceConnection pdConnection
	
	//Stuff that I take to here just to not need to have synth proxy/communication code in the Activity subclass
	private Activity			activity;
	
	private final ServiceConnection pdConnection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected( ComponentName name ) {} //Not used by PD
		
		@Override
		public void onServiceConnected( ComponentName name, IBinder service ) {
			
			pdService = ( ( PdService.PdBinder ) service ).getService();
			initPd();
		}
	};
	
	private void initPd() {
		
		Resources res = activity.getResources();
		File patchFile = null;
		
		try {
			
			//Set receiver for the pure data communication
			PdBase.setReceiver( receiver );
			PdBase.subscribe( "android" ); //Tell pd this is an android device
			
			InputStream in = res.openRawResource( R.raw.twosawsandfilter ); //Get raw data from the pd synth file
			
			patchFile = IoUtils.extractResource( in, "twosawsandfilter.pd", activity.getCacheDir() ); //extract the pd file
			
			PdBase.openPatch( patchFile );
			
			startAudio();
		
		} catch( IOException e ) {
			
			Log.e( "PureDataProxy", e.toString() );
			activity.finish();
		
		} finally {
			
			if( patchFile != null ) {
				
				patchFile.delete();
			}
		}
	}
	
	private void startAudio() {
		
		String name = activity.getResources().getString( R.string.app_name );
		
		try {
			
			pdService.initAudio( -1, -1, -1, -1 ); //Negative values will be replaced
			pdService.startAudio( new Intent( activity, BeatrActivity.class ), R.drawable.icon, name, "return to " + name );
		
		} catch( IOException e ) {
			
			Log.e( "PureDataProxy", e.toString() );
		}
	}
	
	private PdReceiver receiver = new PdReceiver() {

		private void pdPost( String msg ) {
			
//			toast("Pure Data says, \"" + msg + "\"");
		}

		@Override
		public void print( String s ) {
			
//			post(s);
		}

		@Override
		public void receiveBang( String source ) {
			
			pdPost("bang");
		}

		@Override
		public void receiveFloat( String source, float x ) {
			
			pdPost("float: " + x);
		}

		@Override
		public void receiveList( String source, Object... args ) {
			
			pdPost("list: " + Arrays.toString(args));
		}

		@Override
		public void receiveMessage( String source, String symbol, Object... args ) {
			
			pdPost("message: " + Arrays.toString(args));
		}

		@Override
		public void receiveSymbol( String source, String symbol ) {
			
			pdPost("symbol: " + symbol);
		}
	};
	
	/**
	 * Constructor: PureDataProxy
	 * 
	 * @param context
	 * @param activity
	 */
	private PureDataProxy( Context context, Activity activity ) {
		
		this.activity 	= activity;
		
		//Initialize PdPreferences
		PdPreferences.initPreferences( context );
		
		//Tell android what should happen on defaultSharedPreferences
		PreferenceManager.getDefaultSharedPreferences( context ).registerOnSharedPreferenceChangeListener( this );
		
		//Bind a service to the Android system
		activity.bindService( new Intent( activity, PdService.class ), pdConnection, Activity.BIND_AUTO_CREATE );
	}

	@Override
	public void onSharedPreferenceChanged( SharedPreferences sharedPreferences, String key ) {
		
		startAudio();
	}
	
	public void destroy() {
		
		try {
			
			activity.unbindService( pdConnection );
		
		} catch ( IllegalArgumentException e ) {
			
			pdService = null;
		}
	}
}

//Just so I will not forget how to use this:

/*
private void evaluateMessage(String s) {
	String dest = "test", symbol = null;
	boolean isAny = s.length() > 0 && s.charAt(0) == ';';
	Scanner sc = new Scanner(isAny ? s.substring(1) : s);
	if (isAny) {
		if (sc.hasNext()) dest = sc.next();
		else {
			toast("Message not sent (empty recipient)");
			return;
		}
		if (sc.hasNext()) symbol = sc.next();
		else {
			toast("Message not sent (empty symbol)");
		}
	}
	List<Object> list = new ArrayList<Object>();
	while (sc.hasNext()) {
		if (sc.hasNextInt()) {
			list.add(new Float(sc.nextInt()));
		} else if (sc.hasNextFloat()) {
			list.add(sc.nextFloat());
		} else {
			list.add(sc.next());
		}
	}
	if (isAny) {
		PdBase.sendMessage(dest, symbol, list.toArray());
	} else {
		switch (list.size()) {
		case 0:
			PdBase.sendBang(dest);
			break;
		case 1:
			Object x = list.get(0);
			if (x instanceof String) {
				PdBase.sendSymbol(dest, (String) x);
			} else {
				PdBase.sendFloat(dest, (Float) x);
			}
			break;
		default:
			PdBase.sendList(dest, list.toArray());
			break;
		}
	}
}*/



/*
switch (v.getId()) {
	case R.id.left_box:
		PdBase.sendFloat( "phasleft",  (float) (left.isChecked() ? 100.5 : 0) );
		break;
	case R.id.right_box:
		PdBase.sendFloat( "phasright", right.isChecked() ? 100 : 0 );
		break;
	case R.id.mic_box:
		PdBase.sendFloat("mic", mic.isChecked() ? 1 : 0);
		break;
	case R.id.pref_button:
		startActivity(new Intent(this, PdPreferences.class));
		break;
	default:
		break;
		
		*/