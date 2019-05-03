package Tienda.View;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Juego.Control.JuegoDTO;

public class ComprarJuego extends JPanel{

	private static final long serialVersionUID = 1L;
	
	JuegoDTO _juego;
	JPanel _panel;
	
	public ComprarJuego(JuegoDTO juego) {
		
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
		//Arregla la mierda de que la scrollbar inicie a la derecha
		tArea.setSelectionStart(0);
		tArea.setSelectionEnd(0);
		
		JScrollPane sPane = new JScrollPane(tArea);

		gameInfo.add(sPane, BorderLayout.CENTER);
		
		_panel.add(gameInfo);
		
	}
	
	private void form() {
		
		JPanel form = new JPanel();
		form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
		
		JLabel nombre = new JLabel("Nombre:");
		JLabel apell = new JLabel("Apellidos:");
		JLabel edad = new JLabel("Edad:");
		JLabel cuenta = new JLabel("Cuenta");
		JLabel cvv = new JLabel("CVV");
		JLabel cad = new JLabel("Fecha caducidad:");
		
		JTextField tfnombre = new JTextField();
		JTextField tfapell = new JTextField();
		JTextField tfedad = new JTextField();
		JTextField tfcuenta = new JTextField();
		JTextField tfcvv = new JTextField();
		JTextField tfcad = new JTextField();
		
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
		_panel.add(comprar);
		
	}

}
