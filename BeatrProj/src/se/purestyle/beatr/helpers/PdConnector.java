package se.purestyle.beatr.helpers;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.puredata.android.service.PdPreferences;
import org.puredata.android.service.PdService;
import org.puredata.core.PdBase;
import org.puredata.core.PdReceiver;

import se.purestyle.beatr.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

@SuppressWarnings("rawtypes")
public class PdConnector {

	private Activity activity = null;
	private static boolean initialized = false;
	
	private Class classRef; //Using Class just to get rid of a circular dependency 
	
	public PdConnector( Context context, Activity activity, Class classRef ) {
		
		this.activity = activity;
		this.classRef = classRef;
		
		initialized = true;
		
		PdPreferences.initPreferences( context );
		
		activity.bindService( new Intent( activity, PdService.class ), pdConnection, Activity.BIND_AUTO_CREATE );
	}
	
	private PdService pdService = null;

	private PdReceiver receiver = new PdReceiver() {

		private void pdPost(String msg) {
			//toast("Pure Data says, \"" + msg + "\"");
		}

		@Override
		public void print(String s) {
			//post(s);
		}

		@Override
		public void receiveBang(String source) {
			pdPost("bang");
		}

		@Override
		public void receiveFloat(String source, float x) {
			pdPost("float: " + x);
		}

		@Override
		public void receiveList(String source, Object... args) {
			pdPost("list: " + Arrays.toString(args));
		}

		@Override
		public void receiveMessage(String source, String symbol, Object... args) {
			pdPost("message: " + Arrays.toString(args));
		}

		@Override
		public void receiveSymbol(String source, String symbol) {
			pdPost("symbol: " + symbol);
		}
	};

	private final ServiceConnection pdConnection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			pdService = ((PdService.PdBinder)service).getService();
			initPd();
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {}
	};
	
	private void initPd() {

		File patchFile = null;
		
		try {
			
			PdBase.setReceiver(receiver);
			PdBase.subscribe("android");
			startAudio();

		} finally {
			
			if (patchFile != null) patchFile.delete();
		}
	}

	private void startAudio() {
		
		try {
			pdService.initAudio(-1, -1, -1, -1);   // negative values will be replaced with defaults/preferences
			pdService.startAudio(new Intent(activity, classRef), R.drawable.icon, "beatr", "back to beatr");
		} catch (IOException e) {
			//toast(e.toString());
		}
	}

	public void cleanup() {
		try {
			activity.unbindService(pdConnection);
		} catch (IllegalArgumentException e) {
			// already unbound
			pdService = null;
		}
	}
	
	public static void sendToPd( String str, float fl ) {
		
		if( !initialized ) {
			
			throw new RuntimeException( "Cannot run this method, the class is not initialized." );
		}
		
		PdBase.sendFloat( str, fl );
	}

	public static void addPatch( File patchFile ) {
		
		if( !initialized ) {
			
			throw new RuntimeException( "Cannot run this method, the class is not initialized." );
		}
		
		try {
			
			PdBase.openPatch( patchFile );
		
		} catch( IOException e ) {
			
			Log.e( "PdConnector: ERROR: ", e.toString() );
		
		} finally {
			
			if( patchFile != null ) {
				
				patchFile.delete();
			}
		}
	}
}