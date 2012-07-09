package se.purestyle.beatr.view.generic;

import com.purestyle.amvc.model.IModel;
import com.purestyle.amvc.model.IModelUser;

import android.content.Context;
import android.view.View;

public class AbstractVolumeView extends View implements IModelUser {

	protected IModel model;
	
	public AbstractVolumeView(Context context) {
		
		super(context);
	}

	@Override
	public void setModel( IModel model ) {
		
		this.model = model;
	}

	@Override
	public void init() {
		
		throw new RuntimeException( "This is an abstract class, do not use this!" );
	}
}