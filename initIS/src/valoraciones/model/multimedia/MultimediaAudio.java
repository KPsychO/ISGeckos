package valoraciones.model.multimedia;

import valoraciones.factories.BuildersMultimediaTypes;

public class MultimediaAudio extends Multimedia{
	private static String[] suportedAudio = {"",""};
	public MultimediaAudio() {
		super(suportedAudio,BuildersMultimediaTypes.AUDIO);
	}

	@Override
	public String getPresentation() {
		return "Multimedia audio "+this.aviableFormats[this.format]+" execute,\nfile: "+this.nameFile;
	}

}
