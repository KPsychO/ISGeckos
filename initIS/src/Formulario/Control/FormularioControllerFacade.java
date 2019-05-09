package Formulario.Control;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.JSONArray;

import Formulario.Control.EventoFormulario;
import Formulario.View.MainViewFormulario;
import Formulario.View.MainViewPublicacion;
import Juego.Control.JuegoDTO;
import Juego.Control.LogroDTO;
import Usuario.Control.UsuarioDTO;
import common.Controller;
import common.EventoCommon;

public class FormularioControllerFacade {
	
private Controller _controller;
	
	public FormularioControllerFacade(Controller controller) {
		
		_controller = controller;
	}
	
	public void evento(EventoFormulario e, UsuarioDTO _user) {
		switch (e) {
		case ViewFormulario:
			_controller.setPrincipalPanel(new MainViewFormulario(_user, this));
			break;
		case ViewPublicacion:
			_controller.evento(EventoCommon.Usuario, null, _user);
		case Perfil:
			_controller.evento(EventoCommon.Usuario, null, _user);
		
		default:
			break;
		}
	}
	
	public JPanel getFormularioPanel(UsuarioDTO dev) {
		return new MainViewFormulario(dev, this);
	}

	public JPanel getPublicacion(UsuarioDTO dev) {
		Object JPanel = null;
		if(this.getNumForm() != 0) {
			return new MainViewPublicacion(dev, this);
		}
		else {
			JOptionPane.showMessageDialog(null, "No hay mas formulariuos", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		} 
	}
	
	public int getNumForm() {
		JSONArray form = new JSONArray();
		form = new FormularioDAOJSON().getFormularies();
		return form.length();
	}

	public void insertarFormulario(FormularioDTO dto, String type) {
		SingletonFormularioDAO.getInstance().insertFormulary(dto, type);
	}

	public FormularioDTO crearFormulario(JuegoDTO dto, String type, String just) {
		FormularioDTO data = new FormularioDTO(_controller.getCurrentUser(), dto.get_id());
		data.set_id(dto.get_id());
		data.set_title(dto.get_title());
		data.set_descLong("");
		data.set_price(dto.get_price());
		data.set_pegi(dto.get_pegi());
		data.set_date("");
		data.set_descShort(just);
		data.set_genres(new ArrayList<String>());
		data.set_achievements(new ArrayList<LogroDTO>());
		data.set_developer(dto.get_developer());
		data.set_type(type);
		
		return data;
	}

	public void eliminarJuego(String id) {
		_controller.eliminarJuego(id);
	}

	public void eliminarFormularios(String id) {
		SingletonFormularioDAO.getInstance().deleteFormularies(id);
	}
}
