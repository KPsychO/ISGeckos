package valoraciones.model.text;

public class TextComentario extends Text{
	private static String[] wordsBannedComentario = new String[] {
			"comentario1",
			"comentario2",
			"comentario3"
	};
	
	public TextComentario(){
		super(256,wordsBannedComentario);
	}
}
