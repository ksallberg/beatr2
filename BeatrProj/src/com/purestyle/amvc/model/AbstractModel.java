package com.purestyle.amvc.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class AbstractModel implements IModel {

	protected PropertyChangeSupport observers = new PropertyChangeSupport(this);
	
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

}
