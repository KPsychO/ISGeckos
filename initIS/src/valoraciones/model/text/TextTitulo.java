package valoraciones.model.text;

public class TextTitulo extends Text{
	private static String[] wordsBannedTitulo = new String[] {
			"titulo1",
			"titulo2",
			"titulo3"
	};
	
	public TextTitulo(){
		super(128,wordsBannedTitulo);
	}
}
