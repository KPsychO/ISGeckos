package common;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Juego.Control.JuegoController;
import Tienda.Control.TiendaController;
import viewer.MainWindow;

public class MainController {
	
	private MainWindow mw;
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
				mw.reinicia(((TiendaController)controllers[0]).getPanel("Tienda", null));
				
				mw.addPropertyChangeListener(new PropertyChangeListener() {
		            @Override
		            public void propertyChange(PropertyChangeEvent e) {
		            	JPanel newPanel = new JPanel();
		            	for (Controller c : controllers) {
		        			newPanel = c.getPanel(e.getPropertyName(), e.getNewValue());
		        			if (newPanel != null) {
		        				mw.reinicia(newPanel);
		        				break;
		        			}
		        		}

		            }
		        });
				
			}
		});
	}
	
}
