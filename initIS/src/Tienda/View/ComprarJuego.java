package Tienda.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Juego.Control.JuegoDTO;
import Tienda.Control.EventoTienda;
import Tienda.Control.TiendaControllerFacade;

public class ComprarJuego extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private JuegoDTO _juego;
	private JPanel _panel;
	private TiendaControllerFacade _tiendaCont;
	
	private JTextField tfnombre;
	private JTextField tfapell;
	private JTextField tfedad;
	private JTextField tfcuenta;
	private JTextField tfcvv;
	private JTextField tfcad;
	
	
	public ComprarJuego(JuegoDTO juego, TiendaControllerFacade tc) {
		
		_tiendaCont = tc;
		_juego = juego;
		initGUI();
		this.setVisible(true);
		
	}
	
	private void initGUI() {
		
		configPanel();
		addComponents();
		
	}
	
	private void configPanel() {
		
		this.setLayout(new BorderLayout());
		_panel = new JPanel();
		_panel.setLayout(new BoxLayout(_panel, BoxLayout.Y_AXIS));
		
	}
	
	private void addComponents() {
		
		gameInfo();
		
		_panel.add(new JSeparator());
		
		form();
		
		_panel.add(new JSeparator());
		
		bcomprar();
		
		this.add(_panel);
		
	}
	
	private void gameInfo() {
		
		JPanel gameInfo = new JPanel();
		gameInfo.setLayout(new BoxLayout(gameInfo, BoxLayout.X_AXIS));
		JPanel name_price = new JPanel();
		name_price.setLayout(new BoxLayout(name_price, BoxLayout.Y_AXIS));
		name_price.add(new JLabel(_juego.get_title()));
		name_price.add(new JLabel(String.valueOf(_juego.get_price())));
		gameInfo.add(new JLabel(new ImageIcon("./src/resources/game_icon.jpg")));
		gameInfo.add(name_price);
		JPanel desc = new JPanel(new BorderLayout());
		desc.setMaximumSize(new Dimension(200, 100));
		desc.setMinimumSize(new Dimension(200, 100));
		desc.setPreferredSize(new Dimension(200, 100));
		
		JTextArea tArea = new JTextArea();
		tArea.setText(_juego.get_descShort());
		tArea.setEditable(false);

		tArea.setSelectionStart(0);
		tArea.setSelectionEnd(0);
		tArea.setWrapStyleWord(true);
		tArea.setLineWrap(true);
		
		JScrollPane sPane = new JScrollPane(tArea);

		gameInfo.add(sPane, BorderLayout.CENTER);
		
		_panel.add(gameInfo);
		
	}
	
	private void form() {
		
		JPanel form = new JPanel();
		form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
		
		JLabel nombre = new JLabel("Nombre (Solo caracteres, (NOT NULL):");
		JLabel apell = new JLabel("Apellidos (Solo caracteres, (NOT NULL):");
		JLabel edad = new JLabel("Edad (mayor de 12 y menor de 100):");
		JLabel cuenta = new JLabel("Cuenta (16 digitos):");
		JLabel cvv = new JLabel("CVV (3 digitos):");
		JLabel cad = new JLabel("Fecha caducidad:");
		
		tfnombre = new JTextField();
		tfnombre.setMaximumSize(new Dimension(400, 25));
		tfnombre.setMinimumSize(new Dimension(400, 25));
		tfnombre.setPreferredSize(new Dimension(400, 25));
		tfapell = new JTextField();
		tfapell.setMaximumSize(new Dimension(400, 25));
		tfapell.setMinimumSize(new Dimension(400, 25));
		tfapell.setPreferredSize(new Dimension(400, 25));
		tfedad = new JTextField();
		tfedad.setMaximumSize(new Dimension(400, 25));
		tfedad.setMinimumSize(new Dimension(400, 25));
		tfedad.setPreferredSize(new Dimension(400, 25));
		tfcuenta = new JTextField();
		tfcuenta.setMaximumSize(new Dimension(400, 25));
		tfcuenta.setMinimumSize(new Dimension(400, 25));
		tfcuenta.setPreferredSize(new Dimension(400, 25));
		tfcvv = new JTextField();
		tfcvv.setMaximumSize(new Dimension(400, 25));
		tfcvv.setMinimumSize(new Dimension(400, 25));
		tfcvv.setPreferredSize(new Dimension(400, 25));
		tfcad = new JTextField();
		tfcad.setMaximumSize(new Dimension(400, 25));
		tfcad.setMinimumSize(new Dimension(400, 25));
		tfcad.setPreferredSize(new Dimension(400, 25));
		
		form.add(nombre);
		form.add(tfnombre);
		form.add(apell);
		form.add(tfapell);
		form.add(edad);
		form.add(tfedad);
		form.add(cuenta);
		form.add(tfcuenta);
		form.add(cvv);
		form.add(tfcvv);
		form.add(cad);
		form.add(tfcad);
		
		_panel.add(form);
		
	}
	
	private void bcomprar() {
		
		JButton comprar = new JButton("Comprar");
		comprar.addActionListener(new ComprarButton());
		_panel.add(comprar);
		
	}
	
	class ComprarButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			boolean ret = true;
			/*
			ret = !tfnombre.getText().equals("");
			ret = !tfapell.getText().equals("");
			ret = !(Integer.valueOf(tfedad.getText()) >= 12);
			ret = !(Integer.valueOf(tfedad.getText()) <= 100);
			ret = !(Long.parseUnsignedLong(tfcuenta.getText()) / 1E15 < 10);
			ret = !(Long.parseUnsignedLong(tfcuenta.getText()) / 1E15 > 1);
			ret = !(Integer.valueOf(tfcvv.getText()) / 100 < 10);
			ret = !(Integer.valueOf(tfcvv.getText()) / 100 > 1);
			*/
			if(ret)	ret = _tiendaCont.comprarJuego(_juego);

			/*
			if(Integer.valueOf(tfedad.getText()) >= 12 || Integer.valueOf(tfedad.getText()) <= 100) System.out.println("edad");
			if(Long.parseUnsignedLong(tfcuenta.getText()) / 1E15 < 10 || Long.parseUnsignedLong(tfcuenta.getText()) / 1E15 > 1) System.out.println("cuenta");
			if(Integer.valueOf(tfcvv.getText()) / 100 < 10 || Integer.valueOf(tfcvv.getText()) / 100 > 1) System.out.println("cvv");
			*/
			
			if (ret) {
				
				JFrame frame = new JFrame("Juego comprado");
				frame.setLayout(new BorderLayout());
				frame.setMinimumSize(new Dimension(800,200));
				frame.setPreferredSize(new Dimension(800,200));
				
				JLabel label = new JLabel("Se ha comprado: " + _juego.get_title() + " por: " + _juego.get_price()/100 + "$", SwingConstants.CENTER);
				
				frame.add(label, BorderLayout.CENTER);
				frame.setVisible(true);
				
				_tiendaCont.evento(EventoTienda.comprarJuego, _juego, _tiendaCont.getCurrentUser());
				
			} else {
				
				JFrame frame = new JFrame("ERROR");
				frame.setLayout(new BorderLayout());
				frame.setMinimumSize(new Dimension(800,200));
				frame.setPreferredSize(new Dimension(800,200));
				
				JLabel label = new JLabel("No se ha comprado: " + _juego.get_title(), SwingConstants.CENTER);
				
				frame.add(label, BorderLayout.CENTER);
				frame.setVisible(true);
				
			}
			
		}
	}

}
