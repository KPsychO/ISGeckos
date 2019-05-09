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

import Formulario.Control.FormularioControllerFacade;
import Formulario.Control.EventoFormulario;
import Formulario.Control.FormularioDAOJSON;
import Formulario.Control.FormularioDTO;
import Usuario.Control.UsuarioDTO;

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
    private JPanel typepanel;
    private JLabel type;
    
    private IteratorFormulario iter;
    
    private FormularioControllerFacade _cf;

	public MainViewPublicacion(UsuarioDTO dev, FormularioControllerFacade cf) {
		_cf = cf;
		_formularioDTO = new FormularioDTO(dev);
		initGUI();
		this.setVisible(true);
	}
		
	private void initGUI() {
		this.iter = new IteratorFormulario(0);
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
		
		this.type.setText("TIPO :  " + formulary.get_type());
		
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
        
        this.typepanel = new JPanel();
        this.type = new JLabel();
        
        type.setPreferredSize(new Dimension(500,20));
        type.setText("TIPO :  " );
        
        titulopanel.add(titulo);
        preciopanel.add(precio);
        pegipanel.add(pegi);
        shortpanel.add(shortdes);
        longpanel.add(longdes);
        numpanel.add(num);
        devpanel.add(devname);
        typepanel.add(type);
        
        campos.add(titulopanel);
        campos.add(preciopanel);
        campos.add(pegipanel);
        campos.add(shortpanel);
        campos.add(longpanel);
        campos.add(numpanel);
        campos.add(devpanel);
        campos.add(typepanel);
        
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
					_cf.evento(EventoFormulario.Perfil, null);
				}
				else if(iter.actual < form.length() - 1){
					
					showFormulary(iter.siguiente());
				}
				else if (iter.actual == form.length() - 1){
					n = 0;
					showFormulary(iter.primero());
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
					_cf.evento(EventoFormulario.Perfil, null);
				}
				else if(iter.actual > 0){
					
					showFormulary(iter.anterior());//ITERATOR
				}
				else if (iter.actual == 0){
					iter.setPos( form.length() - 1);
					showFormulary(iter.actual);
				}	
				
			}

	        });
			
		delete.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent arg0) {
				_formularioDTO.deleteFormulary(iter.actual);
				
				JSONArray form = new JSONArray();
				form = new FormularioDAOJSON().getFormularies();
				
				if(form.length() == 0) {
					JOptionPane.showMessageDialog(null, "No hay mas formularios", "Error", JOptionPane.ERROR_MESSAGE);
					_cf.evento(EventoFormulario.Perfil, null);
				}
				else if(iter.actual < form.length() - 1){
					showFormulary(iter.siguiente());
				}
				else if (iter.actual >= form.length() - 1){
					showFormulary(iter.primero());
				}
			}

	    });
			
		accept.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				JSONArray form = new JSONArray();
				form = new FormularioDAOJSON().getFormularies();
				FormularioDTO formulary = new FormularioDTO(form.getJSONObject(iter.actual));
				
				if (formulary.get_type().equals("Eliminar")) {
					_formularioDTO.deleteFormulary(iter.actual);
					_cf.eliminarJuego(formulary.get_id());
					form = new FormularioDAOJSON().getFormularies();
				} else {
					_formularioDTO.insertGame(iter.actual);
					_formularioDTO.deleteFormulary(iter.actual);
					
				}
				if(form.length() == 0) {
					JOptionPane.showMessageDialog(null, "No hay mas formularios", "Error", JOptionPane.ERROR_MESSAGE);
					//firePropertyChange("PerfilUsuarioCurrent", null, null);
					_cf.evento(EventoFormulario.Perfil, null);
				}
				else if(iter.actual < form.length() - 1){
					JOptionPane.showMessageDialog(null, "Juego enviado correctamente ", null, JOptionPane.ERROR_MESSAGE);
					showFormulary(iter.siguiente());
				}
				else if (iter.actual >= form.length() - 1){
					JOptionPane.showMessageDialog(null, "Juego enviado correctamente ", "null", JOptionPane.ERROR_MESSAGE);
					showFormulary(iter.primero());
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
