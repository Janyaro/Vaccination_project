import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
interface ImplimentMethod {
    public void add_Deatail(String child_name, String Father_name, int child_age, String vaccine, String gender,
            String date, String location) throws SQLException;
            public void Fetch_Detail() throws SQLException ;
            public void DeleteData(String cname, String faname);    
            public void Indivdual_Selection(String name, String fname);    
} 

class fetch extends Thread {
    public void run() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/children_detail", "root", "")) {
            try {
                java.sql.Statement stml = con.createStatement();
                ResultSet rs = stml.executeQuery("select * from data");
            while (rs.next())
                    System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " "
                            + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6)+" "+rs.getString(7));
                con.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("maslo ache tho " + e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

class vaccination {
    // Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public void add_Deatail(String child_name, String Father_name, int child_age, String vaccine, String gender,
            String date, String location) throws SQLException {
        // con =
       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/children_detail",
        "root", "");
        try (Connection conn = SQLConnection.makeConnection()) {
            String sqlQuery = "insert into data values(?,?,?,?,?,?,?)";
            pst = con.prepareStatement(sqlQuery);
            pst.setString(1, child_name);
            pst.setString(2, Father_name);
            pst.setInt(3, child_age);
            pst.setString(4, vaccine);
            pst.setString(5, gender);
            pst.setString(6, date);
            pst.setString(7, location);
            if (pst.executeUpdate() == 1)
                System.out.println("Record Saved To SQl Database");
            else
                System.out.println("Record Saved To SQl Database Error");
        } catch (Exception e) {
            System.out.println("Exception is occured");
            e.printStackTrace();
        }

    }

    public void Fetch_Detail() throws SQLException {
        fetch f = new fetch();
        f.start();
        try {
            f.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public void Indivdual_Selection(String name, String fname) {
        try {
            // con =
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/children_detail",
            "root", "");
            
            String sqlQuery1 = "select * from data where Child_Name = ? and Father_name= ?";
           pst = con.prepareStatement(sqlQuery1);
            pst.setString(1, name);
            pst.setString(2, fname);
            ResultSet rs1 = pst.executeQuery();
            if (rs1.next() == false) {
                System.out.println("Sorry record not found");
            } else {
                System.out.println("**************************");
                System.out.println("Name" + " " + rs1.getString(1));
                System.out.println("Father Name" + " " + rs1.getString(2));
                System.out.println("Age" + " " + rs1.getString(3));
                System.out.println("Vaccine" + " " + rs1.getString(4));
                System.out.println("Gender" + " " + rs1.getString(5));
                System.out.println("Date" + " " + rs1.getString(6));
                System.out.println("Location" + " " + rs1.getString(7));
                System.out.println("**************************");

            }
        } catch (Exception e) {
            
            System.out.println("Exception occured");
            e.printStackTrace();
            
        }
    }

    public void DeleteData(String cname, String faname) {
        String sql = "delete  from data where Child_Name=? and Father_name=?";
        try {
            // con =
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/children_detail",
            "root", "");
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cname);
            pst.setString(2, faname);
            pst.execute();
            System.out.println("Data Delected");
        } catch (SQLException e) {
            System.out.println("Data is not find in the data base ");
            e.printStackTrace();
        }

    }

}

public class Project {
    public static void main(String[] args) throws SQLException {

        vaccination v = new vaccination();

        Scanner sc = new Scanner(System.in);
        char cont;
        try {

        do {
           
           System.out.println("*************************Welcome to Vaccination Management system***************************");
            System.out.println("1 : Vaccination ");
            System.out.println("2 : Vacinated Children");
            System.out.println("3 : Search children Data");
            System.out.println("4 : Delete children Data");
                        
            
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Enter children name ");
                    String child_name = sc.nextLine();
                    System.out.println("Enter Father name ");
                    String Father_name = sc.nextLine();
                    System.out.println("Enter age");
                    int child_age = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter vaccine type");
                    String vaccine = sc.nextLine();
                    System.out.println("Enter gender");
                    String gender = sc.nextLine();
                    System.out.println("Enter vaccination Date");
                    String date = sc.nextLine();
                    System.out.println("Enter Location ");
                    String location = sc.nextLine();
                    v.add_Deatail(child_name, Father_name, child_age, vaccine, gender, date, location);
                    break;
                case 2:
                    v.Fetch_Detail();
                    break;
                case 3:
                    System.out.println("Enter Child name");
                    String name = sc.nextLine();
                    System.out.println("Enter father name");
                    String fname = sc.nextLine();

                    v.Indivdual_Selection(name, fname);
                    break;
                case 4:
                    System.out.println("Enter Child name");
                    String cname = sc.nextLine();
                    System.out.println("Enter father name");
                    String faname = sc.nextLine();

                    v.DeleteData(cname, faname);
                    break;
                default:
                    System.out.println("Select above options");
                    break;
            }
        
                    System.out.println("Do you want to continue");
            cont = sc.next().charAt(0);
        
    
        } while (cont == 'Y' || cont == 'y');
    } catch (Exception e) {
        System.out.println("Enter above Option");
        }
        sc.close();
    
    }
}
