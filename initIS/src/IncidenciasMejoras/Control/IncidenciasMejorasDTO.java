package IncidenciasMejoras.Control;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import Usuario.Control.UsuarioDTO;


public class IncidenciasMejorasDTO {
	private String _desc;	
	private String _coment;
	private String _type;
	private String _id_user;
	private String _id_user_Denun;
	private String _id_game;
	IncidenciasDAO dao = new IncidenciasDAOJSON();

	private List<IncidenciasMejorasDTO> inciMejor = new ArrayList<IncidenciasMejorasDTO>();
	
	public IncidenciasMejorasDTO(String type, String idUser, String idUserDenun, String idGame, String desc, String coment) {
		_desc = desc;
		_coment = coment;
		_type = type;
		_id_user = idUser;
		_id_user_Denun = idUserDenun;
		_id_game = idGame;
	}
	
	public IncidenciasMejorasDTO(UsuarioDTO im) {
		dao = new IncidenciasDAOJSON();
	}
	
	public IncidenciasMejorasDTO(JSONObject im) {
		dao = new IncidenciasDAOJSON();
		_type = im.getString("_type");
		_id_user = im.getString("_id_user");
		switch (_type) {
		case "IncJue": 
			_id_game = im.getString("_id");
			break;
		case "DenJug":
			_id_user_Denun = im.getString("_id_user_Denun");
			break;
		case "DenJue":
			_id_game = im.getString("_id");
			break;
		}
		_desc = im.getString("_desc");
		_coment = im.getString("_coment");
	}

	public void eliminarIncidenciaMejora(int n) {
		dao.borrarIncidencia(n);
	}
	
	public String get_type() {
		return _type;
	}

	public void set_type(String _type) {
		this._type = _type;
	}

	public String get_desc() {
		return _desc;
	}

	public void set_desc(String _desc) {
		this._desc = _desc;
	}

	public String get_coment() {
		return _coment;
	}

	public void set_coment(String _coment) {
		this._coment = _coment;
	}

	public String get_id_user() {
		return _id_user;
	}

	public void set_id_user(String _id_user) {
		this._id_user = _id_user;
	}

	public String get_id_user_Denun() {
		return _id_user_Denun;
	}

	public void set_id_user_Denun(String _id_user_Denun) {
		this._id_user_Denun = _id_user_Denun;
	}

	public String get_id_game() {
		return _id_game;
	}

	public void set_id_game(String _id_game) {
		this._id_game = _id_game;
	}

	
	
}
