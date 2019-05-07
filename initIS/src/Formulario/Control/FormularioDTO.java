package Formulario.Control;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import Formulario.Control.FormularioDAO;
import Juego.Control.LogroDTO;

public class FormularioDTO {

	//En esta primera versión, solo pido: titulo, descripcion, precio y edad
	//Cuando esté listo añadiré las demás que sean necesarias
	
	private String _id;
	private String _title;
	private String _descLong;
	private String _descShort;
	private String _notes;
	private String _date;
	private String _developer; //Para esto es necesario que funcione el usuario, asi que...
	private int _version;
	private int _pegi;
	private int _price;
	private List<String> _genres;
	private List<LogroDTO> _achievements;
	
	FormularioDAO dao = new FormularioDAOJSON();
	
	public FormularioDTO(String title, String desc, int pegi, int price) {
		
		dao = new FormularioDAOJSON();
		
		_id = UUID.randomUUID().toString();
		_title = title;
		_descShort = desc;
		_pegi = pegi;
		_price = price;
		
	}
	
	public FormularioDTO(JSONObject formulario) {
		
		dao = new FormularioDAOJSON();
		_id = UUID.randomUUID().toString();
		_title = formulario.getString("_title");
		_descShort = formulario.getString("_desc");
		_pegi = Integer.getInteger(formulario.getString("_pegi"));
		_price = Integer.getInteger(formulario.getString("_price"));
		
	}
	
	public FormularioDTO() {
		dao = new FormularioDAOJSON();
		_id = UUID.randomUUID().toString();
		_genres = new ArrayList<String>();
		//solo para acceder a los formularios REVISAR
		//Revisado, usado para crearlo de forma mas limpia
	}
	
	//Este metodo no se va a necesitar ya
	public String toString() {
		String aux = "";
		aux = " {" +  " \"_title\": \"" + this._title + "\", \"_price\": "  
				+ this._price + ", \"_pegi\": " + this._pegi +  ", \"_desc\": " + this._descShort + "} " ;
		return aux;
	}
	
	public void insertGame(int n) {
		dao.insertGame(n);
	}
	
	public void deleteFormulary(int n) {
		dao.deleteFormulary(n);
	}
	
	public void insert (FormularioDTO x) {
		dao.insertFormulary(x);
	} 
	
	public void addGenres(String g) {
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

	public List<LogroDTO> get_achievements() {
		return _achievements;
	}

	public void set_achievements(List<LogroDTO> _achievements) {
		this._achievements = _achievements;
	}

	public JSONArray getFormularies() {
		// TODO Auto-generated method stub
		return null;
	}
	
}




