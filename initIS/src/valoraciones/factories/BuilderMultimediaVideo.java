package valoraciones.factories;

import valoraciones.model.multimedia.Multimedia;
import valoraciones.model.multimedia.MultimediaVideo;

public class BuilderMultimediaVideo extends Builder<Multimedia>{
	
	public BuilderMultimediaVideo(){
		super(BuildersMultimediaTypes.VIDEO);
	}
	
	@Override
	protected Multimedia createTheInstance(Object[] data) {
		Multimedia mul = new MultimediaVideo();
		
		if(!mul.isFormatSuported((String)data[1]))
			throw new IllegalArgumentException("format not suported");
		
		mul.setPath((String)data[1], (String)data[2]);
		return mul;
	}


}
