package valoraciones;

import javax.swing.JPanel;

import common.Controller;

//comentario
public class ValoracionesControllerFacade {
	private Controller controller;
	
	public ValoracionesControllerFacade(Controller controller) {
		
	}
	
	public float getMedia() {
		return 2.5f;
	}
	
	public JPanel getPanelListValoraciones() {
		return new JPanel();
	}
	
	public void setPrincipalPanel(JPanel panel) {
		this.controller.setPrincipalPanel(panel);
	}
}
