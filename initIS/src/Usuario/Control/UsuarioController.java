package Usuario.Control;

import javax.swing.JPanel;

import IncidenciasMejoras.View.MainViewIncidenciasJugador;
import Usuario.View.MainWindowAcuerdoSuscriptor;
import Usuario.View.MainWindowCrearCuenta;
import Usuario.View.MainWindowEliminarCuenta;
import Usuario.View.MainWindowIniciarSesion;
import Usuario.View.MainWindowModificarCuenta;
import Usuario.View.MainWindowPerfilUsuario;
import common.Controller;

public class UsuarioController extends Controller{
	
	private UsuarioDTO _dto;
	
	public UsuarioController(UsuarioDTO dto) {
		
		_dto = dto;
		
	}

	
	@Override
	public JPanel getPanel(String panel, Object o, UsuarioDTO user) {
		if (panel.equals("BotonInicio")) {
			
			if (user.isUnregistered())
				return new MainWindowIniciarSesion();
			else
				return new MainWindowPerfilUsuario(user);
			
		}
			
		else if (panel.equals("botonModificarCuenta"))
			return new MainWindowModificarCuenta(user);
		
		else if (panel.equals("ModificarCuenta")) 
			return new MainWindowPerfilUsuario(user);
		
		else if (panel.equals("botonEliminarCuenta")) 
			return new MainWindowEliminarCuenta(user);
		
		else if (panel.equals("EliminarCuenta")) 
			return new MainWindowIniciarSesion();
		
		else if (panel.equals("CerrarSesion")) 
			return new MainWindowIniciarSesion();
		
		else if (panel.equals("botonCrearCuenta")) 
			return new MainWindowAcuerdoSuscriptor();
		
		else if (panel.equals("AcuerdoSuscriptor")) 
			return new MainWindowCrearCuenta();
		
		else if (panel.equals("CrearCuenta")) 
			return new MainWindowIniciarSesion();
		
		else
			return null;
	}
	

}
