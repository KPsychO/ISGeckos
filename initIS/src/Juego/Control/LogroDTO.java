package Juego.Control;

public class LogroDTO {
	
	private String _name;
	private String _obtencion;
	
	public LogroDTO(String name, String obtencion) {
		_name = name;
		_obtencion = obtencion;
	}

	public void updateName(String name) {
		if (name != null)
			_name = name;
	}
	
	public void updateObtencion(String obtencion) {
		if (obtencion != null)
			_obtencion = obtencion;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public String get_obtencion() {
		return _obtencion;
	}

	public void set_obtencion(String _obtencion) {
		this._obtencion = _obtencion;
	}
}
