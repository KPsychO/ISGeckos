package Usuario.Control;

import Usuario.View.MainWindowAcuerdoSuscriptor;
import Usuario.View.MainWindowEliminarCuenta;
import Usuario.View.MainWindowModificarCuenta;
import Usuario.View.MainWindowCrearCuenta;
import Usuario.View.MainWindowIniciarSesion;
import Usuario.View.MainWindowPerfilUsuario;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Usuario.Control.EventoUsuario;
import Usuario.Control.UsuarioDTO;
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
			_controller.setCurrentUser(getUsuarioUnreg());
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
			_controller.setCurrentUser(getUsuarioUnreg());
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
	
	public UsuarioDTO getUsuarioUnreg() {
		return _user.getUsuarioID("0000000000");
	}


	public JPanel getIconoPanel(UsuarioDTO dto) {
		if (dto.isUnregistered())
			return new MainWindowIniciarSesion(this);
		else
			return new MainWindowPerfilUsuario(dto, this);
	}
	
	public void storeUser(String id, String name, String password, String email, String country, int balance, String desc,
			boolean user, boolean dev, boolean admin) {
		
		List<tipoCuenta> tipos = new ArrayList<tipoCuenta>();
		if (user) tipos.add(tipoCuenta.user);
		if (dev) tipos.add(tipoCuenta.developer);
		if (dev) tipos.add(tipoCuenta.admin);
		
		UsuarioDTO data = new UsuarioDTO(tipos, balance, desc, email, country, password, id, name);
		
		new UsuarioDAOJSON().insertarUsuario(data);
		
	}
	
	public void storeUser(UsuarioDTO dto) {
		new UsuarioDAOJSON().insertarUsuario(dto);
	}


	public void eliminarUsuario(UsuarioDTO _dto) {
		new UsuarioDAOJSON().deleteUser(_dto);
	}
	
	public void quitaBalance(int b, UsuarioDTO u) {
		u.set_balance(u.get_balance() - b);
		// GUARDAR EN EL JSON EL NUEVO BALANCE
	}
	public void addBalance(int b) {
		_controller.addBalance(b);
	}
	public int get_ownedGames() {
		return _controller.get_ownedGames();
	}
}