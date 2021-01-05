package logica;

import java.io.Serializable;

public class Protocolo<T> implements  Serializable  {
	private String tipo;
	private T cuerpo;


	public Protocolo(String tipo,T cuerpo) {
		
	
		this.tipo = tipo;
		this.cuerpo=cuerpo;
		
	}



	@Override
	public String toString() {
		return "Protocolo [tipo=" + tipo + "]";
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public T getCuerpo() {
		return cuerpo;
	}



	public void setCuerpo(T cuerpo) {
		this.cuerpo = cuerpo;
	}
	

}
