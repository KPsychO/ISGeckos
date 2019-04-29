package Formulario.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import Formulario.Control.FormularioDTO;
import viewer.MainWindow;

public class ViewFormulario extends JPanel{
	
	private JTextField titleField;
	private JTextField descField;
	private JTextField priceField;
	private JTextField ageField;
	
	private JLabel errorlabel;
	
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
		
	}

	private void configPanel() {
		this.setLayout(new BorderLayout());
		_panel = new JPanel();
		_panel.setLayout(new BoxLayout(_panel, BoxLayout.Y_AXIS));
	}
	
	private void createFormulary() {
		JPanel campos = new JPanel();
        GridLayout experimentLayout = new GridLayout(8,2);
        campos.setLayout(experimentLayout);
        
        //TITULO
        
        JPanel titulopanel = new JPanel();
        JLabel titulo = new JLabel();
        
        titulo.setPreferredSize(new Dimension(125,20));
        titulo.setText("TITULO :  ");
        titleField = new JTextField();
        titleField.setPreferredSize(new Dimension(200,25));
        
        
        titulopanel.add(titulo);
        titulopanel.add(titleField);
        campos.add(titulopanel);
               
        //DESC	
        JPanel descpanel = new JPanel();
        JLabel desc = new JLabel();
        
        desc.setPreferredSize(new Dimension(125,20));
        desc.setText("DESCRIPCION :  ");
        
        descField = new JTextField();
        descField.setPreferredSize(new Dimension(200,25));
        
        descpanel.add(desc);
        descpanel.add(descField);
        campos.add(descpanel);
        
        //PRECIO
        JPanel preciocpanel = new JPanel();
        JLabel precio = new JLabel();
        
        precio.setPreferredSize(new Dimension(125,20));
        precio.setText("PRECIO:  ");
        
        priceField = new JTextField();
        priceField.setPreferredSize(new Dimension(200,25));
        
        preciocpanel.add(precio);
        preciocpanel.add(priceField);
        campos.add(preciocpanel);
        
        //EDAD
        JPanel edadPanel = new JPanel();
        JLabel edad = new JLabel();
        
        edad.setPreferredSize(new Dimension(125,20));
        edad.setText("EDAD :  ");
        
        ageField = new JTextField();
        ageField.setPreferredSize(new Dimension(200,25));
        
        edadPanel.add(edad);
        edadPanel.add(ageField);
        campos.add(edadPanel);
      

        
      //ERRORPANEL  
        
        JPanel errorpanel = new JPanel();
        errorlabel = new JLabel();
        errorlabel.setText("");
        errorlabel.setForeground(Color.red);
        errorlabel.setEnabled(false);
        errorlabel.setPreferredSize(new Dimension(300,20));
        errorpanel.add(errorlabel,BorderLayout.PAGE_END);
        campos.add(errorpanel);
   
        
        this.add(campos);
	}
	
	private void createBottom() {
		JPanel inferior = new JPanel();
		JButton confirm = new JButton("Aceptar");

		confirm.addActionListener(new ActionListener()
	        {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	//System.out.print(precioField.getText());
	            	
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
	            		exception.printStackTrace();
	            		tipoError = "Precio no valido";
	            	}
	            	
	            	try {
	            		aux  = Integer.parseInt(ageField.getText());
	            		if (aux < 0) {
	            			tipoError = "La edad debe tener un valor positivo";
	            		}
	            		
	            	} catch (NumberFormatException exception) {
	            		exception.printStackTrace();
	            		tipoError = "Edad no válida";
	            	}
	            	
	            
	                if(titleField.getText().equals("") 
		               || descField.getText().equals("") 
		               || priceField.getText().equals("")
		               || ageField.getText().equals("")) {
	                	showError("Hay un campo incorrecto o vacío",true);
		            }
	                else if(tipoError != "") {//hay error
	                	showError(tipoError,true);
	                }
	                else {//no hay error
	                	showError("Lo has enviado de puta madre socio",true);
	                	FormularioDTO formulario = new FormularioDTO(titleField.getText(), descField.getText(), Integer.parseInt(ageField.getText()), Integer.parseInt(priceField.getText()) );
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
}
