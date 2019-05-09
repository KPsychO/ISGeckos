package Formulario.Control;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import Formulario.Control.FormularioDAO;
import Juego.Control.LogroDTO;
import Usuario.Control.UsuarioDTO;

public class FormularioDTO {

	//En esta primera versi�n, solo pido: titulo, descripcion, precio y edad
	//Cuando est� listo a�adir� las dem�s que sean necesarias
	
	private String _id;
	private String _title;
	private String _descLong;
	private String _descShort;
	private String _notes;
	private String _date;
	private String _developer; 
	private String _type;
	private int _version;
	private int _pegi;
	private int _price;
	private List<String> _genres;
	private List<LogroDTO> _achievements;
	
	public FormularioDTO(String title, String desc, int pegi, int price, UsuarioDTO dev, String type) {
		
		_id = UUID.randomUUID().toString();
		_title = title;
		_descShort = desc;
		_pegi = pegi;
		_price = price;
		_developer = dev.get_user_id();
		_type = type;
		
	}
	
	public FormularioDTO(JSONObject formulario) {
		
		_id = formulario.getString("_id");
		_title = formulario.getString("_title");
		_descShort = formulario.getString("_descShort");
		_pegi = formulario.getInt("_pegi");
		_price = formulario.getInt("_price");
		_developer = formulario.getString("_developer");
		_type = formulario.getString("_type");
		
	}
	
	public FormularioDTO(UsuarioDTO dev) {

		_id = UUID.randomUUID().toString();
		_genres = new ArrayList<String>();
		_developer = dev.get_user_id();
		//solo para acceder a los formularios REVISAR
		//Revisado, usado para crearlo de forma mas limpia
	}
	
	public FormularioDTO(UsuarioDTO dev, String id) {

		_id = id;
		_genres = new ArrayList<String>();
		_developer = dev.get_user_id();
		//solo para acceder a los formularios REVISAR
		//Revisado, usado para crearlo de forma mas limpia
	}
	
	public void insertGame(int n) {
		SingletonFormularioDAO.getInstance().insertGame(n);
	}
	
	public void deleteFormulary(int n) {
		SingletonFormularioDAO.getInstance().deleteFormulary(n);
	}
	
	public void insert (FormularioDTO x, String type) {
		SingletonFormularioDAO.getInstance().insertFormulary(x, type);
	} 
	
	public void addGenres(String g) {
		if (!_genres.contains(g))
			_genres.add(g);
	}
	
	public String get_title() {
		return _title;
	}

	public void set_title(String _title) {
		this._title = _title;
	}

	public String get_desc() {
		return _descShort;
	}

	public void set_desc(String _desc) {
		this._descShort = _desc;
	}

	public int get_pegi() {
		return _pegi;
	}

	public void set_pegi(int _pegi) {
		this._pegi = _pegi;
	}

	public int get_price() {
		return _price;
	}

	public void set_price(int _price) {
		this._price = _price;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String get_descLong() {
		return _descLong;
	}

	public void set_descLong(String _descLong) {
		this._descLong = _descLong;
	}

	public String get_descShort() {
		return _descShort;
	}

	public void set_descShort(String _descShort) {
		this._descShort = _descShort;
	}

	public String get_notes() {
		return _notes;
	}

	public void set_notes(String _notes) {
		this._notes = _notes;
	}

	public String get_date() {
		return _date;
	}

	public void set_date(String _date) {
		this._date = _date;
	}

	public int get_version() {
		return _version;
	}

	public void set_version(int _version) {
		this._version = _version;
	}

	public List<String> get_genres() {
		return _genres;
	}

	public void set_genres(List<String> _genres) {
		this._genres = _genres;
	}

	public JSONArray get_achievements() {
		JSONArray arr = new JSONArray();
		
		for (LogroDTO l : this._achievements) {
			JSONObject logro = new JSONObject();
			logro.put("_id", l.get_id());
			logro.put("_name", l.get_name());
			logro.put("_getMode", l.get_getMode());
			
			arr.put(logro);
		}
		
		
		return arr;
		
	}

	public void set_achievements(List<LogroDTO> _achievements) {
		this._achievements = _achievements;
	}

	public JSONArray getFormularies() {
		// TODO Auto-generated method stub
		return null;
	}

	public String get_developer() {
		return _developer;
	}

	public void set_developer(String _developer) {
		this._developer = _developer;
	}
	
	public String get_type() {
		return _type;
	}

	public void set_type(String _type) {
		this._type = _type;
	}
	
}




