package Juego.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import Juego.Control.JuegoDTO;

public class MainViewJuego extends JPanel{
	
	JuegoDTO _juegoDTO;
	JLabel _icon;
	JPanel _leftS;
	JPanel _center;
	JPanel _rightS;
	
	public MainViewJuego(JuegoDTO dto) {
		
		_juegoDTO = dto;

		initGUI();
		this.setVisible(true);
		
	}
	

	private void initGUI() {
		
		leftSide();
		rightSide();
		
		BoxLayout layoutThis = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(layoutThis);
		JLabel nombre = new JLabel(_juegoDTO.get_title());
		nombre.setFont(new Font("Calibri", Font.PLAIN, 24));
		nombre.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		this.add(nombre);
		
		GridLayout grid = new GridLayout(1, 2);
		
		
		JPanel jp = new JPanel();
		
		jp.setLayout(grid);
		jp.add(_leftS);
		jp.add(_rightS);
		this.add(jp);
		
		this.setVisible(true);
		
	}

	private void leftSide() {
		
		JPanel izq = new JPanel();
		
		BoxLayout layout = new BoxLayout(izq, BoxLayout.Y_AXIS);
		JLabel icon = new JLabel(new ImageIcon("./src/resources/game_icon.jpg"));
		icon.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		icon.setAlignmentX(CENTER_ALIGNMENT);
		
		FlowLayout dos = new FlowLayout();
		JPanel precioycomprar = new JPanel();
		JLabel precio = new JLabel("Comprar");
		JButton comprar = new JButton(Double.toString(_juegoDTO.get_price() / 100.0) + " €");
		precioycomprar.setLayout(dos);
		precioycomprar.add(precio);
		precioycomprar.add(comprar);
		
        JTextArea texto = new JTextArea(20, 25);
        texto.setWrapStyleWord(true);
        texto.setLineWrap(true);

        texto.append(_juegoDTO.get_desc());
        JScrollPane scroll = new JScrollPane(texto, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        izq.setLayout(layout);
        izq.add(icon);
        izq.add(precioycomprar);
        
        JPanel f = new JPanel();
        GridLayout fl = new GridLayout(2, 1);
        f.setLayout(fl);
        f.add(izq);
        f.add(scroll);
        _leftS = f;
	}

	private void rightSide() {
		
		JPanel der = new JPanel();
		BoxLayout layoutDer = new BoxLayout(der, BoxLayout.Y_AXIS);
        der.setLayout(layoutDer);
        GridLayout derecha = new GridLayout(3, 1);
        
        JTextArea console = new JTextArea(15, 15);
        console.append(_juegoDTO.get_desc());
        console.setWrapStyleWord(true);
        console.setLineWrap(true);
        JPanel mainPanel = new JPanel(new BorderLayout());
        JScrollPane vertical = new JScrollPane(console, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        vertical.setPreferredSize(new Dimension(200, 100));
        mainPanel.add(vertical);

        Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-hh-dd hh:mm:ss");  
        String strDate = dateFormat.format(date);  
        JLabel fecha = new JLabel(strDate);
        fecha.setHorizontalAlignment(JLabel.CENTER);
        fecha.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        
        JLabel etiquetas = new JLabel("Etiquetas");
        etiquetas.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		der.setLayout(derecha);
		der.add(mainPanel);
		der.add(fecha);
		der.add(etiquetas);
		
		_rightS = der;

	}
}
