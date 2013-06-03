package ar.edu.untdf.monedas.jtablemodelo;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

import ar.edu.untdf.monedas.modelos.Moneda;
import ar.edu.untdf.monedas.modelos.Exchange;


/**
 *
 * @author 
 */
public class ExchangeComboBoxModel extends DefaultComboBoxModel<Moneda> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExchangeComboBoxModel(Exchange ex){
     ArrayList<Moneda> a=ex.getMonedasSoportadas();
     for(Moneda m:a)this.addElement(m);
    };
}
