package Formulario.Control;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

import Formulario.Control.FormularioDAO;

public class FormularioDTO {

	//En esta primera versión, solo pido: titulo, descripcion, precio y edad
	//Cuando esté listo añadiré las demás que sean necesarias
	
	private String _id;
	private String _title;
	private String _desc;
	private String _version;
	private String _notes;
	private int _pegi;
	private int _price;
	private List<String> _genres;
	
	FormularioDAO dao = new FormularioDAOJSON();
	
	public FormularioDTO(String title, String desc, int pegi, int price) {
		dao = new FormularioDAOJSON();
		_title = title;
		_desc = desc;
		_pegi = pegi;
		_price = price;
	}
	
	public FormularioDTO(JSONObject formulario) {
		
		_title = formulario.getString("_title");
		_desc = formulario.getString("_desc");
		_pegi = Integer.getInteger(formulario.getString("_pegi"));
		_price = Integer.getInteger(formulario.getString("_price"));
		
	}
	
	public FormularioDTO() {
		//solo para acceder a los formularios REVISAR
	}
	
	public String toString() {
		String aux = "";
		aux = " {" +  " \"_title\": \"" + this._title + "\", \"_price\": "  + this._price + ", \"_pegi\": " + this._pegi +  ", \"_desc\": " + this._desc + "} " ;
		return aux;
	}
	
	public void insert (FormularioDTO x) {
		dao.insertFormulary(dao.getFormularies(), x);
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
	
}

	




