package Tienda.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Juego.Control.JuegoDTO;

public class JuegoTienda extends JPanel {

	private static final long serialVersionUID = 1L;
	
	JuegoDTO _juegoDTO;
	
	JLabel _icon;
	JPanel _contents;
	JPanel _desc;
	
	public JuegoTienda(JuegoDTO juego) {
		
		_juegoDTO = juego;
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
		
		_icon = new JLabel(new ImageIcon("./src/resources/game_icon.jpg"));
		
	}
	
	private void createContents() {
		
		_contents = new JPanel(new BorderLayout());
		JPanel auxPanel = new JPanel(new FlowLayout());
		
		JLabel pegi = new JLabel();
		pegi.setText(String.valueOf(_juegoDTO.getPegi()));
		
		JLabel price = new JLabel();
		price.setText(String.valueOf((float)_juegoDTO.getPrice()/100) + " $ ");
		
		auxPanel.add(new JLabel(" Pegi: "));
		auxPanel.add(pegi);
		
		auxPanel.add(new JLabel("| Price: "));
		auxPanel.add(price);
		
		JLabel title = new JLabel();
		title.setText(" Title: " + _juegoDTO.getTitle());
		
		_contents.add(title, BorderLayout.NORTH);
		_contents.add(auxPanel, BorderLayout.SOUTH);
		
	}
	
	private void createDesc() {
		
		_desc = new JPanel(new BorderLayout());
		_desc.setMaximumSize(new Dimension(200, 100));
		_desc.setMinimumSize(new Dimension(200, 100));
		_desc.setPreferredSize(new Dimension(200, 100));
		JTextArea tArea = new JTextArea();
		tArea.setText(_juegoDTO.getDesc());
		tArea.setEditable(false);
		
		JScrollPane sPane = new JScrollPane(tArea);
		_desc.add(sPane, BorderLayout.CENTER);
		
	}

}
