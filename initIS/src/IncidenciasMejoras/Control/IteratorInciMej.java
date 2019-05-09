package IncidenciasMejoras.Control;

public class IteratorInciMej {
	private int n = 0;
	
	public int primero() {
		n = 0;
		return n; 
	}
	
	public void sumarUno() {
		n++;
	}
	
	public void restarUno() {
		n--;
	}
	
	public int getN() {
		return n;
	}
	
	public void setN(int n) {
		this.n = n;
	}
}
