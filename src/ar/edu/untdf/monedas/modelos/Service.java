package ar.edu.untdf.monedas.modelos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 *
 * @author Martin
 */
public interface Service {
    ArrayList<Moneda> getMonedasSoportadas();
    void getChange(ArrayList<Moneda> moneyList);
}
