package valoraciones.model.text;

public abstract class Text {
	private String text;
	
	private int maxCharacters;
	private String[] wordsBanned;
	
	protected Text(int maxCharacters,String[] wordsBanned) throws IllegalArgumentException{
		this.maxCharacters = maxCharacters;
		this.wordsBanned = wordsBanned;
	}
	
	public boolean isTextValid(String text) {
		if(text.length() > maxCharacters || text.length()<=0)
			return false;
		
		for(String wordBanned : wordsBanned) {
				if(text.indexOf(wordBanned)>=0)
					return false;
		}
		
		return true;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return this.text;
	}

}
