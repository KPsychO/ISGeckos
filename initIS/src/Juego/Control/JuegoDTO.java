package Juego.Control;

import java.util.*;

import org.json.JSONObject;

public class JuegoDTO {

	private String _id;
	private String _title;
	private String _desc;
	private String _version;
	private String _notes;
	private int _pegi;
	private int _price;
	private List<String> _genres;
	private List<LogroDAO> _achievements;
	
	JuegoDAO dao;
	
	// la imagen "thumb"
	

	public JuegoDTO(String id, String title, int price, int pegi, String desc) {
		
		dao = new JuegoDAOJSON();
		crearJuego(id, title, price, pegi, desc);

	}
	
	private void crearJuego(String id, String title, int price, int pegi, String desc) {
		
		set_id(id);
		_title = title;
		_desc = desc;
		_pegi = pegi;
		_price = price;
		_genres = new ArrayList<String>();
		//_achievements = dao.getLogros(get_id()); //Hay que hacer los JSON de logros
		_genres = new ArrayList<String>(); //Como hacemos los generos de los games?
		
	}

	public JuegoDTO(@SuppressWarnings("exports") JSONObject juego) {
		
		set_id(juego.getString("_id"));
		_title = juego.getString("_title");
		_desc = juego.getString("_desc");
		_pegi = Integer.getInteger(juego.getString("_pegi"));
		_price = Integer.getInteger(juego.getString("_price"));
		_genres = new ArrayList<String>();
		//_achievements = dao.getLogros(get_id());
		
	}
	
	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String get_title() {
		return _title;
	}

	public void set_title(String _title) {
		this._title = _title;
	}

	public String get_desc() {
		return _desc;
	}

	public void set_desc(String _desc) {
		this._desc = _desc;
	}

	public String get_version() {
		return _version;
	}

	public void set_version(String _version) {
		this._version = _version;
	}

	public String get_notes() {
		return _notes;
	}

	public void set_notes(String _notes) {
		this._notes = _notes;
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

	public List<String> get_genres() {
		return _genres;
	}

	public void set_genres(List<String> _genres) {
		this._genres = _genres;
	}

	public List<LogroDAO> get_achievements() {
		return _achievements;
	}

	public void set_achievements(List<LogroDAO> _achievements) {
		this._achievements = _achievements;
	}
	
}
