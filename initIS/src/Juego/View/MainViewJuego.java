package Juego.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import Juego.Control.JuegoDTO;
import common.MainController;

public class MainViewJuego extends JPanel{

	private static final long serialVersionUID = 1L;
	
	JuegoDTO _juegoDTO;
	JLabel _icon;
	JPanel _leftS;
	JPanel _center;
	JPanel _rightS;
	JPanel _valoraciones;
	JLabel _labelValoraciones;
	
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
		
		_valoraciones = new JPanel();
		_labelValoraciones = new JLabel("Valoraciones");
		_valoraciones.add(_labelValoraciones);
		
		this.add(_valoraciones);
				
		
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
		JButton comprar = new JButton(Double.toString(_juegoDTO.get_price() / 100.0) + " $ ");
		
		comprar.addActionListener(new ComprarButton());
		
		precioycomprar.setLayout(dos);
		precioycomprar.add(precio);
		precioycomprar.add(comprar);
		
        JTextArea descLong = new JTextArea(20, 25);
        descLong.setWrapStyleWord(true);
        descLong.setLineWrap(true);

        descLong.append(_juegoDTO.get_descLong());
        JScrollPane scroll = new JScrollPane(descLong, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
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
        
        JTextArea descShort = new JTextArea(15, 15);
        descShort.append(_juegoDTO.get_descShort());
        descShort.setWrapStyleWord(true);
        descShort.setLineWrap(true);
        JPanel mainPanel = new JPanel(new BorderLayout());
        JScrollPane vertical = new JScrollPane(descShort, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        vertical.setPreferredSize(new Dimension(200, 100));
        mainPanel.add(vertical);

        JLabel fecha = new JLabel(_juegoDTO.get_date());
        fecha.setHorizontalAlignment(JLabel.CENTER);
        fecha.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        
        JPanel generos = new JPanel();
        BoxLayout genL = new BoxLayout(generos, BoxLayout.Y_AXIS);
        generos.setLayout(genL);
        
        JLabel tituloGeneros = new JLabel("Generos");
        tituloGeneros.setFont(new Font("Calibri", Font.BOLD, 16));
        generos.add(tituloGeneros);
        
        JPanel etiquetas = new JPanel();
        for (String et : _juegoDTO.get_genres()) {
        	JLabel jl = new JLabel(et);
        	jl.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        	etiquetas.add(jl);
        }
        generos.add(etiquetas);
        generos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		der.setLayout(derecha);
		der.add(mainPanel);
		der.add(fecha);
		der.add(generos);
		
		_rightS = der;

	}
	
	class ComprarButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
			if (MainController.getCurrentUser().isUnregistered()) {
				
				JFrame frame = new JFrame("ERROR");
				frame.setLayout(new BorderLayout());
				frame.setMinimumSize(new Dimension(800,200));
				frame.setPreferredSize(new Dimension(800,200));
				
				JLabel label = new JLabel("Un usuario debe estar registrado para poder comprar un juego, porfavor, inicie sesión.", SwingConstants.CENTER);
				
				frame.add(label, BorderLayout.CENTER);
				frame.setVisible(true);
				
			}
			else
				firePropertyChange("ComprarJuego", null, _juegoDTO);
		}
	}
}
