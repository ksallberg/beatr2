package se.purestyle.beatr.view.instrumentmixer;

import se.purestyle.beatr.view.instrumentmixer.volumeobject.IInstrumentView;

/**
 * Interface for manipulating the list of instruments currently available to mix
 * 
 * @author kristian
 *
 */
public interface IInstrumentMixer {
	
	public void addInstrumentView( IInstrumentView view );
	public void removeInstrumentView( IInstrumentView view );
}