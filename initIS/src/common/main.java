package common;
import javax.swing.SwingUtilities;

import org.json.JSONObject;

import Tienda.Control.TiendaDAO;
import Tienda.Control.TiendaDAOJSON;
import viewer.MainWindow;

public class main {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		// Aqui pones tu mierda DAO para cojer datos
		TiendaDAO tiendaDAO = new TiendaDAOJSON();
		JSONObject js = new JSONObject();
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				final MainWindow v = new MainWindow();
				v.setVisible(true);
			}
		});
	}
	
}
