/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.untdf.monedas.jtablemodelo;

import java.util.List;

import javax.swing.DefaultComboBoxModel;


import ar.edu.untdf.monedas.controllers.MonedaJpaController;
import ar.edu.untdf.monedas.modelos.Moneda;


/**
 *
 * @author Martin
 */
public class MonedaComboBoxModel extends DefaultComboBoxModel<Moneda> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MonedaComboBoxModel(MonedaJpaController ex){
     List<Moneda> a=ex.findMonedaEntities();
     for(Moneda m:a)this.addElement(m);
    };
   
   
}
