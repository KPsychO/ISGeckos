package valoraciones.model;

import java.io.IOException;
import java.util.ArrayList;

import Juego.Control.JuegoDTO;

public class FacadeMedia {
	
	public FacadeMedia() {}
	
	public float getMediaJuego(JuegoDTO juego) throws IOException {
		return calculateMedia(getValoracionesJuego(juego));
	}
	
	
	public float getMediaJuego(ArrayList<TransferValoracion> valoraciones) {
		return calculateMedia(valoraciones);
	}
	
	private float calculateMedia(ArrayList<TransferValoracion> valoraciones) {
		float sum = 0;
		int num = 0;
		
		for(TransferValoracion valoracion : valoraciones) {
			sum += valoracion.getPuntuacion();
			num ++;
		}
		if(num == 0)
			return 0;
		
		return (sum/(float)num);
	}
	
	private ArrayList<TransferValoracion> getValoracionesJuego(JuegoDTO juego) throws IOException{
		DAOValoracion daoValoracion = new DAOValoracion();
		return daoValoracion.getValoraciones(juego);
	}
}
