package valoraciones.factories;

public abstract class Builder<T> {
	int type;
	//String[] data;
	
	protected Builder(int type) {
		this.type = type;
	}
	
	public T createInstance(Object[] data) {
		if( (int)data[0] == type) {
			return createTheInstance(data);
		}
		
		return null;
	}
	
	protected abstract T createTheInstance(Object[] data)  throws IllegalArgumentException; 
}
