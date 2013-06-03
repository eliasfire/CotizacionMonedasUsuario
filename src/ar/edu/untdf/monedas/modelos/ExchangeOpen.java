package ar.edu.untdf.monedas.modelos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Martin
 */
public class ExchangeOpen extends Exchange{

    @Override
    Service getService() {
        return new ServiceOpen();
    }
    
}
