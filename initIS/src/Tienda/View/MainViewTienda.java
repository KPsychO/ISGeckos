package Tienda.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import Juego.Control.JuegoDTO;
import Tienda.Control.TiendaControllerFacade;
import Usuario.Control.UsuarioDTO;

public class MainViewTienda extends JPanel {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private boolean _click;

	private static TiendaControllerFacade _tiendaController;
	private JPanel _panel;
	private JPanel aux;
	private List<JuegoDTO> _games;

	public MainViewTienda(TiendaControllerFacade crtl) {
		
		_tiendaController = crtl;
		_click = false;
		_games = _tiendaController.getJuegosEnTienda();

		initGUI();

		this.setVisible(true);

	}
	
	private void initGUI() {

		configPanel();
		addSearch();
		addGames();

	}

	private void configPanel() {

		this.setLayout(new BorderLayout());
		_panel = new JPanel();
		_panel.setLayout(new BoxLayout(_panel, BoxLayout.Y_AXIS));
		
		aux = new JPanel();
		aux.setLayout(new BoxLayout(aux, BoxLayout.Y_AXIS));

	}

	private void addSearch() {

		JPanel search = new JPanel();
		search.setLayout(new BoxLayout(search, BoxLayout.X_AXIS));

		JTextField buscado = new JTextField("");
		buscado.setMinimumSize(new Dimension(400, 25));
		buscado.setMaximumSize(new Dimension(400, 25));
		buscado.setPreferredSize(new Dimension(400, 25));
		JButton buscar = new JButton("Buscar");

		buscar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				List<JuegoDTO> list = new ArrayList<JuegoDTO>();
				
				for (JuegoDTO j : _tiendaController.getJuegosEnTienda()) {

					if (j.get_title().toLowerCase().startsWith(buscado.getText().toLowerCase())) {

						list.add(j);

					}

				}

				_games = list;
				createGameList();
				_panel.revalidate();

			}

		});

		search.add(buscado);
		search.add(buscar);

		_panel.add(search);

	}

	private void addGames() {
		
		createGameList();

		JScrollPane jsp = new JScrollPane(_panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.getVerticalScrollBar().setUnitIncrement(20);
		this.add(jsp);

	}

	private void createGameList() {
		
		aux.removeAll();
		
		for (JuegoDTO j : _games) {

			JuegoTienda jj = new JuegoTienda(j, _tiendaController);
			
			aux.add(jj);
			aux.add(new JSeparator());

		}
		
		_panel.add(aux);
		_panel.revalidate();
		
	}

}
