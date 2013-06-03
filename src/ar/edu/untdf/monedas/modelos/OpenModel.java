package ar.edu.untdf.monedas.modelos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author User
 */
public class OpenModel extends AbstractTableModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Exchange ex;

    public OpenModel(Exchange ex) {
        super();
        this.ex = ex;

    }

    public int getRowCount() {
        return ex.size();
    }

    public int getColumnCount() {
        return 2;
    }

    public String getColumnName(int columnIndex) {
        if (columnIndex == 0) {
            return "Moneda";
        } else {
            return "Cambio";
        }
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Moneda m = ex.getMoneyAt(rowIndex);
        if (columnIndex == 0) {
            return m.getSiglas();
        } else {
            return m.getCambio();
        }


    }
}

