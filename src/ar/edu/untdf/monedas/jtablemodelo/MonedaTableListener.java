/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.untdf.monedas.jtablemodelo;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ar.edu.untdf.monedas.vistas.Monedas;


/**
 *
 * @author Matias
 */
    public class MonedaTableListener implements ListSelectionListener {
    Monedas form;
    int rowSelected=0;

    // It is necessary to keep the table since it is not possible
    // to determine the table from the event's source
    public MonedaTableListener(Monedas form) {
        this.form = form;
    }
    public void valueChanged(ListSelectionEvent e) {
        
        
        // If cell selection is enabled, both row and column change events are fired
        if ( this.rowSelected!=form.getTableMonedas().getSelectedRow() && form.getTableMonedas().getSelectedRow()>0)
        { 
            System.out.println(form.getTableMonedas().getSelectedRow());
          this.rowSelected=form.getTableMonedas().getSelectedRow();
        }
 
        if (e.getValueIsAdjusting()) {
            // The mouse button has not yet been released
        }
    }
	
}

