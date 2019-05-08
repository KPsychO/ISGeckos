package common;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Biblioteca.Control.BibliotecaController;
import Comunidad.View.ComunidadController;
import Formulario.Control.FormularioController;
import IncidenciasMejoras.Control.IncidenciasMejorasController;
import Juego.Control.JuegoController;
import Tienda.Control.TiendaController;
import Usuario.Control.UsuarioController;
import Usuario.Control.UsuarioDTO;
import Usuario.Control.tipoCuenta;
import viewer.MainWindow;

public class MainController {
	
	private MainWindow mw;
	private static UsuarioDTO _current_user;
	
	private Controller[] controllers = {
		new JuegoController(),
		new TiendaController(),
		new ComunidadController(),
		new IncidenciasMejorasController(),
		new FormularioController(),
		new UsuarioController(new UsuarioDTO(null, 0, null, null, null, null, "0000000000", null), this),
		new BibliotecaController()
		
	};

	@SuppressWarnings("unused")
	public MainController() {
		
		List<tipoCuenta> types = new ArrayList<tipoCuenta>();
		types.add(tipoCuenta.unregistered);
		_current_user = new UsuarioDTO(types, 0, null, null, null, null, "0000000000", null);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				mw = new MainWindow();
				mw.setVisible(true);
				mw.reinicia(((TiendaController)controllers[1]).getPanel("Tienda", null, _current_user), _current_user);
				
				mw.addPropertyChangeListener(new PropertyChangeListener() {
		            @Override
		            public void propertyChange(PropertyChangeEvent e) {
		            	if (e.getPropertyName().equals("UserChange")) {
		            		_current_user = (UsuarioDTO)e.getNewValue();
		            	}
		            	else {
		            		JPanel newPanel = new JPanel();
			            	for (Controller c : controllers) {
			        			newPanel = c.getPanel(e.getPropertyName(), e.getNewValue(), _current_user);
			        			if (newPanel != null) {
			        				mw.reinicia(newPanel, _current_user);
			        				break;
			        			}
			        		}
		            	}
		            }
		        });
			}
		});
	}

	public void modifyUser(UsuarioDTO o) {
		_current_user = o;
	}
	
	public static UsuarioDTO getCurrentUser() {
		
		return _current_user;
		
	}
	
}
