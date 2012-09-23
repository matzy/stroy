package openCage.tetris;

import java.util.ArrayList;
import java.util.List;

public class Loop<Elem> {

	private final List<Elem> elements;
	private int               idx;
	
	public Loop() {
		elements = new ArrayList<Elem>();
		idx      = 0;
	}
	
	public void add( Elem st ) {
		elements.add( st );
	}
	
	public Elem get() {
		return elements.get(idx);
	}
	
	public void advance() {
		++idx;
		if ( idx >= elements.size() ) {
			idx = 0;
		}
	}
}
