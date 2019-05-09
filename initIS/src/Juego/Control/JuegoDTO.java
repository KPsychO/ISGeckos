package Juego.Control;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class JuegoDTO {

	private String _id;
	private String _title;
	private String _descLong;
	private String _descShort;
	private String _notes;
	private String _date;
	private int _version;
	private int _pegi;
	private int _price;
	private List<String> _genres;
	private List<LogroDTO> _achievements;
	
	// la imagen "thumb"
	

	public JuegoDTO(String id, String title, int price, int pegi, int version, 
			String descL, String descS, List<String> genres, List<LogroDTO> achievements) {
		
		crearJuego(id, title, price, pegi, version, descL, descS, genres, achievements);

	}
	
	public JuegoDTO(String id) {

		JuegoDTO g = SingletonJuegoDAO.getInstance().getJuegoID(id);
		if (g == null)
			return;
		
		this._achievements = g._achievements;
		this._date = g._date;
		this._descLong = g._descLong;
		this._descShort = g._descShort;
		this._genres = g._genres;
		this._id = g._id;
		this._notes = g._notes;
		this._pegi = g._pegi;
		this._price = g._price;
		this._title = g._title;
		this._version = g._version;

	}
	
	private void crearJuego(String id, String title, int price, int pegi, int version, 
			String descL, String descS, List<String> genres, List<LogroDTO> achievements) {
		
		_id = id;
		_title = title;
		_descLong = descL;
		_descShort = descS;
		_pegi = pegi;
		_price = price;
		_version = version;
		_genres = genres;
		_achievements = achievements;
		
	}

	@SuppressWarnings("exports")
	public JuegoDTO(JSONObject juego) {
		
		_id = juego.getString("_id");
		_title = juego.getString("_title");
		_descLong = juego.getString("_descLong");
		_descShort = juego.getString("_descShort");
		
		_pegi = juego.getInt("_pegi");
		_price = juego.getInt("_price");
		//_version = juego.getInt("_version");

		_date = juego.getString("_date");
		_genres = SingletonJuegoDAO.getInstance().getGenres(juego.getJSONArray("_genres"));
		_achievements = SingletonJuegoDAO.getInstance().getLogros(juego.getJSONArray("_achievements"));
		
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

	public List<LogroDTO> get_achievements() {
		return _achievements;
	}

	public void set_achievements(List<LogroDTO> _achievements) {
		this._achievements = _achievements;
	}
	
}
