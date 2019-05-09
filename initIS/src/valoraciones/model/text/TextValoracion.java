package valoraciones.model.text;

public class TextValoracion extends Text{
	private static String[] wordsBannedValoracion = new String[] {
			"valoracion1",
			"valoracion2",
			"valoracion3"
	};
	
	public TextValoracion(){
		super(128,wordsBannedValoracion);
	}
}
