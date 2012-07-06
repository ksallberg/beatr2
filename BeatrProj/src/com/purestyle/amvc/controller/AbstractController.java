package com.purestyle.amvc.controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.Map;

import com.purestyle.amvc.model.IModel;
import com.purestyle.amvc.view.IView;

public abstract class AbstractController implements IController {

	protected Map<String,IView> views 					= new HashMap<String,IView>();
	protected Map<String,IModel> models 				= new HashMap<String,IModel>();
	protected Map<String,IController> subcontrollers 	= new HashMap<String,IController>();
	protected PropertyChangeSupport observers 			= new PropertyChangeSupport(this);
	
	@Override
	public Map<String, IView> getViews() {

		return views;
	}

	@Override
	public Map<String, IController> getSubcontollers() {

		return subcontrollers;
	}

	@Override
	public Map<String, IModel> getModels() {

		return models;
	}
	
	@Override
	public void addObserver(PropertyChangeListener observer) {
		observers.addPropertyChangeListener(observer);
	}

	@Override
	public void removeObserver(PropertyChangeListener observer) {
		observers.removePropertyChangeListener(observer);
	}

	@Override
	public void removeAllObservers() {
		
		for(PropertyChangeListener listener : observers.getPropertyChangeListeners()){
			observers.removePropertyChangeListener(listener);
		}
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event){
		
	}
	
	/**
	 * Convenience method for adding a view to the map of views in the controller
	 * 
	 * @param k key
	 * @param v value
	 */
	public void addView(String k, IView v) {
		
		views.put(k,v);
	}
	
	/**
	 * Convenience method for removing an existing view from the map of views in the controller
	 * 
	 * @param k key
	 */
	public void removeView(String k) {
		
		views.remove(k);
	}
	
	/**
	 * Convenience method for adding a model to the map of models in the controller
	 * 
	 * @param k key
	 * @param v value
	 */
	public void addModel(String k,IModel v) {
		
		models.put(k,v);
	}
	
	/**
	 * Convenience method for removing an existing model from the map of models in the controller
	 * 
	 * @param k key
	 */
	public void removeModel(String k) {
		
		models.remove(k);
	}
	
	/**
	 * Convenience method for adding a controller to the map of sub controllers
	 * 
	 * @param k key
	 * @param v value
	 */
	public void addSubcontroller(String k,IController v) {
		
		subcontrollers.put(k, v);
	}
	
	/**
	 * Convenience method for removing an existing controller from the map of sub controllers
	 * 
	 * @param k key
	 */
	public void removeSubcontroller(String k) {
		
		subcontrollers.remove(k);
	}
}