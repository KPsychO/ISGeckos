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

import org.json.JSONArray;

import Formulario.Control.ControllerFormulario;
import Formulario.Control.FormularioDAOJSON;
import Formulario.Control.FormularioDTO;
import Usuario.Control.UsuarioDTO;

//En la pestaña publicación, se aceptarán o rechazarán los formularios enviados por un administrador
public class MainViewPublicacion extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private int n = 0;
	private FormularioDTO _formularioDTO;
	private JPanel _panel;

    //NO ESTOY SEGURO
    private JPanel titulopanel;
    private JLabel titulo;
    private JPanel preciopanel;
    private JLabel precio;
    private JPanel pegipanel;
    private JLabel pegi;
    private JPanel shortpanel;
    private JLabel shortdes;
    private JPanel longpanel;
    private JLabel longdes;
    private JPanel numpanel;
    private JLabel num;
    private JPanel devpanel;
    private JLabel devname;
    
    private ControllerFormulario _cf;


	public MainViewPublicacion(UsuarioDTO dev, ControllerFormulario cf) {
		_cf = cf;
		_formularioDTO = new FormularioDTO(dev);
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
		
		form = new FormularioDAOJSON().getFormularies();
		
		FormularioDTO formulary = new FormularioDTO(form.getJSONObject(n));

		this.titulo.setText("TITULO :  " + formulary.get_title());
		
		this.precio.setText("PRECIO :  " + formulary.get_price());
		
		this.pegi.setText("PEGI :  " + formulary.get_pegi());
		
		this.shortdes.setText("DESCRIPCION :  " + formulary.get_descShort());
		
		this.longdes.setText("DETALLES :  " + formulary.get_descLong());
		
		this.num.setText("NUMERO :  " + n);
		
		this.devname.setText("DEVELOPER :  " + new UsuarioDTO(formulary.get_developer()).get_username());
		
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
        
        this.numpanel = new JPanel();
        this.num = new JLabel();
        
        num.setPreferredSize(new Dimension(500,20));
        num.setText("NUMERO :  " );
        
        this.devpanel = new JPanel();
        this.devname = new JLabel();
        
        devname.setPreferredSize(new Dimension(500,20));
        devname.setText("NUMERO :  " );
        
        titulopanel.add(titulo);
        preciopanel.add(precio);
        pegipanel.add(pegi);
        shortpanel.add(shortdes);
        longpanel.add(longdes);
        numpanel.add(num);
        devpanel.add(devname);
        
        campos.add(titulopanel);
        campos.add(preciopanel);
        campos.add(pegipanel);
        campos.add(shortpanel);
        campos.add(longpanel);
        campos.add(numpanel);
        campos.add(devpanel);
        
        campos.add(Box.createVerticalStrut(20));
        this.add(campos);
        
        showFormulary(n);
		
	}
	
	private void createBottom() {
		JPanel inferior = new JPanel();
		JButton next = new JButton(">>>");
		JButton back = new JButton("<<<");
		JButton delete = new JButton("DELETE");
		JButton accept = new JButton ("ACCEPT");
		

		next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JSONArray form = new JSONArray();
				form = new FormularioDAOJSON().getFormularies();
				if(form.length() == 0) {
					JOptionPane.showMessageDialog(null, "No hay mas formulariuos", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(n < form.length() - 1){
					n++;
					showFormulary(n);
				}
				else if (n == form.length() - 1){
					n = 0;
					showFormulary(n);
				}
				
			}
	           

	        });
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JSONArray form = new JSONArray();
				form = new FormularioDAOJSON().getFormularies();
				if(form.length() == 0) {
					JOptionPane.showMessageDialog(null, "No hay mmas formulariuos", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(n > 0){
					n--;
					showFormulary(n);
				}
				else if (n == 0){
					n = form.length() - 1;
					showFormulary(n);
				}	
			}

	        });
			
		delete.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent arg0) {
				_formularioDTO.deleteFormulary(n);
				
				JSONArray form = new JSONArray();
				form = new FormularioDAOJSON().getFormularies();
				
				if(form.length() == 0) {
					JOptionPane.showMessageDialog(null, "No hay mas formularios", "Error", JOptionPane.ERROR_MESSAGE);
					firePropertyChange("PerfilUsuarioCurrent", null, null);
				}
				else if(n < form.length() - 1){
					showFormulary(n);
				}
				else if (n >= form.length() - 1){
					n = 0;
					showFormulary(n);
				}
			}

	    });
			
		accept.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent arg0) {
				_formularioDTO.insertGame(n);
				_formularioDTO.deleteFormulary(n);
				
				JSONArray form = new JSONArray();
				form = new FormularioDAOJSON().getFormularies();
				
				if(form.length() == 0) {
					JOptionPane.showMessageDialog(null, "No hay mas formularios", "Error", JOptionPane.ERROR_MESSAGE);
					firePropertyChange("PerfilUsuarioCurrent", null, null);
				}
				else if(n < form.length() - 1){
					showFormulary(n);
				}
				else if (n >= form.length() - 1){
					n = 0;
					showFormulary(n);
				}
			}

	    });
		
		
		inferior.add(back);
		inferior.add(next);
		inferior.add(delete);
		inferior.add(accept);
		
		this.add(inferior, BorderLayout.SOUTH);
		
	}
	
	
	
	
}
