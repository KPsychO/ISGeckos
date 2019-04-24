package common;
import javax.swing.SwingUtilities;

import Tienda.Control.TiendaDAO;
import Tienda.Control.TiendaDAOJSON;
import viewer.MainWindow;

public class main {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		TiendaDAO tiendaDAO = new TiendaDAOJSON();
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				final MainWindow v = new MainWindow();
				v.setVisible(true);
			}
		});
	}
	
}
