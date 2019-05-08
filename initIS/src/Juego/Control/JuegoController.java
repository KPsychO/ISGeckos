package Juego.Control;

import java.util.List;

import javax.swing.JPanel;

import org.json.JSONArray;

import Juego.View.MainViewActualizarJuego;
import Juego.View.MainViewDeveloper;
import Juego.View.MainViewJuego;
import Usuario.Control.UsuarioDTO;
import common.Controller;

public class JuegoController {
	
	JuegoDAO _dao;
	
	public JuegoController() {
		
		_dao = new JuegoDAOJSON();
		new JuegoDTO(this);
		
	}

	public JuegoDTO getJuego(String id) {
		List<JuegoDTO> games = _dao.getJuegos();
		
		for (JuegoDTO g : games) {
			
			if (g.get_id().equals(id)) {
				return g;
			}
			
		}
		return null;
	}

	@SuppressWarnings("exports")
	public List<String> getGenres(JSONArray jsonArray) {
		return _dao.getGenres(jsonArray);
	}

	@SuppressWarnings("exports")
	public List<LogroDTO> getLogros(JSONArray jsonArray) {
		return _dao.getLogros(jsonArray);
	}

	
	
}
