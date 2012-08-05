package se.purestyle.beatr.controller.editors;

import java.beans.PropertyChangeEvent;

import se.purestyle.beatr.controller.generic.KnobController;
import se.purestyle.beatr.model.editors.DrumEditorModel;
import se.purestyle.beatr.view.editors.DrumEditorView;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.purestyle.amvc.controller.AbstractController;

public class DrumEditorController extends AbstractController {
	
	private String 					pdInternalInstrumentName;
	
	private DrumEditorView 			view;
	private DrumEditorModel 		model;
	
	private Context 				context;
	
	//Some sub controllers
	private KnobController			rootKnobController;
	private KnobController			f01KnobController;
	private KnobController			f02KnobController;
	private KnobController			clipKnobController;
	private KnobController			shapeKnobController;
	private KnobController			decayKnobController;
	private KnobController			modKnobController;
	
	public DrumEditorController( String pdInternalInstrumentName, Context context ) {
		
		this.pdInternalInstrumentName = pdInternalInstrumentName;
		this.context = context;
	}
	
	@Override
	public void setup() {
		
		view 				= new DrumEditorView( context );
		model 				= new DrumEditorModel( pdInternalInstrumentName );
		
		model.setClip( 2.0f );
		
		//Initialize subcontrollers
		rootKnobController 	= new KnobController( context );
		rootKnobController.setup();
		f01KnobController 	= new KnobController( context );
		f01KnobController.setup();
		f02KnobController	= new KnobController( context );
		f02KnobController.setup();
		clipKnobController	= new KnobController( context );
		clipKnobController.setup();
		shapeKnobController	= new KnobController( context );
		shapeKnobController.setup();
		decayKnobController	= new KnobController( context );
		decayKnobController.setup();
		modKnobController	= new KnobController( context );
		modKnobController.setup();
		
		//Make this controller listen to it's subcontrollers
		rootKnobController.addObserver(		this );
		f01KnobController.addObserver(		this );
		f02KnobController.addObserver(		this );
		clipKnobController.addObserver(		this );
		shapeKnobController.addObserver(	this );
		decayKnobController.addObserver(	this );
		modKnobController.addObserver(		this );
		
		view.addKnob( rootKnobController.getView() 	);
		view.addKnob( f01KnobController.getView() 	);
		view.addKnob( f02KnobController.getView() 	);
		view.addKnob( clipKnobController.getView() 	);
		view.addKnob( shapeKnobController.getView() );
		view.addKnob( decayKnobController.getView() );
		view.addKnob( modKnobController.getView() );
	}

	@Override
	public void teardown() {
		
		
	}
	
	public View getView() {
		
		return view;
	}
	
	@Override
	public void propertyChange( PropertyChangeEvent event ) {
		
	//	root is changed
		if( event.getSource().equals( rootKnobController ) ) {
			
			Log.i( "DrumEditorController", "root" );
			
	//	f01 is changed
		} else if( event.getSource().equals( f01KnobController ) ) {
			
			Log.i( "DrumEditorController", "f01" );
			
	//	f02 is changed
		} else if( event.getSource().equals( f02KnobController ) ) {
			
			Log.i( "DrumEditorController", "f02" );
			
	//	clip is changed
		} else if( event.getSource().equals( clipKnobController ) ) {
			
			Log.i( "DrumEditorController", "clip" );
			
	//	shape is changed
		} else if( event.getSource().equals( shapeKnobController ) ) {
			
			Log.i( "DrumEditorController", "shape" );
			
	//	decay is changed
		} else if( event.getSource().equals( decayKnobController ) ) {
			
			Log.i( "DrumEditorController", "decay" );
			
	//	mod is changed
		} else if( event.getSource().equals( modKnobController ) ) {
			
			Log.i( "DrumEditorController", "mod" );
		}
	}
}