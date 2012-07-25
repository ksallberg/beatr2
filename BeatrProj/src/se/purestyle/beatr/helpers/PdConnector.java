package se.purestyle.beatr.helpers;

import java.io.File;
import java.io.IOException;
//import java.io.InputStream;
import java.util.Arrays;

import org.puredata.android.service.PdPreferences;
import org.puredata.android.service.PdService;
import org.puredata.core.PdBase;
import org.puredata.core.PdReceiver;
//import org.puredata.core.utils.IoUtils;

import se.purestyle.beatr.BeatrActivity;
import se.purestyle.beatr.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
//import android.content.res.Resources;
import android.os.IBinder;
import android.util.Log;

public class PdConnector {

	private Activity activity = null;
	private static boolean initialized = false;
	
	public PdConnector( Context context, Activity activity ) {
		
		this.activity = activity;
		
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
		//	InputStream in = res.openRawResource(R.raw.smalletst);
		//	patchFile = IoUtils.extractResource(in, "smalletst.pd", activity.getCacheDir());
		//	PdBase.openPatch( patchFile );
			startAudio();
			
//		} catch (IOException e) {
//			activity.finish();
		} finally {
			if (patchFile != null) patchFile.delete();
		}
	}

	private void startAudio() {
		String name = activity.getResources().getString(R.string.app_name);
		try {
			pdService.initAudio(-1, -1, -1, -1);   // negative values will be replaced with defaults/preferences
			pdService.startAudio(new Intent(activity, BeatrActivity.class), R.drawable.icon, name, "Return to " + name + ".");
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
	
	/**
	 * Dummy method, just for testing if it's possible to add instrumnets after startAudio()
	 
	
	public void addSynth() {
		
		Resources res = activity.getResources();
		File patchFile = null;
		
		try {
			
			PdBase.setReceiver(receiver);
			PdBase.subscribe("android");
			InputStream in = res.openRawResource(R.raw.smalletst);
			patchFile = IoUtils.extractResource(in, "smalletst.pd", activity.getCacheDir());
			
			patchFile.setWritable( true );
			
			PdBase.openPatch( patchFile );
			
		} catch (IOException e) {
			//activity.finish();
		} finally {
			if (patchFile != null) patchFile.delete();
		}
	}
	*/
	
	/**
	 * Dummy method, just for testing if it's possible to add instrumnets after startAudio()
	
	public void addDrum() {
		

		Resources res = activity.getResources();
		File patchFile = null;
		
		try {
			
			PdBase.setReceiver(receiver);
			PdBase.subscribe("android");
			InputStream in = res.openRawResource(R.raw.smalletst2);
			patchFile = IoUtils.extractResource(in, "smalletst2.pd", activity.getCacheDir());
			PdBase.openPatch( patchFile );
			
		} catch (IOException e) {
			//activity.finish();
		} finally {
			if (patchFile != null) patchFile.delete();
		}
	}
	
	 */
	
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