/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Pack;

import java.util.*;
public class MyDate {
    
    
    public static String getdate(){
    StringBuffer str = new StringBuffer("");
    Date date = new Date();
    
    System.out.println(date);
    
    str.append(String.valueOf(date).substring(24));
    str.append("-");
    str.append(date.getMonth()+1);
     str.append(String.valueOf(date).substring(3,7));
     
     str.append("-");
     str.append(String.valueOf(date).substring(8,10));
     
     str.append("(");
     str.append(String.valueOf(date).substring(0,3));
     
     str.append(")");
     return str.toString();
    
    }
    
    public static void main(String[] args)
    {
        System.out.print(getdate());
    }
    
}
