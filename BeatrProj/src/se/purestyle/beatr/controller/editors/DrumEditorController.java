package se.purestyle.beatr.controller.editors;

import se.purestyle.beatr.model.editors.DrumEditorModel;
import se.purestyle.beatr.view.editors.DrumEditorView;

import android.content.Context;
import android.view.View;

import com.purestyle.amvc.controller.AbstractController;

public class DrumEditorController extends AbstractController {
	
	private String 					pdInternalInstrumentName;
	
	private DrumEditorView 			view;
	private DrumEditorModel 		model;
	
	private Context 				context;
	
	public DrumEditorController( String pdInternalInstrumentName, Context context ) {
		
		this.pdInternalInstrumentName = pdInternalInstrumentName;
	}
	
	@Override
	public void setup() {
		
		view 	= new DrumEditorView( context );
		model 	= new DrumEditorModel( pdInternalInstrumentName );
		
		model.setClip( 2.0f );
	}

	@Override
	public void teardown() {
		
		
	}
	
	public View getView() {
		
		return view;
	}
}