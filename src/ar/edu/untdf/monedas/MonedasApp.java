/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.untdf.monedas;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ar.edu.untdf.monedas.controller.MonedaJpaController;
import ar.edu.untdf.monedas.controller.UsuarioJpaController;

/**
 *
 */
public class MonedasApp {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("CotizacionMonedasUsuario");
    private static UsuarioJpaController usuarioC=new UsuarioJpaController(emf);
    private static MonedaJpaController monedaC=new MonedaJpaController(emf);
    static EntityManager em = emf.createEntityManager();
    
        
    public static EntityManagerFactory getEmf() {
        return emf;
    }

    public static void setEmf(EntityManagerFactory emf) {
        MonedasApp.emf = emf;
    }

	public static UsuarioJpaController getUsuarioC() {
		return usuarioC;
	}

	public static void setUsuarioC(UsuarioJpaController usuarioC) {
		MonedasApp.usuarioC = usuarioC;
	}

	public static MonedaJpaController getMonedaC() {
		return monedaC;
	}

	public static void setMonedaC(MonedaJpaController monedaC) {
		MonedasApp.monedaC = monedaC;
	}

	
	 public static void main(String args[]) {
         em.getTransaction().begin();
	    }

   
}
