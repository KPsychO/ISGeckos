package Usuario.Control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import Usuario.View.MainWindowAcuerdoSuscriptor;
import Usuario.View.MainWindowCrearCuenta;
import Usuario.View.MainWindowEliminarCuenta;
import Usuario.View.MainWindowIniciarSesion;
import Usuario.View.MainWindowModificarCuenta;
import Usuario.View.MainWindowPerfilUsuario;
import common.Controller;

public class UsuarioControllerFacade {

	private Controller _controller;
	private UsuarioDTO _user;
	
	public UsuarioControllerFacade(Controller controller, UsuarioDTO user) {
		_controller = controller;
		_user = user;
	}
	

	public void evento(EventoUsuario e, UsuarioDTO _user) {
		switch (e) {
		
		case IniciarSesion:
			_controller.setCurrentUser(_controller.getUnregUser());
			_controller.setPrincipalPanel(new MainWindowIniciarSesion(this));
			break;
		
		case PerfilUsuario:
			if (_user != null) {
				_controller.setCurrentUser(_user);
				_controller.setPrincipalPanel(new MainWindowPerfilUsuario(_user, this));
			}
			
			break;
			
		case ModificarCuenta:
			_controller.setPrincipalPanel(new MainWindowModificarCuenta(_user, this));
			break;
		
		case EliminarCuenta:
			_controller.setPrincipalPanel(new MainWindowEliminarCuenta(_user, this));
			break;
		
		case CerrarSesion: 
			_controller.setCurrentUser(_controller.getUnregUser());
			_controller.setPrincipalPanel(new MainWindowIniciarSesion(this));
			break;
		
		case BotonCrearCuenta: 
			_controller.setPrincipalPanel(new MainWindowAcuerdoSuscriptor(this));
			break;
			
		case AcuerdoSuscriptor: 
			_controller.setPrincipalPanel(new MainWindowCrearCuenta(this));
			break;
			
		case CrearCuenta:
			_controller.setPrincipalPanel(new MainWindowPerfilUsuario(_user, this));
			break;
		
		case Formulario:
			_controller.setPrincipalPanel(_controller.getFormularioPanel());
			break;
		
		case Publicacion:
			_controller.setPrincipalPanel(_controller.getPublicacionPanel());
			break;
		
		case Desarrolladora:
			_controller.setPrincipalPanel(_controller.getDesarrolladoraPanel());
			break;
			
		case Biblioteca:
			_controller.setPrincipalPanel(_controller.getBiblioteca());
			break;
		
		case RevIncMej: 
			_controller.setPrincipalPanel(_controller.getRevMej());
			break;
			
		default: break;
		
		}
	}
	
	public UsuarioDTO getUsuarioId(String ID) {
		return _user.getUsuarioID(ID);
	}

	@SuppressWarnings("exports")
	public JPanel getIconoPanel(UsuarioDTO dto) {
		if (dto.isUnregistered())
			return new MainWindowIniciarSesion(this);
		else
			return new MainWindowPerfilUsuario(dto, this);
	}
	
	public UsuarioDTO storeUser(String id, String name, String password, String email, String country, int balance, String desc,
			boolean user, boolean dev, boolean admin) {
		
		List<tipoCuenta> tipos = new ArrayList<tipoCuenta>();
		if (user) tipos.add(tipoCuenta.user);
		if (dev) tipos.add(tipoCuenta.developer);
		if (admin) tipos.add(tipoCuenta.admin);
		
		UsuarioDTO data = new UsuarioDTO(tipos, balance, desc, email, country, password, id, name);
		
		SingletonUsuarioDAO.getInstance().insertarUsuario(data);
		
		return data;
		
	}
	
	public void storeUser(UsuarioDTO dto) {
		SingletonUsuarioDAO.getInstance().insertarUsuario(dto);
	}


	public void eliminarUsuario(UsuarioDTO _dto) {
		SingletonUsuarioDAO.getInstance().deleteUser(_dto);
		_controller.eliminarUsuario(_dto);
	}
	
	public void quitaBalance(int b, UsuarioDTO u) {
		u.set_balance(u.get_balance() - b);
		SingletonUsuarioDAO.getInstance().saveUserData(u);
	}
	public void addBalance(int b) {
		_controller.addBalance(b);
		SingletonUsuarioDAO.getInstance().saveUserData(_controller.getCurrentUser());
	}
	public int get_ownedGames() {
		return _controller.get_ownedGames();
	}


	public void actualizarUsuario(UsuarioDTO _dto) {
		SingletonUsuarioDAO.getInstance().deleteUser(_dto);
		storeUser(_dto);
	}
}