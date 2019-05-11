package valoraciones.factories;

import valoraciones.model.text.Text;
import valoraciones.model.text.TextTitulo;

public class BuilderTextTitulo extends Builder<Text>{

	BuilderTextTitulo(){
		super(BuildersTextTypes.TITULO);
	}
	
	@Override
	protected Text createTheInstance(Object[] data) {
		Text text = new TextTitulo();

		if(!text.isTextValid((String)data[1]))
			throw new IllegalArgumentException("not valid text titulo");
		
		text.setText((String)data[1]);
		return text;
	}


}
