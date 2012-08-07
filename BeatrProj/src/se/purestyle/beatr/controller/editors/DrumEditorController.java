package se.purestyle.beatr.controller.editors;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import se.purestyle.beatr.controller.generic.DrumPadController;
import se.purestyle.beatr.controller.generic.KnobController;
import se.purestyle.beatr.model.editors.DrumEditorModel;
import se.purestyle.beatr.model.generic.KnobModel;
import se.purestyle.beatr.view.editors.DrumEditorView;
import se.purestyle.beatr.view.generic.DrumPadView;

import android.content.Context;
import android.view.View;

import com.purestyle.amvc.controller.AbstractController;

/**
 * 
 * 
 * @author kristian
 *
 */
public class DrumEditorController extends AbstractController {
	
	private String 					pdInternalInstrumentName;
	
	private DrumEditorView 			view;
	private DrumEditorModel 		model;
	
	private Context 				context;
	
	public static final String		rootLabel 	= "rootLabel";
	public static final String		f01Label 	= "f01Label";
	public static final String		f02Label 	= "f02Label";
	public static final String		clipLabel 	= "clipLabel";
	public static final String		shapeLabel 	= "shapeLabel";
	public static final String		decayLabel 	= "decayLabel";
	public static final String		modLabel 	= "modLabel";
	
	//Some sub controllers
	private KnobController			rootKnobController;
	private KnobController			f01KnobController;
	private KnobController			f02KnobController;
	private KnobController			clipKnobController;
	private KnobController			shapeKnobController;
	private KnobController			decayKnobController;
	private KnobController			modKnobController;
	
	private List<DrumPadController> drumPads;
	
	public DrumEditorController( String pdInternalInstrumentName, Context context ) {
		
		this.pdInternalInstrumentName = pdInternalInstrumentName;
		this.context = context;
	}
	
	@Override
	public void setup() {
		
		drumPads 			= new ArrayList<DrumPadController>();
		
		view 				= new DrumEditorView( context );
		model 				= new DrumEditorModel( pdInternalInstrumentName );
		
		//TODO: Find a better place to start the individual drum
		model.setOnoff( 1 );
		
		model.setClip( 2.0f );
		
		//Initialize subcontrollers
		rootKnobController 	= new KnobController( context );
		rootKnobController.setLabel( rootLabel );
		rootKnobController.setup();
		
		f01KnobController 	= new KnobController( context );
		f01KnobController.setLabel( f01Label );
		f01KnobController.setup();
		
		f02KnobController	= new KnobController( context );
		f02KnobController.setLabel( f02Label );
		f02KnobController.setup();
		
		clipKnobController	= new KnobController( context );
		clipKnobController.setLabel( clipLabel );
		clipKnobController.setup();
		
		shapeKnobController	= new KnobController( context );
		shapeKnobController.setLabel( shapeLabel );
		shapeKnobController.setup();
		
		decayKnobController	= new KnobController( context );
		decayKnobController.setLabel( decayLabel );
		decayKnobController.setup();
		
		modKnobController	= new KnobController( context );
		modKnobController.setLabel( modLabel );
		modKnobController.setup();
		
		//Make this controller listen to it's subcontrollers
		rootKnobController.addObserver(		this );
		f01KnobController.addObserver(		this );
		f02KnobController.addObserver(		this );
		clipKnobController.addObserver(		this );
		shapeKnobController.addObserver(	this );
		decayKnobController.addObserver(	this );
		modKnobController.addObserver(		this );
		
		View[] knobs = new View[] { 
									rootKnobController.getView(),
									f01KnobController.getView(),
									f02KnobController.getView(),
									clipKnobController.getView(),
									shapeKnobController.getView(),
									decayKnobController.getView(),
									modKnobController.getView()
								  };
		
		view.addKnobs( knobs );
		
//TODO: Snygga till skapanded av pads!
		List<DrumPadView> pads = new ArrayList<DrumPadView>();
		
		for( int i = 0; i < 9; i ++ ) {
			
			DrumPadController drumPad = new DrumPadController( context );
			drumPad.setup();
			
			drumPads.add( drumPad );
			
			pads.add( (DrumPadView) drumPad.getView() );
		}
		
		View[] drumPads = pads.toArray( new View[]{} );
		
		view.addDrumPads( drumPads );
	}

	@Override
	public void teardown() {
		
		view.removeAllViews();
		
		rootKnobController.teardown();
		f01KnobController.teardown();
		f02KnobController.teardown();
		clipKnobController.teardown();
		shapeKnobController.teardown();
		decayKnobController.teardown();
		modKnobController.teardown();
	}
	
	public View getView() {
		
		return view;
	}
	
	@Override
	public void propertyChange( PropertyChangeEvent event ) {
		
		if( event.getPropertyName().equals( KnobModel.NEW_PERCENTAGES ) ) {
			
			KnobModel eventModel = (KnobModel) event.getSource();
			
			//	root is changed
			if( eventModel.getLabel().equals( rootLabel ) ) {
				
				model.setRoot( (Float) event.getNewValue() );
				
		//	f01 is changed
			} else if( eventModel.getLabel().equals( f01Label ) ) {
				
				model.setF01( (Float) event.getNewValue() );
				
		//	f02 is changed
			} else if( eventModel.getLabel().equals( f02Label ) ) {
				
				model.setF02( (Float) event.getNewValue() );
				
		//	clip is changed
			} else if( eventModel.getLabel().equals( clipLabel ) ) {
				
				model.setClip( (Float) event.getNewValue() );
				
		//	shape is changed
			} else if( eventModel.getLabel().equals( shapeLabel ) ) {
				
				model.setShape( (Float) event.getNewValue() );
				
		//	decay is changed
			} else if( eventModel.getLabel().equals( decayLabel ) ) {
				
				model.setDecay( (Float) event.getNewValue() );
				
		//	mod is changed
			} else if( eventModel.getLabel().equals( modLabel ) ) {
				
				model.setMod( (Float) event.getNewValue() );
			}
		}
	}
}