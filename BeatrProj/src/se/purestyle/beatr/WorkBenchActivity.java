package se.purestyle.beatr;

import se.purestyle.beatr.view.generic.ViewAdapter;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;
import android.view.View;

public class WorkBenchActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
	    
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	    
	    GridView gridview = (GridView) findViewById( R.id.gridview );
	    gridview.setBackgroundColor( Color.WHITE );
	    
	    ViewAdapter adapter = new ViewAdapter();
	    adapter.setViewArray( null );
	    
	    gridview.setAdapter( adapter );
	    
	    gridview.setOnItemClickListener(new OnItemClickListener() {
		
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	        	
	            Toast.makeText(WorkBenchActivity.this, "" + position, Toast.LENGTH_SHORT).show();
	        }
	    });
	}
}