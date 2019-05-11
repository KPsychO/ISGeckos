package valoraciones.factories;

import valoraciones.model.text.Text;
import valoraciones.model.text.TextComentario;

public class BuilderTextComentario extends Builder<Text>{
	
	BuilderTextComentario(){
		super(BuildersTextTypes.COMENTARIO);
	}
	
	@Override
	protected Text createTheInstance(Object[] data) {
		Text text = new TextComentario();
		
		if(!text.isTextValid((String)data[1]))
			throw new IllegalArgumentException("not valid text comentario");
		
		text.setText((String)data[1]);
		return text;
	}

}
