/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package pos.project;

import java.sql.*;
import Pack.*;
import java.awt.print.PrinterException;
import java.util.HashSet;
import java.util.Set;
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
public class PurchaseSearch extends javax.swing.JDialog {

     mySQLQueries mysql = new mySQLQueries();
    DefaultTableModel dtm = new DefaultTableModel();
    DBConnection db = new DBConnection();
    Statement ste = null;
    Connection con = null;
    ResultSet rs = null;
    
    
    
    
    public PurchaseSearch() {
        
        initComponents();
        
         try
        {
            con = db.GetMySQLConnection();
            createtable();
            fillcbo();
            
            ste = con.createStatement();
           
            
        }
       catch(ClassNotFoundException cnfx)
        {
            cnfx.printStackTrace();
            System.out.print(cnfx);
        }
        
        catch(SQLException sqle)
        {
            sqle.printStackTrace();
            System.out.print(sqle);
        }
        
        catch (Exception exp)
        {
            exp.printStackTrace();
            System.out.print(exp);
        }
    }
    
    
    public void createtable()
    {
        dtm.addColumn("Purchase ID");
        dtm.addColumn("Date");
        dtm.addColumn("Supplier Name");
        dtm.addColumn("Item Name | Brand | Type");
        dtm.addColumn("Price");
        dtm.addColumn("Quantity");
        
        tblpurchase.setModel(dtm);
        
        setColumnWidth(0,40);
        setColumnWidth(1,100);
        setColumnWidth(2,60);
        setColumnWidth(3,300);
        setColumnWidth(4,30);
        setColumnWidth(5,20);
    }
    
    public void setColumnWidth(int index,int width)
    {
        DefaultTableColumnModel tcm = (DefaultTableColumnModel)tblpurchase.getColumnModel();
        TableColumn tc = tcm.getColumn(index);
        tc.setPreferredWidth(width);
        
    }
    
