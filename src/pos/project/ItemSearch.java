/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */

package pos.project;

import java.sql.*;
import Pack.*;
import java.awt.print.PrinterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author CYBORG 15
 */
public class ItemSearch extends javax.swing.JDialog {
    
    mySQLQueries mysql = new mySQLQueries();
    DefaultTableModel dtm = new DefaultTableModel();
    DBConnection db = new DBConnection();
    Statement ste = null;
    Connection con = null;
    ResultSet rs = null;
    
    
    public ItemSearch() throws ClassNotFoundException, SQLException {
    
        initComponents();
        con = db.GetMySQLConnection();
        createtable();
        fillcbo();
    }
    
    public void createtable()
    {
        dtm.addColumn("Item ID");
        dtm.addColumn("Item Name | Brand | Type");
        dtm.addColumn("Price");
        dtm.addColumn("Quantity");
        dtm.addColumn("Remark");
        
        tblitemlist.setModel(dtm);
        
        setColumnWidth(0,30);
        setColumnWidth(1,260);
        setColumnWidth(2,20);
        setColumnWidth(3,10);
        setColumnWidth(4,50);
    }
    
    
    public void setColumnWidth(int index,int width)
    {
        DefaultTableColumnModel tcm = (DefaultTableColumnModel)tblitemlist.getColumnModel();
        TableColumn tc = tcm.getColumn(index);
        tc.setPreferredWidth(width);
        
    }
    
    
    
    
    public void fillall(String str)
    {
         
        String strdataitem[] = new String[5];
        String strquery[] = new String [2];
        
        try
        {
            ste = con.createStatement();
            //String str  = "select * from pos_project.itemdetail";
            
             rs = ste.executeQuery(str);
            
             while(dtm.getRowCount() > 0)
             {
                 dtm.removeRow(0);
             }
             
             
             
            while(rs.next())
            {
                strdataitem[0] = rs.getString(1);
                strquery = mysql.getMerchandiseData(rs.getString(2));
                 strdataitem[1] = rs.getString(3);
                 strdataitem[1] += " | "+ mysql.getBrandName(strquery[0]);
                  strdataitem[1] += " | "+ mysql.getTypeName(strquery[1]);
                 strdataitem[2] = rs.getString(4);
                 strdataitem[3] = rs.getString(6);
                 strdataitem[4] = rs.getString(5);
                
                 dtm.addRow(strdataitem);
            }
            tblitemlist.setModel(dtm);
        }
        
       catch (Exception exp)
        {
            exp.printStackTrace();
            System.out.print(exp);
        }
        
    
    }
    
    public void fillcbo()
    {
        try
        {
            cbobrand.addItem("----Select----");
            ste = con.createStatement();
            String sql = "Select brandName from pos_project.brand";   
            rs = ste.executeQuery(sql);
            while(rs.next())
            {
                cbobrand.addItem(rs.getString(1));
            }
            
            cbotype.addItem("----Select----");
            ste = con.createStatement();
            sql = "Select typeName from pos_project.type";   
            rs = ste.executeQuery(sql);
            while(rs.next())
            {
                cbotype.addItem(rs.getString(1));
            }
            
            
            
            
            
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
            System.out.print(exp);
        }
        
        
        
        
    }
    
    
    
