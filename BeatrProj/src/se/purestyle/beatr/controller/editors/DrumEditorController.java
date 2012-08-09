package se.purestyle.beatr.controller.editors;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import se.purestyle.beatr.controller.generic.DrumPadController;
import se.purestyle.beatr.controller.generic.KnobController;
import se.purestyle.beatr.helpers.InstrumentTracker;
import se.purestyle.beatr.model.editors.DrumEditorModel;
import se.purestyle.beatr.model.generic.KnobModel;
import se.purestyle.beatr.view.editors.DrumEditorView;
import se.purestyle.beatr.view.generic.DrumPadView;

import android.app.Activity;
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
	private Activity				activity;
	
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
	
	public DrumEditorController( String pdInternalInstrumentName, Context context, Activity activity ) {
		
		this.pdInternalInstrumentName = pdInternalInstrumentName;
		this.context = context;
		this.activity = activity;
	}
	
	@Override
	public void setup() {
		
		drumPads 			= new ArrayList<DrumPadController>();
		
		view 				= new DrumEditorView( context );

		model				= (DrumEditorModel) InstrumentTracker.getModel( pdInternalInstrumentName );
		model.setView( view );
		
		//TODO: Find a better place to start the individual drum
		model.setOnoff( 1 );
		
		model.setClip( 2.0f );
		
		//Initialize subcontrollers
		rootKnobController 	= new KnobController( context );
		rootKnobController.setLabel( rootLabel );
		rootKnobController.setup();
		rootKnobController.getModel().setCurrentPercent( model.getRoot() / model.getMaxRoot() );
		rootKnobController.getView().invalidate();
		
		f01KnobController 	= new KnobController( context );
		f01KnobController.setLabel( f01Label );
		f01KnobController.setup();
		f01KnobController.getModel().setCurrentPercent( model.getF01() / model.getMaxF01() );
		f01KnobController.getView().invalidate();
		
		f02KnobController	= new KnobController( context );
		f02KnobController.setLabel( f02Label );
		f02KnobController.setup();
		f02KnobController.getModel().setCurrentPercent( model.getF02() / model.getMaxF02() );
		f02KnobController.getView().invalidate();
		
		clipKnobController	= new KnobController( context );
		clipKnobController.setLabel( clipLabel );
		clipKnobController.setup();
		clipKnobController.getModel().setCurrentPercent( model.getClip() / model.getMaxClip() );
		clipKnobController.getView().invalidate();
		
		shapeKnobController	= new KnobController( context );
		shapeKnobController.setLabel( shapeLabel );
		shapeKnobController.setup();
		shapeKnobController.getModel().setCurrentPercent( model.getShape() / model.getMaxShape() );
		shapeKnobController.getView().invalidate();
		
		decayKnobController	= new KnobController( context );
		decayKnobController.setLabel( decayLabel );
		decayKnobController.setup();
		decayKnobController.getModel().setCurrentPercent( model.getDecay() / model.getMaxDecay() );
		decayKnobController.getView().invalidate();
		
		modKnobController	= new KnobController( context );
		modKnobController.setLabel( modLabel );
		modKnobController.setup();
		modKnobController.getModel().setCurrentPercent( model.getMod() / model.getMaxMod() );
		modKnobController.getView().invalidate();
		
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
		
		String[] knobHeaders = new String[] { 
												"root",
												"f01",
												"f02",
												"clip",
												"shape",
												"decay",
												"mod"
			  								};
		
		view.addKnobs( knobs, knobHeaders );
		
		List<DrumPadView> pads = new ArrayList<DrumPadView>();
		
		for( int i = 0; i < DrumEditorModel.NUMBER_OF_PADS; i ++ ) {
			
			DrumPadController drumPad = new DrumPadController( context, activity );
			drumPad.setId( i );
			drumPad.setup();
			
			drumPad.addObserver( this ); //Add this controller as an observer to be able to tell when the pad is clicked
			drumPads.add( drumPad );
			
			//Set the should play property from whatever it was set to last time
			drumPad.getModel().setShouldPlay( model.getDrumIsOn()[ i ] );
			
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
		
		//If any of the knobs were changed:
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
			
		//If any of the pads were changed
		} else if ( event.getPropertyName().equals( DrumPadController.PAD_PRESSED ) ) {
			
//			Log.i("DrumEditorController: " , "Pad pressed" + event.getNewValue() + ", " + event.getOldValue() );
			
			model.setDrumOnOff( (Integer) event.getOldValue(), (Boolean) event.getNewValue());
		}
	}
}