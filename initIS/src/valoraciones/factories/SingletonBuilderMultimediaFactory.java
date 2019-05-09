package valoraciones.factories;

import java.util.ArrayList;

import valoraciones.model.multimedia.Multimedia;
public class SingletonBuilderMultimediaFactory {
	private static Factory<Multimedia> factory;

	private synchronized static void createInstance() {
		/*
		 * 
		ArrayList<Builder<Body>> bodyBuilders = new ArrayList<>();
		bodyBuilders.add(new BasicBodyBuilder());
		bodyBuilders.add(new MassLosingBodyBuilder());
		_bodyFactory = new BuilderBasedFactory<Body>(bodyBuilders);
		 */
		ArrayList<Builder<Multimedia>> builders = new ArrayList<Builder<Multimedia>>();
		builders.add(new BuilderMultimediaAudio());
		builders.add(new BuilderMultimediaImages());
		builders.add(new BuilderMultimediaVideo());
		factory = new BuilderBasedFactory<Multimedia>(builders);
	}
	
	public static Factory<Multimedia> getSingletonInstance() {
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
