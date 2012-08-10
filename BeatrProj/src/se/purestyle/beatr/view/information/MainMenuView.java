package se.purestyle.beatr.view.information;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;

public class MainMenuView extends LinearLayout {

	private MainMenuButtonView 		mixerButton;
	private int						radius;
	private Context					context;
	private String					name;
	
	public MainMenuView( Context context ) {
		
		super( context );
		
		this.context = context;
	}
	
	public void init() {
		
		LinearLayout mixerButtonWrapper = new LinearLayout( context );
		mixerButtonWrapper.setLayoutParams( new LayoutParams( radius, radius ) );
		
		mixerButton = new MainMenuButtonView( context );
		mixerButton.setName( name );
		
		mixerButtonWrapper.addView( mixerButton );

		addView( mixerButtonWrapper );
		setPadding( 0, 5, 0, 5 );
		
		setGravity( Gravity.CENTER );
	}
	
	public void setRadius( int radius ) {
		
		this.radius = radius;
	}
	
	public void setName( String name ) {
		
		this.name = name;
	}
}