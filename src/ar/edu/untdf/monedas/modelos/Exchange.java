package ar.edu.untdf.monedas.modelos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 */
public abstract class Exchange {

    ArrayList<Moneda> monedas = new ArrayList();

    abstract Service getService();

    public int size() {
        return monedas.size();
    }

    public ArrayList getMonedasSoportadas() {
        return getService().getMonedasSoportadas();
    }

    public void updateMonedas() {
        this.getService().getChange(monedas);
    }

    public void addMoneda(Moneda m) {
        monedas.add(m);
        this.updateMonedas();
    }

    public Moneda getMoneyAt(int index) {
        return monedas.get(index);
    }
    
    public void removeMoneda(Moneda m) {
        monedas.remove(m);
        this.updateMonedas();
    }

	public Iterator listIterator() {
		return monedas.iterator();
	}
}
