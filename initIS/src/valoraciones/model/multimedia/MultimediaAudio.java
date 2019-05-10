package valoraciones.model.multimedia;

import valoraciones.factories.BuildersMultimediaTypes;

public class MultimediaAudio extends Multimedia{
	private static String[] suportedAudio = {"mp3"};
	public MultimediaAudio() {
		super(suportedAudio,BuildersMultimediaTypes.AUDIO);
	}

	@Override
	public String getPresentation() {
		return "Multimedia audio "+this.aviableFormats[this.format]+" execute,\nfile: "+this.pathFile;
	}

}
