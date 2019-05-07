package Usuario.Control;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import IncidenciasMejoras.View.MainViewIncidenciasJugador;
import Usuario.View.MainWindowAcuerdoSuscriptor;
import Usuario.View.MainWindowCrearCuenta;
import Usuario.View.MainWindowEliminarCuenta;
import Usuario.View.MainWindowIniciarSesion;
import Usuario.View.MainWindowModificarCuenta;
import Usuario.View.MainWindowPerfilUsuario;
import common.Controller;
import common.MainController;

public class UsuarioController extends Controller{
	
	private static MainController _mc;
	
	private UsuarioDTO _dto;
	
	public UsuarioController(UsuarioDTO dto, MainController mc) {
		
		_mc = mc;
		_dto = dto;
		
	}

	@Override
	public JPanel getPanel(String panel, Object o, UsuarioDTO user) {
		if (panel.equals("IniciarSesion")) {
		
			if (user.isUnregistered())
				return new MainWindowIniciarSesion();
			else
				return new MainWindowPerfilUsuario(user);
			
		}
			
		else if (panel.equals("PerfilUsuario")) {
			if (o == null) {
				JOptionPane.showMessageDialog(null, "Username o password incorrecto/s", "Error", JOptionPane.ERROR_MESSAGE);
				return null;
			}
			_mc.modifyUser((UsuarioDTO) o);
			return new MainWindowPerfilUsuario((UsuarioDTO) o);
		}
		else if (panel.equals("PerfilUsuarioCurrent"))
			return new MainWindowPerfilUsuario(user);
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