      public void getQuery(String[] merid)
{
    if(merid.length !=0)
    {
        for(int i = 0; i < merid.length; i++)
        {
            fillall("Select * from pos_project.itemdetail where merid ='"+merid[i]+"'");
        }
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        rdobrand = new javax.swing.JRadioButton();
        rdotype = new javax.swing.JRadioButton();
        rdobrandtype = new javax.swing.JRadioButton();
        cbobrand = new javax.swing.JComboBox<>();
        cbotype = new javax.swing.JComboBox<>();
        search = new javax.swing.JButton();
        showall = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblitemlist = new javax.swing.JTable();
        print = new javax.swing.JButton();
        close = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Item Search :"));

        rdobrand.setText("Brand");
        rdobrand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdobrandActionPerformed(evt);
            }
        });

        rdotype.setText("Type");
        rdotype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdotypeActionPerformed(evt);
            }
        });

        rdobrandtype.setText("Brand & Type");
        rdobrandtype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdobrandtypeActionPerformed(evt);
            }
        });

        search.setText("Search");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        showall.setText("Show All");
        showall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showallActionPerformed(evt);
            }
        });

        tblitemlist.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblitemlist);

        print.setText("Print");
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });

        close.setText("Close");
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbobrand, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbotype, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(search)
                        .addGap(28, 28, 28)
                        .addComponent(showall))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rdobrand, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(rdotype, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addComponent(rdobrandtype, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(92, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(print)
                .addGap(18, 18, 18)
                .addComponent(close)
                .addGap(73, 73, 73))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdobrand)
                    .addComponent(rdotype)
                    .addComponent(rdobrandtype))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbobrand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbotype, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search)
                    .addComponent(showall))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(print)
                    .addComponent(close))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printActionPerformed
        try { 
            tblitemlist.print();
        } catch (PrinterException ex) {
            Logger.getLogger(ItemSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_printActionPerformed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        this.dispose();
    }//GEN-LAST:event_closeActionPerformed

    private void showallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showallActionPerformed
        
        
         
       fillall("Select * from pos_project.itemdetail");
        

        
    }//GEN-LAST:event_showallActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        if(rdobrand.isSelected())
        {
            if(cbobrand.getSelectedIndex() == 0)
            {
                JOptionPane.showMessageDialog(this,"Please select brand name");
                cbobrand.requestFocus();
            }
            
            else
            {
                String brandid = mysql.getBrandID(cbobrand.getSelectedItem().toString());
                
                String merid[]= mysql.getMerID(brandid,null);
                
                getQuery(merid);
                
            }
            
        }
        
        
        else if(rdotype.isSelected())
        {
            if(cbotype.getSelectedIndex() == 0)
            {
                JOptionPane.showMessageDialog(this,"Please select type name");
                cbobrand.requestFocus();
            }
            
            else
            {
                String typeid = mysql.getTypeID(cbotype.getSelectedItem().toString());
                
                String merid[]= mysql.getMerID(null,typeid);
                
                getQuery(merid);
                
            }
            
        }
        
        else if(rdobrandtype.isSelected())
        {
             if(cbobrand.getSelectedIndex() == 0)
            {
                JOptionPane.showMessageDialog(this,"Please select brand name");
                cbobrand.requestFocus();
            }
            
             else if(cbotype.getSelectedIndex() == 0)
            {
                JOptionPane.showMessageDialog(this,"Please select type name");
                cbobrand.requestFocus();
            }
            
            else
             {
                 String brandid = mysql.getBrandID(cbobrand.getSelectedItem().toString());
                 String typeid = mysql.getTypeID(cbotype.getSelectedItem().toString());
                 
                 String merid[]= mysql.getMerID(brandid,typeid);
                 
                  getQuery(merid);
                 
             }
            
            
            
            
            
            
        }
        
        
        
        
    }//GEN-LAST:event_searchActionPerformed

    private void rdobrandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdobrandActionPerformed
        
        if(rdobrand.isSelected())
        {
        rdotype.setEnabled(false);
        rdobrandtype.setEnabled(false);
        }
        else
        {
              rdotype.setEnabled(true);
        rdobrandtype.setEnabled(true);
        }
    }//GEN-LAST:event_rdobrandActionPerformed

    private void rdotypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdotypeActionPerformed
        if(rdotype.isSelected())
        {
        rdobrand.setEnabled(false);
        rdobrandtype.setEnabled(false);
        }
        else
        {
              rdobrand.setEnabled(true);
        rdobrandtype.setEnabled(true);
        }
    }//GEN-LAST:event_rdotypeActionPerformed

    private void rdobrandtypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdobrandtypeActionPerformed
        if(rdobrandtype.isSelected())
        {
        rdobrand.setEnabled(false);
        rdotype.setEnabled(false);
        }
        else
        {
              rdobrand.setEnabled(true);
        rdotype.setEnabled(true);
        }
    }//GEN-LAST:event_rdobrandtypeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ItemSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ItemSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ItemSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ItemSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ItemSearch dialog = null;
                try {
                    dialog = new ItemSearch();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ItemSearch.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ItemSearch.class.getName()).log(Level.SEVERE, null, ex);
                }
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbobrand;
    private javax.swing.JComboBox<String> cbotype;
    private javax.swing.JButton close;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton print;
    private javax.swing.JRadioButton rdobrand;
    private javax.swing.JRadioButton rdobrandtype;
    private javax.swing.JRadioButton rdotype;
    private javax.swing.JButton search;
    private javax.swing.JButton showall;
    private javax.swing.JTable tblitemlist;
    // End of variables declaration//GEN-END:variables

}
