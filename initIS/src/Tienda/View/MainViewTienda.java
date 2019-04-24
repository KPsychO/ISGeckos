package Tienda.View;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import Juego.Control.JuegoDTO;
import Tienda.Control.TiendaDTO;

public class MainViewTienda extends JPanel {

	private static final long serialVersionUID = 1L;
	
	TiendaDTO _tiendaDTO;
	JPanel _panel;
	
	public MainViewTienda(String user_id) {
		
		_tiendaDTO = new TiendaDTO(user_id);
		
		initGUI();
		
		this.setVisible(true);
		
	}
	
	private void initGUI() {
		
		configPanel();
		addGames();
		
	}
	
	private void configPanel() {
		
		this.setLayout(new BorderLayout());
		_panel = new JPanel();
		_panel.setLayout(new BoxLayout(_panel, BoxLayout.Y_AXIS));
		
	}
	
	private void addGames() {
		
		List<JuegoDTO> games = _tiendaDTO.getJuegosEnTienda();
		
		for (JuegoDTO j : games) {
			
			_panel.add(new JuegoTienda(j));
			_panel.add(new JSeparator());
			
		}
		
		this.add(new JScrollPane(_panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		//this.add(_panel);
		
	}

}
