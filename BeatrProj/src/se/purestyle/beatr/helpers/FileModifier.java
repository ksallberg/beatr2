package se.purestyle.beatr.helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

public class FileModifier {
	
	private static Activity _activity;
	private static Context _context;
	private static int _counter = 0; //This serves as a counter to be able to have unique temp file names and not overwrite stuff
	
	private static boolean initialized = false;
	
	public static void init( Activity activity, Context context ) {
		
		_activity = activity;
		_context = context;
		
		initialized = true; //It's now OK to call methods of this class
	}
	
	/**
	 * The purpose of this class is to create new .pd files in the temp memory, where they 
	 * have unique variable names. So several instruments will be able to use the same .pd file 
	 * from the assets folder, and then have their own tags for the settings 
	 * 
	 * It takes the activity and context, needed for getting the file
	 * It also takes a map from String -> String, where the first String is the word in the file to replace
	 * (and replaces it with the second file)
	 * 
	 * Then returns the file in which the required variables were changed.
	 * 
	 * @param activity
	 * @param context
	 * @return File that has been modified
	 */
	public static File createIndividualizedFile( Map<String, String> replacementMap, String fileName ) {
		
		if( !initialized ) {
			
			throw new RuntimeException( "FileModifier not yet initialized!" );
		}
		
		InputStream in = null;
		BufferedReader reader = null;
		
		//Try to create an inputstream so we can read from the original .pd file
		try {
			
			in = _activity.getAssets().open( fileName ); //"pdfiles/smalletst.pd"
			reader = new BufferedReader( new InputStreamReader( in ) );
			
		} catch ( IOException e ) {
			
			e.printStackTrace();
		}
		
		//Then, create the temp version of the file
		File outputDir = _context.getCacheDir(); // context being the Activity pointer
		File outputFile = null;
		
		try {
			
			outputFile = File.createTempFile( "temppdfile" + _counter, "pd", outputDir );
			outputFile.setWritable( true );
			outputFile.setReadable( true );
			
			_counter ++; //Increment counter for the next file/run
			
		} catch ( IOException e1 ) {
			
			e1.printStackTrace();
		}
		
		//Read every line 
		try {
			
			String line = reader.readLine();
			BufferedWriter buffWriter = new BufferedWriter( new FileWriter( outputFile ) );
			
			while( line != null ) {
				
				//Replace all variables
				for( String key : replacementMap.keySet() ) {
					
					line = line.replaceAll( key, replacementMap.get( key ) );
				}
				
				buffWriter.write( line ); //Write the line with possibly modified variables
				buffWriter.newLine(); //and then make room for a new line
				
				line = reader.readLine();
			}
			
			buffWriter.close();
			
		} catch( EOFException e ) {
			
			e.printStackTrace();
			
		} catch( IOException ioE ) {
			
			ioE.printStackTrace();
		}
		
//		testOpenAgain( outputFile );
		
		return outputFile;
	}
	
	/**
	 * Test / debug method to read all the lines in a file
	 * 
	 * @param file
	 */
	public static void traceFile( File file ) {
		
		try {
			
			BufferedReader reader = new BufferedReader( new InputStreamReader( new FileInputStream( file ) ) );
			
			String line = reader.readLine();
			
			while( line != null ) {
				
				line = reader.readLine();
				
				Log.i( "FileModifier", "line: " + line );
			}
			
		} catch( EOFException e ) {
			
			e.printStackTrace();
			
		} catch( IOException ioE ) {
			
			ioE.printStackTrace();
		}
	}
}