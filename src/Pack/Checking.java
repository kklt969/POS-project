/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pack;

import javax.swing.*;
import java.util.*;
import java.lang.String.*;
public class Checking {
    public static boolean isValidName(String str)
    {
        if(IsNull(str))
        {
            return false;
        }
        
        else if(!IsLetter(str))
        {
            return false;
        }
        return true;
    }
    
    public static boolean IsNull(String str)
    {
        
        if(str.trim().equals("") || str.trim().equals(null))
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }
    
    public static boolean IsLetter(String str)
    {
        for(int i = 0; i < str.length(); i++)
        {
            if(Character.isLetter(str.charAt(i)))
            {
                return true;
            }
            
            } 
               return false; 
        }
    
    public static boolean IsAllDigit(String str)
    {
         for(int i = 0; i < str.length(); i++)
        {
            if(Character.isDigit(str.charAt(i)))
            {
                return true;
            }
            
            } 
               return false; 
        }
    
    public static boolean IsContain(char c , String str)
    {
         for(int i = 0; i < str.length(); i++)
        {
            if(str.charAt(i) == c)
            {
                return true;
            }
            
            } 
               return false; 
    }
    
     public static boolean IsContain(String s,Vector str)
    {
         for(int i = 0; i < str.size(); i++)
        {
            if(s.equals(str.elementAt(i)))
            {
                return true;
            }
            
            } 
               return false; 
    }
    
        
    public static boolean checktxtquantity(String strqp)
    {
        if(strqp.trim().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Enter quantity");
            return false;
        }
        
        else if(!IsAllDigit(strqp))
        {
            JOptionPane.showMessageDialog(null, "The quantity must be number");
            return false;
        }
        
        else if(Integer.parseInt(strqp) > 10000)
        {
            JOptionPane.showMessageDialog(null, "The quantity you entered is too many to purchase");
            return false;
        }
        
        else{
        return true;}
    }
    
    
    public static boolean checktxtprice(String strqp)
    {
        
         if(strqp.trim().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Enter price");
            return false;
        }
         
         else if(!IsAllDigit(strqp))
        {
            JOptionPane.showMessageDialog(null, "The quantity must be digit");
            return false;
        }
         
        
         else
         {
             return true;
         }
        
    }
    
    public static boolean checksalequantity(String strqp,String id)
    {
        
         if(strqp.trim().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Enter quantity");
            return false;
        }
         
         else if(!IsAllDigit(strqp))
        {
            JOptionPane.showMessageDialog(null, "The quantity must be number");
            return false;
        }
    
         else
         {
             mySQLQueries msql = new mySQLQueries();
             String q[] = msql.getItemData(id);
             
             if(Integer.parseInt(q[5]) < Integer.parseInt(strqp))
             {
                 
                 JOptionPane.showMessageDialog(null, "This item is out of stock");
                 return false;
             }
             else
             {
                 return true;
             }
             
             
         }
    
    }

    public static String Sumamount(Vector data,int t)
    {
        long sum = 0;
        
        for(int i = 0; i < data.size(); i++)
        {
            sum += Long.parseLong((String)data.elementAt(i));
        }
        
        if(t==1)
        {
            int len = String.valueOf(sum).length(), index = 0;
            StringBuffer str = new StringBuffer("");
            for(int i = 0; i < len; i++)
            {
                if(index == 3)
                {
                    str.append(",");
                    index = 0 ; i--;    
                }
                else
                {
                    str.append(String.valueOf(sum).charAt(len-i-1));
                    index ++;
                }
            }
            return str.reverse().toString();
        }
        else
        {
            return String.valueOf(sum);
        }
    }
   public static String[] fromtable(JTable mtable, int size, int column)
    {
        String [] str = new String[size];
        
        for(int i=0; i<size;i++)
        {
            if(IsContain(':',((String)mtable.getValueAt(i,column))))
            {
                int j = ((String)mtable.getValueAt(i,column)).indexOf("::");
                str[i] = ((String)mtable.getValueAt(i,column)).substring(0,j);
                
            }
            
            else
            {
                str[i] = ((String)mtable.getValueAt(i,column));
            }
     
        }
        return str;
    }
    
}
    

