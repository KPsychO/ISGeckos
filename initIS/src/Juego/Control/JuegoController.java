package Juego.Control;

import java.util.List;

import javax.swing.JPanel;

import org.json.JSONArray;

import Juego.View.MainViewActualizarJuego;
import Juego.View.MainViewDeveloper;
import Juego.View.MainViewJuego;
import Usuario.Control.UsuarioDTO;
import common.Controller;

public class JuegoController extends Controller{
	
	JuegoDAO _dao;
	
	public JuegoController() {
		
		_dao = new JuegoDAOJSON();
		new JuegoDTO(this);
		
	}

	@Override
	public JPanel getPanel(String panel, Object o, UsuarioDTO user) {
		if (panel.equals("JuegoTienda"))
			return new MainViewJuego((JuegoDTO)o);
		
		else if (panel.equals("VerEnTienda"))
			return new MainViewJuego((JuegoDTO)o);
		
		else if (panel.equals("Desarrolladora"))
			return new MainViewDeveloper(user);
		
		else if (panel.equals("ActualizarJuego"))
			return new MainViewActualizarJuego(user, (JuegoDTO)o);
		
		else
			return null;
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

	public List<String> getGenres(JSONArray jsonArray) {
		return _dao.getGenres(jsonArray);
	}

	public List<LogroDTO> getLogros(JSONArray jsonArray) {
		return _dao.getLogros(jsonArray);
	}

	
	
}
