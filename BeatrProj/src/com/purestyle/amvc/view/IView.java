package com.purestyle.amvc.view;

import java.beans.PropertyChangeListener;
import java.util.Map;

import android.view.View;

public interface IView extends PropertyChangeListener {

	Map<String,View> getViews();
}
