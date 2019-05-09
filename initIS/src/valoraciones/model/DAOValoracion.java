package valoraciones.model;

import java.io.IOException;
import java.util.ArrayList;

import Juego.Control.JuegoDTO;

public class DAOValoracion {
	private Storage almacen;
	
	public DAOValoracion() {
		almacen = new Storage();
	}
	
	public void createValoracion(TransferValoracion valoracion) throws IOException {
		if(valoracion.getID()==0)
			valoracion.setID(almacen.getLastValoracion(valoracion.getGame()));
		
		almacen.storeValoracion(valoracion);
	}
	
	public void deleteValoracion(TransferValoracion valoracion) {
		almacen.releaseValoracion(valoracion);
	}
	
	public TransferValoracion getValoracion(int idValoracion,JuegoDTO game) throws IOException {
		return almacen.loadValoracion(game, idValoracion);
	}
	
	public void updateValoracion(TransferValoracion valoracion) throws IOException {
		almacen.storeValoracion(valoracion);
	}
	
	public ArrayList<TransferValoracion> getValoraciones(JuegoDTO juego) throws IOException {
		return almacen.loadValoraciones(juego);
	}
}
