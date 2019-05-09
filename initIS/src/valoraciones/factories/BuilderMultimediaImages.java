package valoraciones.factories;

import valoraciones.model.multimedia.Multimedia;
import valoraciones.model.multimedia.MultimediaImages;

public class BuilderMultimediaImages extends Builder<Multimedia>{

	BuilderMultimediaImages(){
		super(BuildersMultimediaTypes.IMAGES);
	}
	
	@Override
	protected Multimedia createTheInstance(Object[] data) {
		Multimedia mul = new MultimediaImages();
		if(!mul.isFormatSuported((String)data[1]))
			throw new IllegalArgumentException("format not suported");
		mul.setPath((String)data[1], (String)data[2]);
		return mul;
	}

}
