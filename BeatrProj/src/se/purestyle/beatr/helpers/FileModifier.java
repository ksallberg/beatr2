package se.purestyle.beatr.helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

public class FileModifier {
	
	private static Activity _activity;
	private static Context _context;
	
	public static void openFile( Activity activity, Context context ) {
		
		
		_activity = activity;
		_context = context;
		
		InputStream in = null;
		BufferedReader reader = null;
		
		try {
			
			in = _activity.getAssets().open( "pdfiles/smalletst.pd" );
			reader = new BufferedReader( new InputStreamReader( in ) );
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		File outputDir = _context.getCacheDir(); // context being the Activity pointer
		File outputFile = null;
		
		try {
			
			outputFile = File.createTempFile( "temppdfile", "temppd", outputDir );
			outputFile.setWritable(true);
			outputFile.setReadable(true);
			
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
		try {
			
			String line = reader.readLine();
			
			FileOutputStream fOut = new FileOutputStream( outputFile );
			
			BufferedWriter buffWriter = new BufferedWriter( new FileWriter( outputFile ) );
			
			while( line != null ) {
				
				buffWriter.write( line ); //Write the line and then make room for a new line
				buffWriter.newLine();
				
				Log.i("FileModifier: readLine", "line: " + line );
				
				line = reader.readLine();
			}
			
			fOut.close();
			
		} catch( EOFException e ) {
			
			e.printStackTrace();
			
		} catch( IOException ioE ) {
			
			ioE.printStackTrace();
		}
		
		Log.i( "FileModifier", "Now the file should be populated." );
		
		Log.i("OKOKOKOK", "utrymme: " + outputFile.getTotalSpace() );
		
		
		Log.i( "SOMEHITNG COOL", "" + outputFile.length() );
		testOpenAgain( outputFile );
	}
	
	private static void testOpenAgain( File file ) {
		
		Log.i( "FileMod, testOpenAgain", "0" );
		
		try {
			
			Log.i( "FileMod, testOpenAgain", "1" );
			
			BufferedReader reader = new BufferedReader( new InputStreamReader( new FileInputStream( file ) ) );
			
			Log.i( "FileMod, testOpenAgain", "2" );
			
			String line = reader.readLine();
			
//			Log.i( "humpalimpa", reader.readLine() );
			
			Log.i( "FileMod, testOpenAgain", "3" );
			
			while( line != null ) {
				
				Log.i( "FileMod, testOpenAgain", "4" );
				
				Log.i("FileModifier: readLineFROM-CACHE-FILE", "CACHE-line: " + line );
				
				line = reader.readLine();
			}
			
		} catch( EOFException e ) {
			
			e.printStackTrace();
			
		} catch( IOException ioE ) {
			
			ioE.printStackTrace();
		}
		
		Log.i( "FileMod, testOpenAgain", "5" );
	}
}