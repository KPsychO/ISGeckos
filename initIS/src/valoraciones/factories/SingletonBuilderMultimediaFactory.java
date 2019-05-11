package valoraciones.factories;

import java.util.ArrayList;

import valoraciones.model.multimedia.Multimedia;
public class SingletonBuilderMultimediaFactory {
	private static Factory<Multimedia> factory;

	private synchronized static void createInstance() {
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
	        ex.printStackTrace();
	    }
	    return null; 
	}
}
