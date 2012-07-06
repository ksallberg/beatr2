package com.purestyle.amvc.model;

public interface IModelUser {

	//To be able to set the model outside of the constructor
	public void setModel( IModel model );
	
	//To be able to initialize stuff using the model outside of the constructor
	public void init();
}