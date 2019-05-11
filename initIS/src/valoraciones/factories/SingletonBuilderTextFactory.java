package valoraciones.factories;

import java.util.ArrayList;

import valoraciones.model.text.Text;

public class SingletonBuilderTextFactory {
	private static Factory<Text> factory;

	private synchronized static void createInstance() {
		ArrayList<Builder<Text>> builders = new ArrayList<Builder<Text>>();
		builders.add(new BuilderTextComentario());
		builders.add(new BuilderTextValoracion());
		builders.add(new BuilderTextTitulo());
		factory = new BuilderBasedFactory<Text>(builders);
	}
	
	public static Factory<Text> getSingletonInstance() {
		if(factory == null) {
			createInstance();
		}
		return factory;
	 }
	
	@Override
	public SingletonBuilderTextFactory clone(){
	    try {
	        throw new CloneNotSupportedException();
	    } catch (CloneNotSupportedException ex) {
	        ex.printStackTrace();
	    }
	    return null; 
	}
}
