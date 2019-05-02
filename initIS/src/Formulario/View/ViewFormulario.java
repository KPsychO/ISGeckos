package Formulario.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;

import Formulario.Control.FormularioDTO;
import viewer.MainWindow;

public class ViewFormulario extends JPanel{
	
	private JLabel errorlabel;
	
	private JTextArea titleField;
	private JTextArea priceField;
	private JTextArea ageField;
	private JTextArea descLongField;
	private JTextArea descShortField;
	private JTextArea version;
	private JTextArea versionNotes;
	//Cajas
	private JCheckBox action;
    private JCheckBox adventure;
    private JCheckBox role_playing;
    private JCheckBox simulation;
    private JCheckBox strategy;
    private JCheckBox sports;
    private JCheckBox puzzle;
    private JCheckBox idle;
	
	//Faltan los generos y los logros
	
	FormularioDTO _formularioDTO;
	JPanel _panel;
	
	public ViewFormulario() {
		 _formularioDTO = new FormularioDTO();
		 initGUI();
		 
		 this.setVisible(true);
	}
	
	private void initGUI() {
		configPanel();
		createFormulary();
		createBottom();
		
		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	}

	private void configPanel() {
		this.setLayout(new BorderLayout());
		_panel = new JPanel();
		_panel.setLayout(new BoxLayout(_panel, BoxLayout.Y_AXIS));
	}
	
