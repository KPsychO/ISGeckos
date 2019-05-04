package Formulario.View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;

import org.json.JSONArray;
import org.json.JSONObject;

import Formulario.Control.FormularioDTO;

//En la pestaña publicación, se aceptarán o rechazarán los formularios enviados por un administrador
public class MainViewPublicacion extends JPanel {
	
	int n = 0;
	FormularioDTO _formularioDTO;
	JPanel _panel;
	
	//NO ESTOY SEGURO
    JPanel titulopanel;
    JLabel titulo;
    JPanel preciopanel;
    JLabel precio;
    JPanel pegipanel;
    JLabel pegi;
    JPanel shortpanel;
    JLabel shortdes;
    JPanel longpanel;
    JLabel longdes;

	

	public MainViewPublicacion() {
		_formularioDTO = new FormularioDTO();
		initGUI();
		this.setVisible(true);
	}
		
	private void initGUI() {
		configPanel();
		initPanel();
		createBottom();
	}
	
	private void configPanel() {
		//this.setLayout(new BorderLayout());
		_panel = new JPanel();
		_panel.setLayout(new BoxLayout(_panel, BoxLayout.Y_AXIS));
	}
	
	private void showFormulary(int n) {
		
		JSONArray form = new JSONArray();
		
		form = _formularioDTO.getFormularies();
		
		FormularioDTO formulary = new FormularioDTO(form.getJSONObject(n));

		this.titulo.setText("TITULO :  " + formulary.get_title());
		
		this.precio.setText("PRECIO :  " + formulary.get_price());
		
		this.pegi.setText("PEGI :  " + formulary.get_pegi());
		
		this.shortdes.setText("DESCRIPCION :  " + formulary.get_descShort());
		
		this.longdes.setText("DETALLES :  " + formulary.get_descLong());
		
	}
	
	private void initPanel() {
		JPanel campos = new JPanel();
		
		
		BoxLayout experimentLayout = new BoxLayout(campos, BoxLayout.Y_AXIS);
        campos.setLayout(experimentLayout);
		
        this.titulopanel = new JPanel();
        this.titulo = new JLabel();
        
        titulo.setPreferredSize(new Dimension(500,20));
        titulo.setText("TITULO :  ");
        
        this.preciopanel = new JPanel();
        this.precio = new JLabel();
        
        precio.setPreferredSize(new Dimension(500,20));
        precio.setText("PRECIO :  ");
        
        this.pegipanel = new JPanel();
        this.pegi = new JLabel();
        
        pegi.setPreferredSize(new Dimension(500,20));
        pegi.setText("PEGI :  ");
        
        this.shortpanel = new JPanel();
        this.shortdes = new JLabel();
        
        shortdes.setPreferredSize(new Dimension(500,20));
        shortdes.setText("DESCRIPCION :  ");
        
        this.longpanel = new JPanel();
        this.longdes = new JLabel();
        
        longdes.setPreferredSize(new Dimension(500,20));
        longdes.setText("DETALLES :  " );
        
        titulopanel.add(titulo);
        preciopanel.add(precio);
        pegipanel.add(pegi);
        shortpanel.add(shortdes);
        longpanel.add(longdes);
        
        campos.add(titulopanel);
        campos.add(preciopanel);
        campos.add(pegipanel);
        campos.add(shortpanel);
        campos.add(longpanel);
        
        campos.add(Box.createVerticalStrut(20));
        this.add(campos);
		
	}
	
	private void createBottom() {
		JPanel inferior = new JPanel();
		JButton next = new JButton(">>>");
		JButton back = new JButton("<<<");
		

		next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JSONArray form = new JSONArray();
				form = _formularioDTO.getFormularies();
				if(form.length() == 0) {
					JOptionPane.showMessageDialog(null, "No hay más formulariuos", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(n < form.length() - 1){
					showFormulary(n);
					n++;
				}
				else if (n == form.length() - 1){
					showFormulary(n);
					n = 0;
				}
			}

	        });
		
			back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JSONArray form = new JSONArray();
				form = _formularioDTO.getFormularies();
				if(form.length() == 0) {
					JOptionPane.showMessageDialog(null, "No hay más formulariuos", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(n > 0){
					showFormulary(n);
					n--;
				}
				else if (n == 0){
					showFormulary(n);
					n = form.length() - 1;
				}
				
			}
	           

	        });
		
		
		inferior.add(back);
		inferior.add(next);
		
		this.add(inferior, BorderLayout.SOUTH);
		
	}
	
	
	
	
}
