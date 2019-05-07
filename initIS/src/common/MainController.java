package common;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Biblioteca.Control.BibliotecaDTO;
import Biblioteca.View.MainViewBiblioteca;
import Comunidad.View.MainViewPerfilUsuarioDenunciado;
import Formulario.View.MainViewPublicacion;
import Formulario.View.ViewFormulario;
import IncidenciasMejoras.View.MainViewDenunciasJugador;
import Juego.Control.JuegoDTO;
import Juego.View.MainViewJuego;
import Tienda.View.ComprarJuego;
import Tienda.View.MainViewTienda;
import Usuario.Control.UsuarioDAOJSON;
import Usuario.Control.UsuarioDTO;
import Usuario.Control.tipoCuenta;
import Usuario.View.MainWindowAcuerdoSuscriptor;
import Usuario.View.MainWindowCrearCuenta;
import Usuario.View.MainWindowEliminarCuenta;
import Usuario.View.MainWindowIniciarSesion;
import Usuario.View.MainWindowModificarCuenta;
import Usuario.View.MainWindowPerfilUsuario;
import viewer.MainWindow;

public class MainController {
	
	private MainWindow mw;
	
	@SuppressWarnings("unused")
	public MainController() {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				mw = new MainWindow();
				mw.addPropertyChangeListener(new PropertyChangeListener() {
		            @Override
		            public void propertyChange(PropertyChangeEvent e) {
		            	try {
		            		if (e.getPropertyName().equals("JuegoTienda")) {
		            			principalPanel = new MainViewJuego((JuegoDTO)e.getNewValue());
		            			reinicia();
		            		}
		            		else if (e.getPropertyName().equals("DenunciarJugador")) {
		            			principalPanel = new MainViewDenunciasJugador("");
		            			reinicia();
		            		}
		            		else if (e.getPropertyName().equals("Soporte")) {
		            			principalPanel = new MainViewTienda(_current_user);
		            			reinicia();
		            		}
		            		else if (e.getPropertyName().equals("ComprarJuego")){
		            			if (state_unregistered) {
		            				String tipoError = "Tienes que estar logeado para comprar juegos";
		            				JOptionPane.showMessageDialog(MainWindow.this, tipoError, "Error", JOptionPane.ERROR_MESSAGE);
		            			}
		            			else {
		            				principalPanel = new ComprarJuego((JuegoDTO)e.getNewValue());
		            				reinicia();
		            			}
		            		}
		            		else if (e.getPropertyName().equals("CrearCuenta")){
		            			principalPanel = new MainWindowCrearCuenta();
		            			reinicia();
		            		}
		            		else if (e.getPropertyName().equals("IniciarSesion")){
		            			principalPanel = new MainWindowIniciarSesion();
		        				reinicia();
		            		}
		            		
		            		else if (e.getPropertyName().equals("PerfilUsuario")){
		            			_current_user = (UsuarioDTO)e.getNewValue();
		            			
		            			if (_current_user != null) {
		            				changeBoxes(_current_user);
		            				principalPanel = new MainWindowPerfilUsuario(_current_user);
		            				reinicia();
		            			}
		            			
		            		}
		            		
		            		else if (e.getPropertyName().equals("AcuerdoSuscriptor")){
		            			principalPanel = new MainWindowAcuerdoSuscriptor();
		        				reinicia();
		            		}
		            		
		            		else if (e.getPropertyName().equals("EliminarCuenta")){
		            			principalPanel = new MainWindowEliminarCuenta(_current_user);
		        				reinicia();
		            		}
		            		
		            		else if (e.getPropertyName().equals("ModificarCuenta")){
		            			principalPanel = new MainWindowModificarCuenta(_current_user);
		        				reinicia();
		            		}
		            		
		            		else if (e.getPropertyName().equals("Biblioteca")){
		            			principalPanel = new MainViewBiblioteca((BibliotecaDTO)e.getNewValue());
		            			reinicia();
		            		}
		            		else if (e.getPropertyName().equals("PerfilUsuarioDenunciado")){
		            			principalPanel = new MainViewPerfilUsuarioDenunciado(null);
		            			reinicia();
		            		}	
		            		else if (e.getPropertyName().equals("VerEnTienda")){
		            			principalPanel = new MainViewJuego((JuegoDTO)e.getNewValue());
		            			reinicia();
		            		}
		            		else if (e.getPropertyName().equals("Formulario")){
		            			principalPanel = new ViewFormulario();
		            			reinicia();
		            		}
		            		else if (e.getPropertyName().equals("Publicacion")){
		            			principalPanel = new MainViewPublicacion();
		            			reinicia();
		            		}
		            		else if (e.getPropertyName().equals("CerrarSesion")){
		            			_current_user = new UsuarioDAOJSON().getUnregUser();
		            			changeBoxes(_current_user);
		            			principalPanel = new MainWindowIniciarSesion();
		            			reinicia();
		            		}
		            		
		            	}
		            	catch(Exception e1) {
		            		//Nada
		            	}
		            }

					private void changeBoxes(UsuarioDTO user) {
						List<tipoCuenta> types = user.get_types();
						
						state_unregistered = types.contains(tipoCuenta.unregistered) ?  true : false;
						state_user = types.contains(tipoCuenta.user) ?  true : false;
						state_developer = types.contains(tipoCuenta.developer) ?  true : false;
						state_admin = types.contains(tipoCuenta.admin) ?  true : false;
						
					}
		        });
				mw.setVisible(true);
			}
		});
	}
	
}
