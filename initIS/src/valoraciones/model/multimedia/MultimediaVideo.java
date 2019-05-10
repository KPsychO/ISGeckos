package valoraciones.model.multimedia;

import valoraciones.factories.BuildersMultimediaTypes;

public class MultimediaVideo extends Multimedia{
	private static String[] suportedVideo= {"",""};
	public MultimediaVideo() {
		super(suportedVideo,BuildersMultimediaTypes.VIDEO);
	}

	@Override
	public String getPresentation() {
		return "Multimedia video "+this.aviableFormats[this.format]+" execute,\nfile: "+this.pathFile;
	}

}
