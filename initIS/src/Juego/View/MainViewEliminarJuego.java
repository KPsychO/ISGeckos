package Juego.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import Juego.Control.EventoJuego;
import Juego.Control.JuegoControllerFacade;
import Juego.Control.JuegoDTO;

public class MainViewEliminarJuego extends JPanel {
	
	private JuegoDTO _dto;
	private JuegoControllerFacade _jc;
	
	private JTextArea _text;

	public MainViewEliminarJuego(JuegoDTO dto, JuegoControllerFacade jc) {
		
		_jc = jc;
		_dto = dto;
		
		initGUI();
		
		this.setVisible(true);
	}
	
	private void initGUI() {
		JPanel all = new JPanel();
		all.setLayout(new BoxLayout(all, BoxLayout.Y_AXIS));
		
		//Eliminar juego texto
		JPanel titulo = new JPanel();
		JLabel title = new JLabel("Eliminar " + _dto.get_title());
		title.setAlignmentX(LEFT_ALIGNMENT);
		title.setMinimumSize(new Dimension(600, 25));
		titulo.add(title);
		
		JPanel tieneTitulo = new JPanel();
		tieneTitulo.setPreferredSize(new Dimension(500, 25));
		tieneTitulo.setLayout(new BorderLayout());
		tieneTitulo.add(titulo, BorderLayout.WEST);
		tieneTitulo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		//Area de escribir
		JPanel area = new JPanel();
		_text = new JTextArea();
		CreateFocusListenerForFields(_text);
		_text.setPreferredSize(new Dimension(500, 300));
		_text.setText("Justificacion: ");
		_text.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		area.add(_text);
		
		//Aceptar y cancelar
		JPanel botones = new JPanel();
		
		JButton cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new CancelarButton());
		JButton aceptar = new JButton("Aceptar");
		aceptar.addActionListener(new AceptarButton());
		
		botones.add(cancelar);
		botones.add(aceptar);
		
		JPanel tieneBotones = new JPanel();
		tieneBotones.setLayout(new BorderLayout());
		tieneBotones.add(botones, BorderLayout.EAST);
		
		
		all.add(title);
		all.add(area);
		all.add(tieneBotones);
		
		this.add(all);
	}
	
	public void CreateFocusListenerForFields(JTextArea txt)
	{
	    txt.addFocusListener(new FocusListener() 
	    {
	        @Override
	        public void focusGained(FocusEvent e) {
	        	txt.setText("");
	        }

			@Override
			public void focusLost(FocusEvent e) {
				
			}
	    });
	}
	class CancelarButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
        	JOptionPane.showMessageDialog(null, "Se ha cancelado la eliminacion de: " + _dto.get_title(), 
        			"Cancelar", JOptionPane.INFORMATION_MESSAGE);
			
        	_jc.evento(EventoJuego.Desarrolladora, null);
				
		}
	}
	class AceptarButton implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
        	JOptionPane.showMessageDialog(null, "Se ha enviado la solicitud de la eliminacion de: " + _dto.get_title(), 
        			"Enviado", JOptionPane.INFORMATION_MESSAGE);

        	_jc.formularioEliminar(_dto, "Eliminar", _text.getText());
			_jc.evento(EventoJuego.EliminarJuego, _dto);
				
		}
	}
}
