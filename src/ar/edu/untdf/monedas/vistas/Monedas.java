/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.untdf.monedas.vistas;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.persistence.Persistence;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import ar.edu.untdf.monedas.MonedasApp;
import ar.edu.untdf.monedas.controller.MonedaJpaController;
import ar.edu.untdf.monedas.controller.UsuarioJpaController;
import ar.edu.untdf.monedas.controller.exceptions.NonexistentEntityException;
import ar.edu.untdf.monedas.jtablemodelo.ExchangeComboBoxModel;
import ar.edu.untdf.monedas.jtablemodelo.MonedaTableListener;
import ar.edu.untdf.monedas.modelos.Exchange;
import ar.edu.untdf.monedas.modelos.Moneda;
import ar.edu.untdf.monedas.modelos.OpenModel;
import ar.edu.untdf.monedas.modelos.Usuario;
import ar.edu.untdf.monedas.modelos.VariablesGlobales;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 *
 * @author Matias
 */
public class Monedas extends javax.swing.JInternalFrame {

	private UsuarioJpaController usu = new UsuarioJpaController(Persistence.createEntityManagerFactory("CotizacionMonedasUsuario"));		
	private MonedaJpaController mon = new MonedaJpaController(Persistence.createEntityManagerFactory("CotizacionMonedasUsuario"));		

	private static final long serialVersionUID = 1L;
	private File imagen ;
	private JTable table;
	private Exchange ex;
	/**
     * Creates new form Monedas
     */
	
	  
    public Monedas(Exchange ex) {
        this.ex=ex;
		setForeground(Color.GREEN);
		setRootPaneCheckingEnabled(false);
		setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setSize(531,296);
		setTitle("Administracion de Monedas");
		setClosable(true);
        initComponents();
        MonedaTableListener listener = new MonedaTableListener(this);
        
        
    }

   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        final JComboBox comboBox = new JComboBox();
        comboBox.setModel(new ExchangeComboBoxModel(ex));
        comboBox.setMaximumRowCount(10);
        
        JButton button = new JButton();
        button.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		  ex.addMoneda((Moneda)comboBox.getSelectedItem());
        		  //listaMonedas.add((Moneda)comboBox.getSelectedItem());
        	      ex.updateMonedas();
        	      table.updateUI();        
        	}
        });
        button.setText("Agregar moneda");
        
        JScrollPane scrollPane = new JScrollPane();
        
        JButton button_1 = new JButton();
        button_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		ex.updateMonedas();  
                table.updateUI();
        	}
        });
        button_1.setText("Actualizar cotizaciones");
        
        JButton button_2 = new JButton();
        button_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
       
				if (fila > -1){
                
                    try {
          
              		  ex.removeMoneda((Moneda)comboBox.getSelectedItem());
              	      ex.updateMonedas();
              	      table.setModel(new OpenModel(ex)); 
        				/*mon.destroy(mon.findMonedaPorSiglas(siglas).getIdmoneda());
        				listaMonedas.remove((Moneda)comboBox.getSelectedItem());
        				table.setModel(new MonedaTableModel());*/

        			} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    
                    fila=-1;
                }
        	}
        });
        button_2.setText("Eliminar �ltima moneda");
        
        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent evt) {
                fila = table.rowAtPoint(evt.getPoint());                 

        	}
        });
        table.setModel(new OpenModel(ex));
        scrollPane.setColumnHeaderView(table);
        
        JButton btnGuardar = new JButton("Guardar monedas para usuario");
        btnGuardar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		Iterator iterador = ex.listIterator(); //Le solicito a la lista que me devuelva un iterador con todos los el elementos contenidos en ella
        		 
        		//Mientras que el iterador tenga un proximo elemento
        		while( iterador.hasNext() ) {
        		       Moneda b = (Moneda) iterador.next();
        		       //b.setUsuarios(usu.findUsuarioListByUser(VariablesGlobales.usuario));
        		       mon.create(b);
        		      
        		       System.out.println("moneda creada : " + b.getDescripcion());

        		}
        		
        		Usuario user ;
        		user = usu.findUsuario(VariablesGlobales.idusuario);
        		user.setMonedas(listaMonedas);
        		try {
					usu.edit(user);
     		       System.out.println("usuario " + user.getUsuario() + "editado");

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        });
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(10)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        					.addComponent(button, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
        				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(button_1)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(button_2)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnGuardar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(14)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(button))
        			.addGap(20)
        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
        			.addGap(11)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(button_1)
        				.addComponent(button_2)
        				.addComponent(btnGuardar)))
        );
        getContentPane().setLayout(groupLayout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    protected Icon ImageIcon(URL resource) {
		// TODO Auto-generated method stub
		return null;
	}

    private void aceptarActionPerformed(java.awt.event.ActionEvent evt) throws IOException {//GEN-FIRST:event_aceptarActionPerformed
        Moneda c=new Moneda();
        FileInputStream fis = new FileInputStream(imagen);
        BufferedImage image = ImageIO.read(fis);

     
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", b);

        byte[] jpgByteArray = b.toByteArray();
		
        /*c.setFoto(jpgByteArray);*/
        MonedasApp.getMonedaC().create(c);
     
        
        
    }//GEN-LAST:event_aceptarActionPerformed

    ArrayList listaMonedas = new ArrayList(); //Creo el ArrayList;
		
	int fila=-1;
	
	public JTable getTableMonedas() {
		// TODO Auto-generated method stub
		return table;
	}
}
