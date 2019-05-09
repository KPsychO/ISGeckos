package valoraciones.factories;

import valoraciones.model.multimedia.Multimedia;
import valoraciones.model.multimedia.MultimediaAudio;

public class BuilderMultimediaAudio extends Builder<Multimedia> {

	public BuilderMultimediaAudio() {
		super(BuildersMultimediaTypes.AUDIO);
	}
	
	@Override
	protected Multimedia createTheInstance(Object[] data) {
		Multimedia mul = new MultimediaAudio();
		if(!mul.isFormatSuported((String)data[1]))
			throw new IllegalArgumentException("format not suported");
		mul.setPath((String)data[1], (String)data[2]);
		return mul;
	}

}
