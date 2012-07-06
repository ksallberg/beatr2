package com.purestyle.amvc.model;

import java.beans.PropertyChangeListener;

public interface IModel {

	void addObserver(PropertyChangeListener observer);
	void removeObserver(PropertyChangeListener observer);
	void removeAllObservers();
}
