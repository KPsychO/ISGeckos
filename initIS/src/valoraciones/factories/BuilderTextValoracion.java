package valoraciones.factories;

import valoraciones.model.text.Text;
import valoraciones.model.text.TextValoracion;

public class BuilderTextValoracion extends Builder<Text>{

	BuilderTextValoracion(){
		super(BuildersTextTypes.VALORACION);
	}
	
	@Override
	protected Text createTheInstance(Object[] data) throws IllegalArgumentException{
		Text text = new TextValoracion();
		
		if(!text.isTextValid((String)data[1]))
			throw new IllegalArgumentException("not valid text valoracion");
		
		text.setText((String)data[1]);
		return text;
	}
}
