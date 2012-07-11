package se.purestyle.beatr.view.instrumentmixer;

import org.puredata.android.service.R;

import se.purestyle.beatr.view.InstrumentMixerView;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class HeaderView extends RelativeLayout {

	public HeaderView( Context context ) {
		
		super( context );
		
		init( context );
	}
	
	private void init( Context context ) {
		
		setLayoutParams( new LayoutParams( LayoutParams.FILL_PARENT, 60 ) );
		
		RelativeLayout.LayoutParams masterVolumeParams = new RelativeLayout.LayoutParams( (int) MasterVolumeView.TOTAL_WIDTH, (int) MasterVolumeView.TOTAL_HEIGHT );
		masterVolumeParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		masterVolumeParams.setMargins( 6, 6, 0, 0);
		
		MasterVolumeView masterVolume = new MasterVolumeView( context );
		masterVolume.setTag( InstrumentMixerView.MASTER_VOLUME_VIEW );
		masterVolume.setLayoutParams( masterVolumeParams );
		addView( masterVolume );
		
		RelativeLayout.LayoutParams logoParams = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
		logoParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		logoParams.setMargins( 0, 3, 0, 0 );
		
		ImageView logo = new ImageView( context );
		logo.setBackgroundResource( R.drawable.logo );
		logo.setLayoutParams( logoParams );
		addView( logo );
	}
}