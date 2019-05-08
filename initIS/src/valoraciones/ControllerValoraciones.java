package valoraciones;

import javax.swing.JPanel;


public class ControllerValoraciones {
	private Controller controller;
	
	public ControllerValoraciones(Controller controller) {
		
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
