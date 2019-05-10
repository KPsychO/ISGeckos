package valoraciones.model.multimedia;

public abstract class Multimedia {
	protected String[] aviableFormats;
	protected String pathFile;
	protected String nameFile;
	protected int format;
	protected int type;
	
	public Multimedia(String[] aviableFormats,int type) {
		this.aviableFormats = aviableFormats;
		this.type = type;
	}
	
	public void setPath(String path, String name) throws IllegalArgumentException{
		this.pathFile = path;
		this.nameFile = name;
		this.format = getFormat(name);
	}
	
	public boolean isFormatSuported(String nameFile) {
		for(String format : aviableFormats) {
			if(nameFile.substring(nameFile.indexOf(".")+1, nameFile.length()).equals(format)) {
				return true;
			}
		}
		return false;
	}
	
	public abstract String getPresentation();

	
	private int getFormat(String nameFile) throws IllegalArgumentException{
		for(int i = 0;i<aviableFormats.length;++i) 
			if(nameFile.substring(nameFile.indexOf(".")+1, nameFile.length()).equals(aviableFormats[i])) 
				return i;
		
		throw new IllegalArgumentException("Error format not suported");
	}

	public String getToStore() {
		return type+"&"+pathFile+"&"+nameFile;
	}

	public int getType() {
		return this.type;
	}

	public String getName() {
		return this.nameFile;
	}
	
}
