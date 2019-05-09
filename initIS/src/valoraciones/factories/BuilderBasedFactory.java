package valoraciones.factories;

import java.util.ArrayList;
import java.util.List;


public class BuilderBasedFactory<T> implements Factory<T>{
	
	List<Builder<T>> builders;
	
	public BuilderBasedFactory(ArrayList<Builder<T>> builders){
		this.builders = builders;
	}

	@Override
	public T createInstance(Object[] data) {
			
		T aux = null;
		for( Builder<T> builder : builders) {
			aux = (T) builder.createInstance(data);
			if( aux != null)
				return aux;
		}
		
		throw new IllegalArgumentException("Error Builder Based Factory "+data[0]+" not supported");
	}
	
	//
}
