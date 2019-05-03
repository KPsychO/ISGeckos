package IncidenciasMejoras.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import IncidenciasMejoras.Control.IncidenciasDAOJSON;
import IncidenciasMejoras.Control.IncidenciasMejorasDTO;

public class MainViewIncidenciasJuego extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	
	private JButton aceptar;
	private JButton cancelar;
	private JTextArea comenText;
	private JTextArea descText;
	private IncidenciasDAOJSON imJSON;
	private String user;
	private String game;
	private JPanel _panel;
	
	public MainViewIncidenciasJuego(String usuario, String juego) {
		user = usuario;
		game = juego;
		imJSON = new IncidenciasDAOJSON();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		initGUI();
		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	}
	
	private void initGUI() {
		this.setLayout(new BorderLayout());
		_panel = new JPanel();
		_panel.setLayout(new BoxLayout(_panel, BoxLayout.Y_AXIS));
		
		JPanel campos = new JPanel();
        BoxLayout experimentLayout = new BoxLayout(campos, BoxLayout.Y_AXIS);
        campos.setLayout(experimentLayout);
        
		JLabel desc = new JLabel();
		desc.setText("Descripcion: ");
		descText = new JTextArea("Cual es el problema que tiene el juego?");
		descText.setWrapStyleWord(true);
	    descText.setLineWrap(true);
		descText.setPreferredSize(new Dimension(500, 200));
		JScrollPane descScroll = new JScrollPane(descText, 
        		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        descScroll.setPreferredSize(new Dimension(500, 50));

		JLabel comen = new JLabel();
		comen.setText("Comentario: ");
		comenText = new JTextArea("Comenta mas a fondo el motivo");
		comenText.setPreferredSize(new Dimension(500, 200));
		JScrollPane comenScroll = new JScrollPane(comenText, 
        		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        comenScroll.setPreferredSize(new Dimension(500, 50));
		aceptar = new JButton("Aceptar");
		aceptar.setActionCommand("aceptar");
		aceptar.addActionListener(this);
		cancelar = new JButton("Cancelar");
		cancelar.setActionCommand("cancelar");
		cancelar.addActionListener(this);
		
		campos.add(desc);
		campos.add(descScroll);
		campos.add(comen);
		campos.add(comenScroll);
		
		JPanel buttons;
		buttons = new JPanel();
		buttons.add(aceptar);
		buttons.add(cancelar);
		campos.add(buttons);
		
		JScrollPane all = new JScrollPane(campos, 
	        		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        all.getVerticalScrollBar().setUnitIncrement(15);
	    this.add(all);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("aceptar")) {
			imJSON.insertarIncidenciasMejoras(new IncidenciasMejorasDTO ("IncJue", user, null, game, descText.getText(), comenText.getText()));
			JOptionPane.showMessageDialog(getParent(), "Has enviado la incidencia del juego");
		}
		else if (e.getActionCommand().equals("cancelar")) {
			JOptionPane.showMessageDialog(getParent(), "Has cancelado la incidencia del juego");
		}
	}

}
