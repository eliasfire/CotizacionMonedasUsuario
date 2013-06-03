package ar.edu.untdf.monedas.modelos;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the moneda database table.
 * 
 */
@Entity
public class Moneda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idmoneda;

	private String cambio;

	private String descripcion;

	private String siglas;

	//bi-directional many-to-many association to Usuario
	@ManyToMany(mappedBy="monedas", cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<Usuario> usuarios;

	public Moneda() {
	}
	
	public Moneda(String nomb,String camb, String descripcion)
    {
        this.siglas=nomb;
        this.cambio=camb;
        this.descripcion=descripcion;
    }

	public int getIdmoneda() {
		return this.idmoneda;
	}

	public void setIdmoneda(int idmoneda) {
		this.idmoneda = idmoneda;
	}

	public String getCambio() {
		return this.cambio;
	}

	public void setCambio(String cambio) {
		this.cambio = cambio;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getSiglas() {
		return this.siglas;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	public String toString(){
		return siglas + " - " + descripcion;
	}

}