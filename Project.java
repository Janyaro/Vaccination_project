import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

interface user {
    public void sign_in(String name, int ID, String password, String Designation, String email, String Contact);
    public void DeleteUser(String name ,String email);
    public void Forget(String name, String Email);
}

interface ImplimentMethod {
    
    public void add_Deatail(String child_name, String Father_name, int child_age, String vaccine, String gender,
            String date, String location) throws SQLException;

    public void Fetch_Detail() throws SQLException;

    public void DeleteData(String cname, String faname);

    public void Indivdual_Selection(String name, String fname);
}

class userAccount implements user {
    Connection con;
    PreparedStatement pst;

    @Override
    public void sign_in(String Name, int ID, String Password, String Designation, String E_mail, String Contact) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "");
            try (Connection conn = SQLConnection.makeConnection()) {
                String sqlQuery = "insert into sign_in values(?,?,?,?,?,?)";
                pst = con.prepareStatement(sqlQuery);
                pst.setString(1, Name);
                pst.setInt(2, ID);
                pst.setString(3, Password);
                pst.setString(4, Designation);
                pst.setString(5, E_mail);
                pst.setString(6, Contact);
                if (pst.executeUpdate() == 1)
                    System.out.println("Record Saved ");
                else
                    System.out.println("Record not contain in the  Error");
            } catch (Exception e) {
                System.out.println("Exception is occured");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    
    public void Forget(String name, String Email) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user",
                    "root", "");
            String sql = "select * from sign_in where Name = ? and E_mail =?";
            try {
                pst = con.prepareStatement(sql);
                pst.setString(1, name);
                pst.setString(2, Email);
                ResultSet rs1 = pst.executeQuery();
                if (rs1.next() == false) {
                    System.out.println("Sorry record not found");
                } else {
                    System.out.println("**************************");
                    System.out.println("Name" + " " + rs1.getString(1));
                    System.out.println("ID" + " " + rs1.getString(2));
                    System.out.println("Password" + " " + rs1.getString(3));
                    System.out.println("Designation" + " " + rs1.getString(4));
                    System.out.println("Email" + " " + rs1.getString(5));
                    System.out.println("Contact" + " " + rs1.getString(6));

                    System.out.println("**************************");

                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println("SQL Connection program occured");
        }

    }

    @Override
    public void DeleteUser(String name, String email) {
        
            String sql = "delete  from sign_in where Name=? and E_mail=?";
            
            try {
    
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user",
                        "root", "");
                PreparedStatement pst = con.prepareStatement(sql);
                       
                pst.setString(1, name);
                pst.setString(2, email);
                pst.execute();
                System.out.println("Data Delected");
 
            } catch (SQLException e) {
                System.out.println("Data is not find in the data base ");
                e.printStackTrace();
            }
        
        }

}

class fetch extends Thread {
    public void run() {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/children_detail", "root", "")) {
            try {
                java.sql.Statement stml = con.createStatement();
                ResultSet rs = stml.executeQuery("select * from data");
                while (rs.next())
                
                    System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " "
                            + rs.getString(4) + " " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7));
                con.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Exception occurd in your program" + e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

class vaccination {

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
                System.out.println("Record Saved ");
            else
                System.out.println("Error");
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

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/children_detail",
                    "root", "");
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cname);
            pst.setString(2, faname);
            pst.execute();
        System.out.println("Data Deleted");
        } catch (SQLException e) {
            System.out.println("Data is not found ");
            
        }

    }

}

public class Project {
    public static void main(String[] args) throws SQLException {
        userAccount a = new userAccount();
        vaccination v = new vaccination();
        Scanner sc = new Scanner(System.in);
        char cont;
        int back;
        try {
            
        
        System.out.println("***********************Welcome to vaccine Management System ****************");
        do {
        
            System.out.println("Login user");
            System.out.println("Vaccination ");
            System.out.println("Forget password");
            System.out.println("Delete User ");
            System.out.println("***********************************************************************");
            int key = sc.nextInt();
            sc.nextLine();
            switch (key) {
                case 1:
                    System.out.println("Enter Name");
                    String Vac_name = sc.nextLine();
                    System.out.println("Enter ID");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter Pass");
                    String Pass = sc.nextLine();
                    System.out.println("Enter Designation");
                    String des = sc.nextLine();
                    System.out.println("Enter E mail");
                    String email = sc.nextLine();
                    System.out.println("Enter Contact Number");
                    String contact = sc.nextLine();
                    a.sign_in(Vac_name, id, Pass, des, email, contact);
                    break;
                case 2:
                    PreparedStatement pst;
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/user",
                            "root", "");
                    System.out.println("Enter Your Name");
                    String Name = sc.nextLine();
                    System.out.println("Enter Your Password");
                    String Password = sc.nextLine();
                    String sqlQuery1 = "select * from sign_in where Name = ? and Password= ?";
                    pst = con.prepareStatement(sqlQuery1);
                    pst.setString(1, Name);
                    pst.setString(2, Password);
                    ResultSet rs1 = pst.executeQuery();
                    if (rs1.next() == false) {
                        System.out.println("Sorry user doesnot found");
                    } else {
                        try {

                            do {

                                System.out.println(
                                        "*************************Welcome to Vaccination Poratl***************************");
                                System.out.println("1 : Vaccination ");
                                System.out.println("2 : See Vacinated Children");
                                System.out.println("3 : Search Vaccinated Children Data");
                                System.out.println("4 : Delete Vaccinated Children Data");
                            System.out.println("*******************************************************");
                         
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
                                        v.add_Deatail(child_name, Father_name, child_age, vaccine, gender, date,
                                                location);
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

                    }
                    break;
                case 3:
                    System.out.println("Enter Your Name");
                    String fName = sc.nextLine();
                    System.out.println("Enter Your Email");
                    String Email = sc.nextLine();
                    a.Forget(fName, Email);
                    break;
                case 4 :
                System.out.println("Enter Your Name");
                    String name = sc.nextLine();
                    System.out.println("Enter Your Email");
                    String del_email = sc.nextLine();
                a.DeleteUser(name, del_email);
                break;
                default:
                System.out.println("Select the Above Option");
                    break;
            }

            System.out.println("Press 0 to back");
            back = sc.nextInt();
        } while (back == 0);
    } catch (Exception e) {
        System.out.println("Select above option ");
    }
        sc.close();
    }
}
