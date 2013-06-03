/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.untdf.monedas.jtablemodelo;

import javax.swing.table.AbstractTableModel;

import ar.edu.untdf.monedas.MonedasApp;
import ar.edu.untdf.monedas.controllers.UsuarioJpaController;
import ar.edu.untdf.monedas.modelos.Usuario;


/**
 *
 * @author Matias
 */
public class UsuarioTableModel extends AbstractTableModel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	UsuarioJpaController usuarioC;
    Object[] Usuarios;
    public UsuarioTableModel(){ 
        super();
       usuarioC=MonedasApp.getUsuarioC();
        this.Usuarios= usuarioC.findUsuarioEntities().toArray();
    }
    @Override
  public int getRowCount(){
      return usuarioC.getUsuarioCount();
  };
  
    @Override
  public int getColumnCount(){
  return 4;
  };
    @Override
  public Object getValueAt(int row, int column){
    switch(column){
        case 0:return ((Usuario)Usuarios[row]).getIdusuario();
        case 1:return ((Usuario)Usuarios[row]).getUsuario();
        case 2:return ((Usuario)Usuarios[row]).getPassword();
        default:return null;
    }};
    
  public   String getColumnName(int columnIndex) {
    switch(columnIndex){
        case 0:return "id";
        case 1:return "cliente";
        case 2:return "password";
        default:return null;
    }}     

}
