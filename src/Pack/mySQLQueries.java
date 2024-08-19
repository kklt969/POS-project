/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pack;

import Pack.DBConnection;
import java.sql.*;
import javax.swing.JOptionPane;

public class mySQLQueries {
     static Connection con = null;
     static Statement ste ;
     static String query,query1;
    ResultSet rs;
    DBConnection db = new DBConnection();
    
    
    
    public static void main(String[] args) throws SQLException,ClassNotFoundException
    {
        mySQLQueries msql = new mySQLQueries();
        
        //msql.getidForChoice("brand");
        
    }
    
    public  mySQLQueries()
    {
        try
        {
          con = db.GetMySQLConnection();
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
    
    
    public String autoid(String field,String Table, String prefix)
    {
      return db.getPrimaryKey(field, Table, prefix);
    }
    
    public static boolean insertData(String tbName,String[] data)
    {
        if(tbName.equals("brand"))
        {
            query = "Insert into pos_project.brand(brandID,brandName) values ('"+data[0]+"','"+data[1]+"')" ;
        }
        
        else if(tbName.equals("merchandise"))
        {
            query = "Insert into pos_project.merchandise(merID,brandID,typeID) values ('"+data[0]+"','"+data[1]+"','"+data[2]+"')" ;
        }
        
        else if(tbName.equals("supplier"))
        {
            query = "Insert into pos_project.supplier(supplierID,Name,Address,PhoneNo,Email) values ('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"','"+data[4]+"')" ;
        }
        
        else if(tbName.equals("purchasedetail"))
        {
            query = "Insert into pos_project.purchasedetail(purchaseID,purchasePrice,purchaseQuantity,itemID) values ('"+data[0]+"',"+Long.parseLong(data[1])+","+Integer.parseInt(data[2])+",'"+data[3]+"')" ;
        }
        
        else if(tbName.equals("purchase"))
        {
            int cat = data[2].indexOf("(");
            query = "Insert into pos_project.purchase(purchaseID,supplierID,purchaseDate) values ('"+data[0]+"','"+data[1]+"','"+data[2].substring(0, cat)+"')" ;
            
        }
        
        else  if(tbName.equals("type"))
        {
            query = "Insert into pos_project.type(typeID,typeName) values ('"+data[0]+"','"+data[1]+"')" ;
        }
        
        else if(tbName.equals("itemdetail"))
        {
            query = "Insert into pos_project.itemdetail(itemid,merid,itemname,cursaleprice,remark,totalqty) values ('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"','"+data[4]+"','"+data[5]+"')" ;
        }
        
        
        try
        {
            
            ste = con.createStatement();
            int result = ste.executeUpdate(query);
            if(result == 1)
            {
                return true;
            }
            else
            {
                return false;
            }
        }catch(SQLException sqle)
        {
            //JOptionPane.showMessageDialog(null, sqle.getMessage());
            sqle.printStackTrace();
            System.out.print(sqle);
            return false;
        }
        
        
    }
    
    public boolean isduplicate(String tbName,String[]data)
    {
        if(tbName.equals("brand"))
        {
            query = "select * from pos_project.brand where brandName='"+data[0]+"'"; 
        }
        
        else if(tbName.equals("supplier"))
        {
            query = "select * from pos_project.supplier where Name='"+data[0]+"' AND Address='"+data[1]+"'and PhoneNo='"+data[2]+"'and Email='"+data[3]+"'"; 
        }
        
        else if(tbName.equals("merchandise"))
        {
            query = "select * from pos_project.merchandise where brandid='"+data[0]+"' AND typeid='"+data[1]+"'";
        }
        else if(tbName.equals("type"))
        {
            query = "select * from pos_project.type where TypeName='"+data[0]+"'"; 
        }
        
        else if(tbName.equals("itemdetail"))
        {
            query = "select * from pos_project.itemdetail where merid='"+data[0]+"' AND itemname = '"+data[1]+"'"; 
        }
        
        
        System.out.print(query);
        
        try
        {
            ste = con.createStatement();
            rs = ste.executeQuery(query);
            
            if(rs.next())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(null, sqle.getMessage());
            sqle.printStackTrace();
            System.out.print(sqle);
            return false;
        }
        
        
    }
    
    
    public boolean updateRecord(String tblName,String id, String[] data)
    {
        if(tblName.equals("supplier"))
        {
            query = "update pos_project.supplier set Name='"+data[0]+"', Address='"+data[1]+"', PhoneNo='"+data[2]+"',Email= '"+data[3]+"'where supplierID='"+id+"'";
            
        }
        
        else if(tblName.equals("itemdetail"))
        {
            query = "update pos_project.itemdetail set itemname='"+data[0]+"', cursaleprice='"+data[1]+"', remark='"+"'where itemid='"+id+"'";
            
        }
        
         try
        {
            ste = con.createStatement();
            int result = ste.executeUpdate(query);
            if(result == 1)
            {
                return true;
            }
            else
            {
                return false;
            }
        }catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(null, sqle.getMessage());
            sqle.printStackTrace();
            System.out.print(sqle);
            return false;
        }
    }
    
    
    
    public void P_updateitemquantity(String tbname,String id,String nprice,String data)
    {
        int r1= 0, price = 0;
        
        mySQLQueries msql = new mySQLQueries();
        String q[] = new String[6];
        q = msql.getItemData(id);
        
        System.out.println("Save QTy = "+data);
        System.out.println("Save curQuantity = "+q[5]);
        
        if(tbname.equals("purchase"))
        {
            r1 = Integer.parseInt(q[5]) + Integer.parseInt(data);
            price = Integer.parseInt(nprice)+(int)(Integer.parseInt(nprice)*0.1);
            System.out.println(price);
        }
        
        query1 = "update pos_project.itemdetail set curSalePrice="+price+",totalQty="+r1+" where itemID='"+id+"'";
        
          try
        {
            ste = con.createStatement();
            int result = ste.executeUpdate(query1);
            if(result == 1)
            {
                JOptionPane.showMessageDialog(null, "One record is sucessfully updated.");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Update not successful");
            }
        }catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(null, sqle.getMessage());
            sqle.printStackTrace();
            System.out.print(sqle);
            
        }
    }