    public void fillall(String str)
    {
        String strdataitem[] = new String[6];
        String strquery[] = new String [6];
        
        try
        {
             while(dtm.getRowCount() > 0)
             {
                 dtm.removeRow(0);
             }
            ste = con.createStatement();
            //String str  = "select * from pos_project.purchase,purchasedetail where purchase.purchaseID=purchasedetail.purchaseID ";
            
            ResultSet rs = ste.executeQuery(str);
            
            while(rs.next())
            {
                strdataitem[0] = rs.getString(1);
                strdataitem[1] = rs.getString(3);
                
                
                
                strquery = mysql.getSupplierData(rs.getString(2));
                  strdataitem[2] = strquery[0];
                  strquery = mysql.getItemData(rs.getString(7));
                  
                  strdataitem[3] = strquery[2];
                 strdataitem[4] = strquery[3];
                 strdataitem[5] = strquery[5];
                 
                 strquery = mysql.getMerchandiseData(strquery[1]);
                  
                  
                 strdataitem[3] += " | "+ mysql.getBrandName(strquery[0]);
                  strdataitem[3] += " | "+ mysql.getTypeName(strquery[1]);
                
                 
                
                 dtm.addRow(strdataitem);
            }
            tblpurchase.setModel(dtm);
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
            
            ste = con.createStatement();
            String sql = "Select purchaseDate from pos_project.purchase";   
            
            
            
            Set<String> checkDuplicateSetmonth = new HashSet<>();
            rs = ste.executeQuery(sql);

            while (rs.next()) { 
                String monthSubstring = rs.getString(1).substring(7, 10);

                if (!checkDuplicateSetmonth.contains(monthSubstring)) {
                    checkDuplicateSetmonth.add(monthSubstring);
                    cbomonth.addItem(monthSubstring);
                }
            }

            Set<String> checkDuplicateSetyear = new HashSet<>();
           rs = ste.executeQuery(sql);

            while (rs.next()) { 
                String yearSubstring = rs.getString(1).substring(0, 4);

                if (!checkDuplicateSetyear.contains(yearSubstring)) {
                    checkDuplicateSetyear.add(yearSubstring);
                    cboyear.addItem(yearSubstring);
                }
            }
           
            
            
            
            
            
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
            System.out.print(exp);
        }
        
        
        
        
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Close = new javax.swing.JPanel();
        rdoyear = new javax.swing.JRadioButton();
        rdomonth = new javax.swing.JRadioButton();
        rdomonthyear = new javax.swing.JRadioButton();
        cbomonth = new javax.swing.JComboBox<>();
        cboyear = new javax.swing.JComboBox<>();
        Search = new javax.swing.JButton();
        showall = new javax.swing.JButton();
        print = new javax.swing.JButton();
        close = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblpurchase = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Close.setBorder(javax.swing.BorderFactory.createTitledBorder("Purchase Search"));

        rdoyear.setText("Yearly");
        rdoyear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoyearActionPerformed(evt);
            }
        });

        rdomonth.setText("Monthly");
        rdomonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdomonthActionPerformed(evt);
            }
        });

        rdomonthyear.setText("Month & Year");
        rdomonthyear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdomonthyearActionPerformed(evt);
            }
        });

        cbomonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-----Select-----" }));

        cboyear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-----Select-----" }));

        Search.setText("Search");
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });

        showall.setText("Show all");
        showall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showallActionPerformed(evt);
            }
        });

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

        tblpurchase.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblpurchase);

        javax.swing.GroupLayout CloseLayout = new javax.swing.GroupLayout(Close);
        Close.setLayout(CloseLayout);
        CloseLayout.setHorizontalGroup(
            CloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CloseLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(print)
                .addGap(18, 18, 18)
                .addComponent(close)
                .addGap(44, 44, 44))
            .addGroup(CloseLayout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addGroup(CloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbomonth, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rdomonth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(CloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rdoyear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboyear, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(CloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rdomonthyear)
                    .addComponent(Search))
                .addGap(26, 26, 26)
                .addComponent(showall)
                .addContainerGap(259, Short.MAX_VALUE))
            .addGroup(CloseLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        CloseLayout.setVerticalGroup(
            CloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CloseLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(CloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoyear)
                    .addComponent(rdomonthyear)
                    .addComponent(rdomonth))
                .addGap(18, 18, 18)
                .addGroup(CloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbomonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboyear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Search)
                    .addComponent(showall))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(CloseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(print)
                    .addComponent(close))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Close, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Close, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdomonthyearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdomonthyearActionPerformed
       if(rdomonthyear.isSelected())
        {
        rdomonth.setEnabled(false);
        rdoyear.setEnabled(false);
        }
        else
        {
              rdomonth.setEnabled(true);
        rdoyear.setEnabled(true);
        }
    }//GEN-LAST:event_rdomonthyearActionPerformed

    private void printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printActionPerformed
        try {
            tblpurchase.print();
        } catch (PrinterException ex) {
            Logger.getLogger(PurchaseSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_printActionPerformed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
        this.dispose();
    }//GEN-LAST:event_closeActionPerformed

    private void rdomonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdomonthActionPerformed
        
        if(rdomonth.isSelected())
        {
        rdoyear.setEnabled(false);
        rdomonthyear.setEnabled(false);
        }
        else
        {
              rdoyear.setEnabled(true);
        rdomonthyear.setEnabled(true);
        }
    }//GEN-LAST:event_rdomonthActionPerformed

    private void rdoyearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoyearActionPerformed
       if(rdoyear.isSelected())
        {
        rdomonth.setEnabled(false);
        rdomonthyear.setEnabled(false);
        }
        else
        {
              rdomonth.setEnabled(true);
        rdomonthyear.setEnabled(true);
        }
    }//GEN-LAST:event_rdoyearActionPerformed

    private void showallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showallActionPerformed
        fillall("select * from pos_project.purchase,purchasedetail where purchase.purchaseID=purchasedetail.purchaseID ");
    }//GEN-LAST:event_showallActionPerformed

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        String month,year;
        
        if(rdomonth.isSelected())
        {
            if(cbomonth.getSelectedIndex() == 0)
            {
                JOptionPane.showMessageDialog(this, "Please Select month");
                
            }
            
            else
            {
                String str = " select * from pos_project.purchase,purchasedetail where purchase.purchaseID=purchasedetail.purchaseID and SUBSTRING(purchaseDate, 8, 3) = '"+cbomonth.getSelectedItem().toString()+"'";
                fillall(str);
            }
            
        }
        
        else if
                (rdoyear.isSelected())
        {
            if(cboyear.getSelectedIndex() == 0)
            {
                JOptionPane.showMessageDialog(this, "Please Select month");
                
            }
            
            else
            {
                String str = " select * from pos_project.purchase,purchasedetail where purchase.purchaseID=purchasedetail.purchaseID and SUBSTRING(purchaseDate, 8, 3) = '"+cboyear.getSelectedItem().toString()+"'";
                fillall(str);
            }
            
        }
        
         else if
                (rdomonthyear.isSelected())
        {
            if(cbomonth.getSelectedIndex() == 0)
            {
                JOptionPane.showMessageDialog(this, "Please Select month");
                
            }
            else if(cboyear.getSelectedIndex() == 0)
            {
                JOptionPane.showMessageDialog(this, "Please Select month");
                
            }
            
            else
            {
                String str = " select * from pos_project.purchase,purchasedetail where purchase.purchaseID=purchasedetail.purchaseID and SUBSTRING(purchaseDate, 8, 3) = '"+cbomonth.getSelectedItem().toString()+"' and SUBSTRING(purchaseDate, 1, 4) = '"+cboyear.getSelectedItem().toString()+"'";
                fillall(str);
            }
            
        }
        
    }//GEN-LAST:event_SearchActionPerformed

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
            java.util.logging.Logger.getLogger(PurchaseSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PurchaseSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PurchaseSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PurchaseSearch.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PurchaseSearch dialog = new PurchaseSearch();
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
    private javax.swing.JPanel Close;
    private javax.swing.JButton Search;
    private javax.swing.JComboBox<String> cbomonth;
    private javax.swing.JComboBox<String> cboyear;
    private javax.swing.JButton close;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton print;
    private javax.swing.JRadioButton rdomonth;
    private javax.swing.JRadioButton rdomonthyear;
    private javax.swing.JRadioButton rdoyear;
    private javax.swing.JButton showall;
    private javax.swing.JTable tblpurchase;
    // End of variables declaration//GEN-END:variables
}
