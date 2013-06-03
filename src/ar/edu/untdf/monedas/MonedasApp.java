/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.untdf.monedas;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ar.edu.untdf.monedas.controllers.MonedaJpaController;
import ar.edu.untdf.monedas.controllers.UsuarioJpaController;

/**
 *
 */
public class MonedasApp {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("CotizacionMonedasUsuario");
    private static UsuarioJpaController usuarioC=new UsuarioJpaController(emf);
    private static MonedaJpaController monedaC=new MonedaJpaController(emf);

        
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
	        //TypedQuery<Cliente> q=ClientesApp.getClienteC().getEntityManager().createQuery("select e from Cliente as e where e.apellido='gel'",Cliente.class);
	        //List<Cliente> l =q.getResultList();
	        //for(Cliente c:l) System.out.println(c.getNombre());
	       /* Cliente c= CatalogoApp.clienteC.findCliente(new Long(53));
	        System.out.println(c.getIdcliente());*/
	    }

   
}