	private void createFormulary() {
		JPanel campos = new JPanel();
        BoxLayout experimentLayout = new BoxLayout(campos, BoxLayout.Y_AXIS);
        campos.setLayout(experimentLayout);
        int sizex = 300;
		
        //TITULO
        
        JPanel titulopanel = new JPanel();
        JLabel titulo = new JLabel();
        
        titulo.setPreferredSize(new Dimension(125,20));
        titulo.setText("TITULO :  ");
        
        titleField = new JTextArea();
        titleField.setPreferredSize(new Dimension(sizex,25));
        titleField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        
        titulopanel.add(titulo);
        titulopanel.add(titleField);

        //PRECIO
        JPanel preciocpanel = new JPanel();
        JLabel precio = new JLabel();
        
        precio.setPreferredSize(new Dimension(125,20));
        precio.setText("PRECIO:  ");
        
        priceField = new JTextArea();
        priceField.setPreferredSize(new Dimension(sizex,25));
        priceField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        
        preciocpanel.add(precio);
        preciocpanel.add(priceField);
        
        //EDAD
        JPanel edadPanel = new JPanel();
        JLabel edad = new JLabel();
        
        edad.setPreferredSize(new Dimension(125,20));
        edad.setText("EDAD :  ");
        
        ageField = new JTextArea();
        ageField.setPreferredSize(new Dimension(sizex,25));
        ageField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        
        edadPanel.add(edad);
        edadPanel.add(ageField);

        //DESC	SHORT
        JPanel descSPanel = new JPanel();
        JLabel descs = new JLabel();
        
        descs.setPreferredSize(new Dimension(125,20));
        descs.setText("DESC SHORT:  ");
        
        descShortField = new JTextArea();
        descShortField.setWrapStyleWord(true);
        descShortField.setLineWrap(true);
        descShortField.setPreferredSize(new Dimension(sizex,50));
        descShortField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        
        descSPanel.add(descs);
        descSPanel.add(descShortField);
        
        //DESC	LONG
        JPanel descLPanel = new JPanel();
        JLabel descl = new JLabel();
        
        descl.setPreferredSize(new Dimension(125,20));
        descl.setText("DESC LONG:  ");
        
        descLongField = new JTextArea();
        descLongField.setWrapStyleWord(true);
        descLongField.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(descLongField, 
        		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(sizex, 100));
        descLongField.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        
        descLPanel.add(descl);
        descLPanel.add(scroll);
        
        //GENEROS
        
        JPanel generos = new JPanel();
        
        BoxLayout generosLayout = new BoxLayout(generos, BoxLayout.Y_AXIS);
        //generos.setLayout(generosLayout);
        JLabel genres = new JLabel("GENEROS:  ");
        genres.setPreferredSize(new Dimension(125,20));
        
        JPanel cajas = new JPanel();
        GridLayout cajasL = new GridLayout(4, 2);
        cajas.setLayout(cajasL);
        cajas.setPreferredSize(new Dimension(sizex, 75));
        action = new JCheckBox("Action");
        adventure = new JCheckBox("Adventure");
        role_playing = new JCheckBox("Role playing");
        simulation = new JCheckBox("Simulation");
        strategy = new JCheckBox("Strategy");
        sports = new JCheckBox("Sports");
        puzzle = new JCheckBox("Puzzle");
        idle = new JCheckBox("Idle");
        
        
        cajas.add(action);
        cajas.add(adventure);
        cajas.add(role_playing);
        cajas.add(simulation);
        cajas.add(strategy);
        cajas.add(sports);
        cajas.add(puzzle);
        cajas.add(idle);
        
        generos.add(genres);
        generos.add(cajas);

      //ERRORPANEL  
        /*
        JPanel errorpanel = new JPanel();
        errorlabel = new JLabel();
        errorlabel.setText("");
        errorlabel.setForeground(Color.red);
        errorlabel.setEnabled(false);
        errorlabel.setPreferredSize(new Dimension(300,20));
        errorpanel.add(errorlabel,BorderLayout.PAGE_END);
        campos.add(errorpanel);
         */
        
        //Añadir todo
        campos.add(titulopanel);
        campos.add(preciocpanel);
        campos.add(edadPanel);
        campos.add(descSPanel);
        campos.add(descLPanel);
        campos.add(generos);
        
        JScrollPane full = new JScrollPane(campos, 
        		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        this.add(full);
        
	}
	
	private void createBottom() {
		JPanel inferior = new JPanel();
		JButton confirm = new JButton("Aceptar");

		confirm.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	
	            	int aux;
	            	double precio;
	            	boolean error = false;
	            	String tipoError = "";
	            	
	            	try {
	            		precio  = Double.parseDouble(priceField.getText());
	            		if (precio < 0) {
	            			tipoError = "El precio debe tener un valor positivo, o acabar en .0";
	            		}
	            		
	            	} catch (NumberFormatException exception) {
	            		tipoError = "Precio no valido";
	            	}
	            	
	            	try {
	            		aux  = Integer.parseInt(ageField.getText());
	            		if (aux < 0) {
	            			tipoError = "La edad debe tener un valor positivo";
	            		}
	            		
	            	} catch (NumberFormatException exception) {
	            		tipoError = "Edad no válida";
	            	}
	            	
	            
	                if(titleField.getText().equals("") 
		               || descShortField.getText().equals("") 
		               || priceField.getText().equals("")
		               || ageField.getText().equals("")
		               || descLongField.getText().equals("")) {
	                	tipoError = "Hay un campo incorrecto o vacío";
	                	JOptionPane.showMessageDialog(null, tipoError, "Error", JOptionPane.ERROR_MESSAGE);
		            }
	                else if(tipoError != "") { //hay error
	                	JOptionPane.showMessageDialog(null, tipoError, "Error", JOptionPane.ERROR_MESSAGE);
	                }
	                else { //no hay error
	                	tipoError = "Lo has enviado de puta madre socio";
	                	String ok = "Enviado correctamente";
	                	JOptionPane.showMessageDialog(null, tipoError, ok, JOptionPane.INFORMATION_MESSAGE);
	                	FormularioDTO formulario = getFormulario();
	                	_formularioDTO.insert(formulario);

	                }  
	            }

	        });
		
		inferior.add(confirm);
		this.add(inferior, BorderLayout.PAGE_END);
	}
		
		private void showError(String error,boolean enable) {
			errorlabel.setText(error);
			errorlabel.setEnabled(enable);
		}
		
		private FormularioDTO getFormulario() {
			FormularioDTO dto = new FormularioDTO();
			
			dto.set_title(titleField.getText());
			dto.set_desc(descShortField.getText());
			dto.set_pegi(Integer.parseInt(ageField.getText()));
			dto.set_price(Integer.parseInt(priceField.getText()));
			dto.set_descLong(descLongField.getText());
			dto.set_date(fecha());
			selectGenres(dto);
			
			
			return dto;
		}
		
		private void selectGenres(FormularioDTO dto) {
			
			if(action.isSelected()) { dto.addGenres("action"); }
			if(adventure.isSelected()) { dto.addGenres("adventure"); }
			if(role_playing.isSelected()) { dto.addGenres("role-playing"); }
			if(simulation.isSelected()) { dto.addGenres("simulation"); }
			if(strategy.isSelected()) { dto.addGenres("strategy"); }
			if(sports.isSelected()) { dto.addGenres("sports"); }
			if(puzzle.isSelected()) { dto.addGenres("puzzle"); }
			if(idle.isSelected()) { dto.addGenres("idle"); }
			
		}

		private String fecha() {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.now();
			return dtf.format(localDate); //2016/11/16
		}
}
