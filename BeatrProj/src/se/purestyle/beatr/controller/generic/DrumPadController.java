package se.purestyle.beatr.controller.generic;

import se.purestyle.beatr.model.generic.DrumPadModel;
import se.purestyle.beatr.view.generic.DrumPadView;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

import com.purestyle.amvc.controller.AbstractController;

public class DrumPadController extends AbstractController {

	public static final String PAD_PRESSED = "padPressed";
	
	private Context context;
	private Activity activity;
	
	private DrumPadModel model;
	private DrumPadView view;
	
	private int id;
	
	public DrumPadController( Context context, Activity activity ) {
		
		this.context	= context;
		this.activity	= activity;
	}
	
	public void setId( int id ) {
		
		this.id = id;
	}
	
	@Override
	public void setup() {
		
		model = new DrumPadModel();
		
		view = new DrumPadView( context, activity );
		view.setModel( model );
		view.setOnClickListener( padPressedListener );
	}

	@Override
	public void teardown() {
		
		
	}
	
	public View getView() {
		
		return view;
	}
	
	private OnClickListener padPressedListener = new OnClickListener() {
		
		@Override
		public void onClick( View v ) {
			
			model.toggleShouldPlay();
			view.invalidate();
			
			observers.firePropertyChange( PAD_PRESSED, id, model.getShouldPlay() );
		}
	};
}