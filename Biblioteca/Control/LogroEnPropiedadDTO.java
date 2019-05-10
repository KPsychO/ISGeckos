package Biblioteca.Control;

import Juego.Control.LogroDTO;

public class LogroEnPropiedadDTO extends LogroDTO{

	private boolean _owned;
	
	public LogroEnPropiedadDTO(String id, boolean owned) {
		super(id);
		this._owned = owned;
	}

	public boolean is_owned() {
		return _owned;
	}

	public void set_owned(boolean _owned) {
		this._owned = _owned;
	}

}
