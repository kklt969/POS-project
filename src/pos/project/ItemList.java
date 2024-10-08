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
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class ItemList extends javax.swing.JDialog {

   mySQLQueries mysql = new mySQLQueries();
    DefaultTableModel dtm = new DefaultTableModel();
    DBConnection db = new DBConnection();
    Statement ste = null;
    Connection con = null;
    public ItemList() {
      
        initComponents();
        
         try
        {
            con = db.GetMySQLConnection();
            createtable();
            fillSupplier();
            
            
            
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
    
    public void fillSupplier()
    {
        String strdataitem[] = new String[5];
        String strquery[] = new String [2];
        
        try
        {
            ste = con.createStatement();
            String str  = "select * from pos_project.itemdetail";
            
            ResultSet rs = ste.executeQuery(str);
            
            while(rs.next())
            {
                strdataitem[0] = rs.getString(1);
                strquery = mysql.getMerchandiseData(rs.getString(2));
                 strdataitem[1] = rs.getString(3);
                 strdataitem[1] += " | "+ mysql.getBrandName(strquery[0]);
                  strdataitem[1] += " | "+ mysql.getTypeName(strquery[1]);
                 strdataitem[2] = rs.getString(4);
                 strdataitem[3] = rs.getString(5);
                 strdataitem[4] = rs.getString(6);
                
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
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblitemlist = new javax.swing.JTable();
        Print = new javax.swing.JButton();
        Close = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        Print.setText("Print");
        Print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintActionPerformed(evt);
            }
        });

        Close.setText("Close");
        Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseActionPerformed(evt);
            }
        });

        jMenu1.setText("File");

        jMenuItem1.setText("jMenuItem1");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("jMenuItem1");
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("jMenuItem1");
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("jMenuItem1");
        jMenu1.add(jMenuItem4);

        jMenuItem5.setText("jMenuItem1");
        jMenu1.add(jMenuItem5);

        jMenuItem6.setText("jMenuItem1");
        jMenu1.add(jMenuItem6);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Print)
                .addGap(18, 18, 18)
                .addComponent(Close)
                .addGap(56, 56, 56))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Print)
                    .addComponent(Close))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintActionPerformed
       try {
           tblitemlist.print();
       } catch (PrinterException ex) {
           Logger.getLogger(ItemList.class.getName()).log(Level.SEVERE, null, ex);
       }

    }//GEN-LAST:event_PrintActionPerformed

    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_CloseActionPerformed

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
            java.util.logging.Logger.getLogger(ItemList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ItemList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ItemList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ItemList.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ItemList dialog = new ItemList();
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
    private javax.swing.JButton Close;
    private javax.swing.JButton Print;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblitemlist;
    // End of variables declaration//GEN-END:variables
}
