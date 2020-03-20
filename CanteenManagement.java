/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package canteenmanagement;
import java.util.Scanner;
import java.sql.*;



class canteenoperations{
    
    double calculate_discount(int amm)
    {
        int o_count=3;
        if(o_count>3)
            amm=amm-(((o_count-3)/100)*amm);
        else if(o_count>10)
            amm=amm-(10/100*amm);
        return amm;
    }
    
    int calculate_penalty()
    {
        int o_cancel=4,a;
        if(o_cancel>3) a=20;
        else a=0;
        return a;
    }
    
    void bill(int SID,int CNO,int item_id)
    {
         try{  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/canteen","root","");  
            //here sonoo is database name, root is username and password  
            Statement stmt=con.createStatement();  
            stmt.executeUpdate("INSERT INTO `order_table`(`Tra_id`, `SID`, `CNO`, `Item_id`, `Price`) VALUES (25,"+SID+","+CNO+","+item_id+",25)");
            con.close();  
            }catch(Exception e){ System.out.println(e);}  
        
        System.out.println("Bill="+52);
//The final amount should include tax for total cancelations the user may have done along with the discounts
    System.out.println("Transaction Id="+123);
    System.out.println("***PLEASE CARRY THE TRANSCATION ID FOR THE REFERENCE AT THE COUNTER");
    
    }
}

/**
 *
 * @author Abhishek
 */
public class CanteenManagement {

    /**
     * @param args the command line arguments
     */
    void order(int USN)//This function has to be changed
    {
        int choice;
        Scanner S=new Scanner(System.in);
        canteenoperations bill=new canteenoperations(); 
        
        System.out.println("List of Canteens:");
        System.out.println("1.Shetty");
        System.out.println("2.Sadhguru");
        System.out.println("3.Suresh Anna");
        System.out.println("Choose a canteen:");
        choice=S.nextInt();
        switch(choice)
        {
            
            case 1: try{  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/canteen","root","");  
            //here sonoo is database name, root is username and password  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from menu where CNO="+choice
                    );  
            System.out.println("Id\tNAME\tPRICE");  
            while(rs.next())  {
            System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));  
              }
            con.close();  
            }catch(Exception e){ System.out.println(e);}  
            System.out.println("Enter the Item ID");
            int Id=S.nextInt();
            bill.bill(USN,choice,Id);
            
                    break;
                    
            case 2:try{  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/canteen","root","");  
            //here sonoo is database name, root is username and password  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from menu where CNO="+choice
                    );  
            System.out.println("Id\tNAME\t\tPRICE");  
            while(rs.next())  {
            System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));  
              }
            con.close();  
            }catch(Exception e){ System.out.println(e);}  
            System.out.println("Enter the Item ID");
            int Id2=S.nextInt();
            bill.bill(USN,choice,Id2);
                    break;
            case 3:System.out.println("Menu");
                    System.out.println("1.Idli--40/-");
                    System.out.println("2.Vada--25/-");
                    System.out.println("3.Uppama--25/-");
                    break;
            default:System.out.println("Invalid option");
        }
    }
    
   void user()
    {
      int flag=0;
      CanteenManagement session=new CanteenManagement();
      Scanner S=new Scanner(System.in);
       System.out.println("Enter USN");
       int USN=S.nextInt();
       try{  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/canteen","root","");  
            //here sonoo is database name, root is username and password  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from student");  
            while(rs.next())  
            if(USN==rs.getInt(1))
            {
                flag=1;
                System.out.println("NAME:"+rs.getString(2));
            }
                con.close(); 
            }catch(Exception e){ System.out.println(e);}  
       //validate in the database
       if(flag==1)
           session.order(USN);
       else{
           System.out.println("Invalid USN");
           user();
       }
       
    }
   
    void canteen()
    {
      CanteenManagement session=new CanteenManagement();
      Scanner S=new Scanner(System.in);
       System.out.println("Enter canteen Number");
       int c=S.nextInt();

       //validate in the database
       if(c==2)
           session.order(c);
       else{
           System.out.println("Invalid Canteen Number");
           canteen();
       }
           
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
       
        CanteenManagement session=new CanteenManagement();
        int choice;
        Scanner S=new Scanner(System.in);
        System.out.println("Welcome to Canteen Management!");
        System.out.println("1)USER");//1]Order,2]Penalty?
        System.out.println("2)CANTEEN");//1]Queue,2]Statistics?
        System.out.println("Choose a canteen:");
        choice=S.nextInt();
        switch(choice)
        {
            case 1:session.user();
                    break;
            case 2:
                    break;
            
            default:System.out.println("Invalid option");
        }
         
            }  
        }  
        
    
    
    

