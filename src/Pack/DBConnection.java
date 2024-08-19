/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pack;


import java.sql.*;
import java.sql.Connection;
import java.util.*;

public class DBConnection {
    public  Connection con = null;
    String url;
    
    public Connection GetMySQLConnection() throws ClassNotFoundException,SQLException
    {
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pos_project?user=root&password=8834");
            System.out.print("Connection with mysql is successful");
            
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
        
        System.out.print(con);
        return con;
        
    }
    
    public Connection ConnectionStop()
    {
        
        return null;
    }
    
    public ResultSet SQLSelect(String field,String table)
    {
        ResultSet rs = null;
        
        try{ 
            Statement ste = GetMySQLConnection().createStatement();
            rs = ste.executeQuery("SELECT "+ field + " FROM "+ table);
        
            
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
       return rs; 
    }
    
    
    public boolean ExecuteSQL(String sql)
    {
        try
        {
            Statement ste = GetMySQLConnection().createStatement();
            ste.executeUpdate(sql);
            return true;
        }
        
         catch(ClassNotFoundException cnfx)
        {
            cnfx.printStackTrace();
            System.out.print(cnfx);
            return false;
        }
        
        catch(SQLException sqle)
        {
            sqle.printStackTrace();
            System.out.print(sqle); return false;
        }
        
        catch (Exception exp)
        {
            exp.printStackTrace();
            System.out.print(exp); return false;
        }
    }
    
    
    public String getPrimaryKey(String field,String table,String prefix)
    {
        
        ResultSet rs = SQLSelect(field,table);
        int current;
        
        try
        {
            java.util.ArrayList result = new java.util.ArrayList();
            
            while(rs.next())
            {
                result.add(rs.getString(field));
                
            }
            
            //System.out.print("sadasdasd "+result.size() +"+++++++");
            
            if(result.size() > 0)
            {
                
                current = Integer.parseInt(result.get(result.size()-1).toString().substring(10))+1;
                System.out.print(current);
                if(current > 0 && current <= 9) { return prefix + "0000000" + current;}
                
                else if(current > 9 && current <= 99) { return prefix + "000000" + current;}
                
                else if(current > 99 && current <= 999) { return prefix + "00000" + current;}
                
                else if(current > 999 && current <= 9999) { return prefix + "0000" + current;}
                
                else if(current > 9999&& current <= 99999) { return prefix + "000" + current;}
                
                else if(current > 99999 && current <= 999999) { return prefix + "00" + current;}
                
                else if(current > 999999 && current <= 9999999) { return prefix + "0" + current;}
                
                else if(current > 9999999 && current <= 99999999) { return prefix + current;}
            }
            
            else
            {
                return prefix + "00000001";
            }
            
        }
         
        
        catch(SQLException sqle)
        {
            sqle.printStackTrace();
            System.out.print(sqle); 
        }
        
      return null;
              
        
    }
    
    public String getPrimaryKey2(String field,String table,String prefix)
    {
        
            ResultSet rs = SQLSelect(field,table);
            int current = 0 ;
        try
        {
           java.util.ArrayList result = new java.util.ArrayList();
           
           while(rs.next())
           {
               result.add(rs.getInt(field));
               
           }
           
           if(result.size()>0)
           {
               current = Integer.parseInt(result.get(result.size()-1).toString().substring(3, 10))+1;
           }
            else if(current > 9 && current <= 99) { return prefix + "000000" + current;}
                
                else if(current > 99 && current <= 999) { return prefix + "00000" + current;}
                
                else if(current > 999 && current <= 9999) { return prefix + "0000" + current;}
                
                else if(current > 9999&& current <= 99999) { return prefix + "000" + current;}
                
                else if(current > 99999 && current <= 999999) { return prefix + "00" + current;}
                
                else if(current > 999999 && current <= 9999999) { return prefix + "0" + current;}
                
                else if(current > 9999999 && current <= 99999999) { return prefix + current;}
            
            
        }
        catch (Exception exp)
        {
            exp.printStackTrace();
            System.out.print(exp);
        }
    
        return prefix +  0000001;
        
    }
    
    
    public static void main(String[] args) throws SQLException,ClassNotFoundException
    {
        DBConnection db = new DBConnection();
        db.GetMySQLConnection();
    }
    
   }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

