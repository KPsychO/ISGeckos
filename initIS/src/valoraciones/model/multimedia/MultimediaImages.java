package valoraciones.model.multimedia;

import valoraciones.factories.BuildersMultimediaTypes;

public class MultimediaImages extends Multimedia{
	private static String[] suportedImages = {"png"};
	public MultimediaImages() {
		super(suportedImages,BuildersMultimediaTypes.IMAGES);
	}

	@Override
	public String getPresentation() {
		return "Multimedia image "+this.aviableFormats[this.format]+" execute,\nfile: "+this.pathFile;
	}

}
