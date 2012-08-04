package se.purestyle.beatr.controller.generic;

import se.purestyle.beatr.model.generic.DrumPadModel;
import se.purestyle.beatr.view.generic.DrumPadView;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

import com.purestyle.amvc.controller.AbstractController;

public class DrumPadController extends AbstractController {

	private Context context;
	
	private DrumPadModel model;
	private DrumPadView view;
	
	public DrumPadController( Context context ) {
		
		this.context = context;
	}
	
	@Override
	public void setup() {
		
		model = new DrumPadModel();
		
		view = new DrumPadView( context );
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
		}
	};
}