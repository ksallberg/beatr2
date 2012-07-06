package com.purestyle.amvc.view;

import java.util.HashMap;
import java.util.Map;

import android.view.View;

public abstract class AbstractView implements IView {

	protected Map<String,View> views = new HashMap<String, View>();
	
	@Override
	public Map<String,View> getViews(){
		return views;
	}
}
