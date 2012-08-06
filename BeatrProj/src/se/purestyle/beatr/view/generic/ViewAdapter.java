package se.purestyle.beatr.view.generic;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Required by the Android framework to tell the GridView what it should contain
 * 
 * @author kristian
 *
 */
public class ViewAdapter extends BaseAdapter {
	
//    private Context mContext;
    private View[]	viewList;
    
//    public ViewAdapter( Context c ) {
    	
//        mContext = c;
//    }

    public int getCount() {
    	
    	Log.i( "ViewAdapter", "getCount: " + viewList.length );
    	
        return viewList.length;
    }

    public Object getItem( int position ) {
    	
        return null;
    }

    public long getItemId( int position ) {
    	
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView( int position, View convertView, ViewGroup parent ) {
    	
        return viewList[ position ];
    }
    
    /**
     * Window to the class that's going to give views
     * 
     * @param viewList
     */
    public void setViewArray( View[] viewList ) {
    	
    	/*
    	LinearLayout layout = new LinearLayout( mContext );
    	layout.setLayoutParams( new AbsListView.LayoutParams( 75, 75 ) );
    	layout.setBackgroundColor( Color.RED );
    	
    	LinearLayout layout2 = new LinearLayout( mContext );
    	layout2.setLayoutParams( new AbsListView.LayoutParams( 75, 75 ) );
    	layout2.setBackgroundColor( Color.BLUE );
    	
    	LinearLayout layout3 = new LinearLayout( mContext );
    	layout3.setLayoutParams( new AbsListView.LayoutParams( 75, 75 ) );
    	layout3.setBackgroundColor( Color.CYAN );
    	
    	LinearLayout layout4 = new LinearLayout( mContext );
    	layout4.setLayoutParams( new AbsListView.LayoutParams( 75, 75 ) );
    	layout4.setBackgroundColor( Color.YELLOW );
    	
    	this.viewList = new View[] { layout, layout2, layout3, layout4 };
    	*/
    	
    	this.viewList = viewList;
    }
}