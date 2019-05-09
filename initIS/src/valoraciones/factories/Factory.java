package valoraciones.factories;

public interface Factory<T> {

	public abstract T createInstance(Object[] data);
}