    public String[] getItemData(String id) {
       try{
        ste = con.createStatement();
        
        query = "Select * from pos_project.itemdetail where itemid = '"+id+"'";
        rs = ste.executeQuery(query);
        String str [] = new String [6];
        while(rs.next())
         {
             str[0] = rs.getString(1);
             str[1] = rs.getString(2);
             str[2] = rs.getString(3);
             str[3] = rs.getString(4);
             str[4] = rs.getString(5);
             str[5] = rs.getString(6);
             
                }
        
        return str;
       }
        catch(SQLException sqle)
        {
            //JOptionPane.showMessageDialog(null, sqle.getMessage());
            sqle.printStackTrace();
            System.out.print(sqle);
            return null;
        }
        
       
        
    }
    
    public void deleteRecord(String tblName, String id)
    {
        int returnvalue =0 ;
        String query ="";
        if(tblName.equals("supplier"))
        {
            query = "delete from pos_project.supplier where supplierID='"+id+"'";
        }
        try
        {
            ste = con.createStatement();
            if(!query.equals("") && ste.executeUpdate(query)==1)
            {
                JOptionPane.showMessageDialog(null, "The record is deleted sucessfully in"+tblName+" table.");
            }
            else 
            {
                JOptionPane.showMessageDialog(null, "The id does not found in the table.");
            }
        }
        catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(null, sqle.getMessage());
            sqle.printStackTrace();
            System.out.print(sqle);
            
        }
        
    }
    
    public String getTypeName(String typeid)
    {
        try
        {
            String typename ="";
            ste = con.createStatement();
            query = "Select * from pos_project.type where typeID = '"+typeid+"'";
            
            rs = ste.executeQuery(query);
            
            while(rs.next())
            {
            typename = rs.getString(2);
            }
            return typename;
            
        }
        catch(SQLException sqle)
        {
            //JOptionPane.showMessageDialog(null, sqle.getMessage());
            sqle.printStackTrace();
            System.out.print(sqle); return null;
            
        }
        
    }
    
    
     public String getTypeID(String typename)
    {
        try
        {
            String typeID;
            ste = con.createStatement();
            query = " Select * from pos_project.type where typename = '"+typename+"';";
            
            rs = ste.executeQuery(query);
            rs.next();
            typeID = rs.getString(1);
            return typename;
            
            
        }
        catch(SQLException sqle)
        {
            //JOptionPane.showMessageDialog(null, sqle.getMessage());
            sqle.printStackTrace();
            System.out.print(sqle); return null;
            
        }
        
    }
    
    
     public String getBrandID(String brandname)
    {
        try
        {
            String brandid = "";
            ste = con.createStatement();
            query = " Select brandID from pos_project.brand where brandName = '"+brandname+"';";
            
            rs = ste.executeQuery(query);
            while(rs.next())
            {
            brandid = rs.getString(1);
            }
            return brandid;
            
        }
        catch(SQLException sqle)
        {
            //JOptionPane.showMessageDialog(null, sqle.getMessage());
            sqle.printStackTrace();
            System.out.print(sqle); return null;
            
        }
        
    }
    
    
     public String getBrandName(String brandid)
    {
        try
        {
            String brandname = "";
            ste = con.createStatement();
            query = " Select * from pos_project.Brand where brandID= '"+brandid+"';";
            
            rs = ste.executeQuery(query);
            while(rs.next())
            {
            brandname = rs.getString(2);
            
            }
            return brandname;
            
        }
        catch(SQLException sqle)
        {
            //JOptionPane.showMessageDialog(null, sqle.getMessage());
            sqle.printStackTrace();
            System.out.print(sqle); return null;
            
        }
        
    }
     
     public String[] getMerID(String brandid,String typeid)
     {
         int count = 0;
         try
         {
             if(brandid!=null && typeid!=null)
             {
                 query = "Select merid from pos_project.merchandise where brandID = '"+brandid+"' and typeid='"+typeid+"'";
                 
             }
             
             else if(brandid!=null)
             {
                  query = "Select merid from pos_project.merchandise where brandID = '"+brandid+"'";
             }
             
             else if(typeid!=null)
             {
                 query = "Select merid from pos_project.merchandise where typeID = '"+typeid+"'";
             }
             
             ste = con.createStatement();
             
             rs = ste.executeQuery(query);
             
             while(rs.next())
             {
                 count++;
             }
             
             if(count == 0)
             {
                 JOptionPane.showMessageDialog(null, "No item FOUND!");
             }
             
             String [] merID = new String[count];
             rs = ste.executeQuery(query);
             
             while(rs.next())
             {
                for(int i = 0; i<count;i++)
                {
                    merID[i] = rs.getString(1);
                    rs.next();
                }
             }
                return merID;
         }
         
         catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(null, sqle.getMessage());
            sqle.printStackTrace();
            System.out.print(sqle); return null;
            
        }
         
         
         
     }
    
    public String[] getMerchandiseData(String merid)
    {
        try{
        String[] value = new String[2];
        
        ste = con.createStatement();
        
        query = "Select * from pos_project.merchandise where merID='"+merid+"'";
        
        rs = ste.executeQuery(query);
        
        while (rs.next()){
        value[0] = rs.getString(2);
        value[1] = rs.getString(3);}
        return value;
        }
        catch(SQLException sqle)
        {
           
            sqle.printStackTrace();
            System.out.print(sqle); return null;
            
        }
    }
    
    public String[] getidForChoice(String tblname)
    {
        try
        {
            int count = 0;
            ste = con.createStatement();
            query = "Select * from  pos_project."+tblname;
            
            rs = ste.executeQuery(query);
            
            while(rs.next())
            {
                count++;
                
            }
            
             if(count == 0)
             {
                 JOptionPane.showMessageDialog(null, "No item FOUND!");
             }
           
             String[] st = new String[count];
            
            rs = ste.executeQuery(query);
            
            
            while(rs.next())
                    {
                       for(int i=0;i<count;i++)
                       {
                           st[i] = rs.getString(1);
                           rs.next();
                       }
                      
                    }
             return st;
        }
        catch(SQLException sqle)
        {
           
            sqle.printStackTrace();
            System.out.print(sqle); return null;
            
        }
        
        
        
    }
    
    
     public String[] getSupplierData(String supid)
    {
        try{
        String[] value = new String[4];
        
        ste = con.createStatement();
        
        query = "Select * from pos_project.supplier where supplierID='"+supid+"'";
        
        rs = ste.executeQuery(query);
        
        while (rs.next()){
        value[0] = rs.getString(2);
        value[1] = rs.getString(3);
        value[2] = rs.getString(4);
        value[3] = rs.getString(5);
        }
        return value;
        }
        catch(SQLException sqle)
        {
           
            sqle.printStackTrace();
            System.out.print(sqle); return null;
            
        }
    }
    
    public String[] getNameForChoice(String tbName)
    {
        try
        {
            
            if(tbName.equals("type"))
            {
                rs = db.SQLSelect("typeName", "type");
                
                
            }
            
            else if(tbName.equals("brand"))
            {
                 rs = db.SQLSelect("brandName", "brand");
            }
            int rowcount=0;
            while(rs.next())
            {
                rowcount++;
            }
            
            String[] temp = new String[rowcount];
            rs.beforeFirst();
            int i = 0;
            
             while(rs.next())
            {
                temp[i] = rs.getString(1); i++;
            }
             return temp;
            
        }
         catch(SQLException sqle)
        {
           
            sqle.printStackTrace();
            System.out.print(sqle); return null;
            
        }
        
        
        
       
        
    }
    
     public String[] getItemIDForSale(String tbName)
        {
            try
            {
                
                if(tbName.equals("itemdetail"))
                
                    ste = con.createStatement();
                    rs = ste.executeQuery("Select itemid from pos_project.itemdetail where totalqty = =0");
                    int rowcount=0;
            while(rs.next())
            {
                rowcount++;
            }
            
            String[] temp = new String[rowcount];
            rs.beforeFirst();
            int i = 0;
            
             while(rs.next())
            {
                temp[i] = rs.getString(1); i++;
            }
                
                
             return temp;    
                
            }
            
            
                catch(SQLException sqle)
            {

                sqle.printStackTrace();
                System.out.print(sqle); return null;

            }

            
            
        }
        
    
    
}
