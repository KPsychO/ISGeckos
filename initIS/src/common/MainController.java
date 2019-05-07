package common;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Juego.Control.JuegoController;
import Tienda.Control.TiendaController;
import Usuario.Control.UsuarioDTO;
import viewer.MainWindow;

public class MainController {
	
	private MainWindow mw;
	private UsuarioDTO _current_user;
	
	private Controller[] controllers = {
		new TiendaController(),
		new JuegoController()
	};

	@SuppressWarnings("unused")
	public MainController() {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				mw = new MainWindow();
				mw.setVisible(true);
				mw.reinicia(((TiendaController)controllers[0]).getPanel("Tienda", null, _current_user));
				
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
			        				mw.reinicia(newPanel);
			        				break;
			        			}
			        		}
		            	}
		            }
		        });
			}
		});
	}
	
}
