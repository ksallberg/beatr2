package se.purestyle.beatr;


import android.app.Activity;

public class PdTest extends Activity {

	PdConnector conn;

	
	

	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		conn = new PdConnector( getApplicationContext(), this );
		
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
		conn.cleanup();
	}

	
}