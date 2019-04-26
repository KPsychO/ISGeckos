package Tienda.View;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import Juego.Control.JuegoDTO;
import Tienda.Control.TiendaDTO;
import viewer.MainWindow;

public class MainViewTienda extends JPanel{

	private static final long serialVersionUID = 1L;
	private boolean _click;
	
	TiendaDTO _tiendaDTO;
	JPanel _panel;
	
	public MainViewTienda(String user_id) {
		
		_tiendaDTO = new TiendaDTO(user_id);
		_click = false;
		
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
			
			JuegoTienda observed = new JuegoTienda(j);
	        observed.addPropertyChangeListener(new PropertyChangeListener() {

	            @Override
	            public void propertyChange(PropertyChangeEvent e) {
	            	firePropertyChange(e.getPropertyName(), e.getOldValue(), e.getNewValue());
	            }
	        });
	        
			_panel.add(observed);
			_panel.add(new JSeparator());
			
		}
		JScrollPane jsp = new JScrollPane(_panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.getVerticalScrollBar().setUnitIncrement(20);
		this.add(jsp);
		//this.add(_panel);
		
	}

}
