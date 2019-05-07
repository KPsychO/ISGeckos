package Usuario.Control;

import javax.swing.JPanel;

import Usuario.View.MainWindowAcuerdoSuscriptor;
import Usuario.View.MainWindowCrearCuenta;
import Usuario.View.MainWindowEliminarCuenta;
import Usuario.View.MainWindowIniciarSesion;
import Usuario.View.MainWindowModificarCuenta;
import Usuario.View.MainWindowPerfilUsuario;

public class UsuarioController {

	
	@Override
	public JPanel getPanel(String panel, Object o, UsuarioDTO user) {
		if (panel.equals("Soporte"))
			return new MainViewIncidenciasJugador(user);
		else if (panel.equals("DenunciarJugador"))
			return new MainViewDenunciasJugador(user, (UsuarioDTO) o);
		else
			return null;
	}
	
	//Controller de usuario
	
	//Acceso a perfil de usuario
	else if (e.getPropertyName().equals("IniciarSesion")){
		_current_user = (UsuarioDTO)e.getNewValue();
		changeBoxes (_current_user);
		principalPanel = new MainWindowPerfilUsuario(_current_user);
		reinicia();
	}
	
	//Acceso a pantalla Modificar
	else if (e.getPropertyName().equals("botonModificarCuenta")){
		principalPanel = new MainWindowModificarCuenta(_current_user);
		reinicia();
	}
	
	//Tras modificar perfil
	else if (e.getPropertyName().equals("ModificarCuenta")) {
		principalPanel = new MainWindowPerfilUsuario(_current_user);
		reinicia();
	}
	
	//Acceso a pantalla Eliminar
	else if (e.getPropertyName().equals("botonEliminarCuenta")){
		principalPanel = new MainWindowEliminarCuenta(_current_user);
		reinicia();
	}
	
	//Tras eliminarCuenta
	else if (e.getPropertyName().equals("EliminarCuenta")){
		_current_user = new UsuarioDAOJSON().getUnregUser();
		changeBoxes(_current_user);
		principalPanel = new MainWindowIniciarSesion();
		reinicia();
	}
	
	//Cerrar sesion
	else if (e.getPropertyName().equals("CerrarSesion")){
		_current_user = new UsuarioDAOJSON().getUnregUser();
		changeBoxes(_current_user);
		principalPanel = new MainWindowIniciarSesion();
		reinicia();
	}
	
	//Boton crear cuenta
	else if (e.getPropertyName().equals("botonCrearCuenta")){
		principalPanel = new MainWindowAcuerdoSuscriptor();
		reinicia();
	}
	
	//Acuerdo de suscriptor
	else if (e.getPropertyName().equals("AcuerdoSuscriptor")){
		principalPanel = new MainWindowCrearCuenta();
		reinicia();
	}
	
	//CrearCuenta
	else if (e.getPropertyName().equals("CrearCuenta")){
		principalPanel = new MainWindowIniciarSesion();
		reinicia();
	}

}
