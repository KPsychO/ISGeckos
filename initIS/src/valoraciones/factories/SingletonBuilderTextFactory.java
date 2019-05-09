package valoraciones.factories;

import java.util.ArrayList;

import valoraciones.model.text.Text;

public class SingletonBuilderTextFactory {
	private static Factory<Text> factory;

	private synchronized static void createInstance() {
		/*
		 * 
		ArrayList<Builder<Body>> bodyBuilders = new ArrayList<>();
		bodyBuilders.add(new BasicBodyBuilder());
		bodyBuilders.add(new MassLosingBodyBuilder());
		_bodyFactory = new BuilderBasedFactory<Body>(bodyBuilders);
		 */
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
	        System.out.println("No se puede clonar un objeto de la clase SingletonBuilderTextFactory");
	    }
	    return null; 
	}
}
