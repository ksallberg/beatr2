package com.purestyle.amvc.controller;

import java.beans.PropertyChangeListener;
import java.util.Map;

import com.purestyle.amvc.model.IModel;
import com.purestyle.amvc.view.IView;

public interface IController extends PropertyChangeListener {

	void setup();
	void teardown();
	Map<String,IView> getViews();
	Map<String,IController> getSubcontollers();
	Map<String,IModel> getModels();
	void addObserver(PropertyChangeListener observer);
	void removeObserver(PropertyChangeListener observer);
	void removeAllObservers();
}
