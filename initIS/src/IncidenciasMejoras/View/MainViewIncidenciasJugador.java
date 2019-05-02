package IncidenciasMejoras.View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import IncidenciasMejoras.Control.IncidenciasDAOJSON;
import IncidenciasMejoras.Control.IncidenciasMejorasDTO;

public class MainViewIncidenciasJugador extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	
	//extends 
	private JPanel descr;
	private JPanel coment;
	private JPanel buttons;
	private JButton aceptar;
	private JTextField comenText;
	private JTextField descText;
	private IncidenciasDAOJSON imJSON;
	private String user;
	
	public MainViewIncidenciasJugador(String usuario) {
		descr = new JPanel();
		coment = new JPanel();
		buttons = new JPanel();
		user = usuario;
		imJSON = new IncidenciasDAOJSON();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		initGUI();
	}
	
	private void initGUI() {
		JLabel desc = new JLabel();
		desc.setText("Descripcion: ");
		descText = new JTextField("Cual es el problema que tienes?");
		descText.setPreferredSize(new Dimension(500, 200));
		JLabel comen = new JLabel();
		comen.setText("Comentario: ");
		comenText = new JTextField("Comenta mas a fondo el motivo");
		comenText.setPreferredSize(new Dimension(500, 200));
		aceptar = new JButton("Aceptar");
		aceptar.setActionCommand("aceptar");
		aceptar.addActionListener(this);
		JButton cancelar = new JButton("Cancelar");
		cancelar.setActionCommand("cancelar");
		cancelar.addActionListener(this);
		descr.add(desc);
		descr.add(descText);
		coment.add(comen);
		coment.add(comenText);
		buttons.add(aceptar);
		buttons.add(cancelar);
		this.add(descr);
		this.add(coment);
		this.add(buttons);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("aceptar")) {
			imJSON.getListIncidencias();
			//Aqui faltan los usuarios y el denunciado
			imJSON.insertarIncidencia(new IncidenciasMejorasDTO ("IncJug", user, null, null, descText.getText(), comenText.getText()));
			JOptionPane.showMessageDialog(getParent(), "Has enviado la Denuncia/Incidencia");
		}
		else if (e.getActionCommand().equals("cancelar")) {
			
			JOptionPane.showMessageDialog(getParent(), "Has cancelado la Denuncia/Incidencia");
		}
	}

}
