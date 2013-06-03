/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.untdf.monedas.jtablemodelo;

import javax.swing.table.AbstractTableModel;

import ar.edu.untdf.monedas.MonedasApp;
import ar.edu.untdf.monedas.controllers.MonedaJpaController;
import ar.edu.untdf.monedas.modelos.Moneda;


/**
 *
 * @author Matias
 */
public class MonedaTableModel extends AbstractTableModel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MonedaJpaController productoC;
    Object[] Monedas;
    public MonedaTableModel(){ 
        super();
       productoC=MonedasApp.getMonedaC();
        this.Monedas= productoC.findMonedaEntities().toArray();
    }
    @Override
  public int getRowCount(){
      return productoC.getMonedaCount();
  };
  
    @Override
  public int getColumnCount(){
  return 4;
  };
    @Override
  public Object getValueAt(int row, int column){
    switch(column){
        case 0:return ((Moneda)Monedas[row]).getIdmoneda();
        case 1:return ((Moneda)Monedas[row]).getSiglas();
        case 2:return ((Moneda)Monedas[row]).getDescripcion();
        default:return null;
    }};
  public   String getColumnName(int columnIndex) {
    switch(columnIndex){
        case 0:return "Id";
        case 1:return "Siglas";
        case 2:return "Descripcion";


        default:return null;
    }}     

}
