package se.purestyle.beatr.view.generic;

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

    public int getCount() {
    	
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
    	
    	this.viewList = viewList;
    }
    
    /**
     * Get rid of the selection feature in the GridView
     */
    @Override
    public boolean areAllItemsEnabled() {
    	
    	return false;
    }
    
    /**
     * Get rid of the selection feature in the GridView
     */
    @Override
    public boolean isEnabled( int position ) {
    	
    	return false;
    }
}