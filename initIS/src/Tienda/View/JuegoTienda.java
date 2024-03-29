package Tienda.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Juego.Control.JuegoDTO;
import Tienda.Control.EventoTienda;
import Tienda.Control.TiendaControllerFacade;

public class JuegoTienda extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JuegoDTO _juegoDTO;
	
	private JButton _icon;
	private JPanel _contents;
	private JPanel _desc;
	private TiendaControllerFacade _tiendaCont;
	
	
	public JuegoTienda(JuegoDTO juego, TiendaControllerFacade tiendCont) {
		
		_juegoDTO = juego;
		_tiendaCont = tiendCont;
		
		initGUI();
		this.setVisible(true);
		
	}
	
	private void initGUI() {
		
		configPanel();
		createIcon();
		createContents();
		createDesc();
		
		this.add(_icon);
		this.add(_contents);
		this.add(_desc);
		
		this.setVisible(true);
		
	}
	
	private void configPanel() {
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setMaximumSize(new Dimension(600, 200));
		this.setMinimumSize(new Dimension(600, 200));
		this.setPreferredSize(new Dimension(600, 200));
		
	}
	
	private void createIcon() {
		
		//_icon = new JButton(new ImageIcon("./resources//game_icon.jpg"));
		_icon = new JButton(new ImageIcon("./resources/game_icon.jpg"));
		_icon.addActionListener(new CargarButton());
		
	}
	
	class CargarButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			_tiendaCont.evento(EventoTienda.accesoJuego, _juegoDTO, _tiendaCont.getCurrentUser());
		}
	}
	
	private void createContents() {
		
		_contents = new JPanel(new BorderLayout());
		JPanel auxPanel = new JPanel(new FlowLayout());
		
		JLabel pegi = new JLabel();
		pegi.setText(String.valueOf(_juegoDTO.get_pegi()));
		
		JLabel price = new JLabel();
		price.setText(String.valueOf((float)_juegoDTO.get_price()/100) + " $ ");
		
		auxPanel.add(new JLabel(" Pegi: "));
		auxPanel.add(pegi);
		
		auxPanel.add(new JLabel("| Price: "));
		auxPanel.add(price);
		
		JLabel title = new JLabel();
		title.setText(" Title: " + _juegoDTO.get_title());
		
		_contents.add(title, BorderLayout.NORTH);
		_contents.add(auxPanel, BorderLayout.SOUTH);
		
	}
	
	private void createDesc() {
		
		_desc = new JPanel(new BorderLayout());
		_desc.setMaximumSize(new Dimension(200, 100));
		_desc.setMinimumSize(new Dimension(200, 100));
		_desc.setPreferredSize(new Dimension(200, 100));
		
		JTextArea tArea = new JTextArea();
		tArea.setText(_juegoDTO.get_descShort());
		tArea.setEditable(false);
		tArea.setSelectionStart(0);
		tArea.setSelectionEnd(0);
		
		JScrollPane sPane = new JScrollPane(tArea);

		_desc.add(sPane, BorderLayout.CENTER);
		
	}

}
