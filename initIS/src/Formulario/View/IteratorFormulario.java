package Formulario.View;

public class IteratorFormulario {
	int actual;
	
	public IteratorFormulario(int n) {
		actual = n;
	}
	
	public int siguiente() {
		actual++;
		return actual;
	}
	public int primero() {
		actual=0;
		return actual;
	}
	public int anterior() {
		actual--;
		return actual;
	}

	public void setPos(int n) {
		actual= n;
	}
	public int getPos() {
		return actual;
	}
	
}
