package se.purestyle.beatr;

import android.content.Context;

public class ResourceManager {
	
	private static Context appContext;
	
	public static void setContext( Context ctx ) { appContext = ctx; }
	public static Context getContext() { return appContext; }
}