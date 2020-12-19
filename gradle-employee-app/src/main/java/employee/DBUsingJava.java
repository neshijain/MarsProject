import java.sql.*;

public class DBUsingJava

{

    public static void createDatabase( )

    {

        Connection c = null;
		 Statement stmt = null;

        try {

            Class.forName("org.sqlite.JDBC");

            c = DriverManager.getConnection("jdbc:sqlite:databse.db");

			createtable();
            c.close();
			getDatabase();
		}

        catch ( Exception e ) {

            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

            System.exit(0);

        }

        System.out.println("database successfully created");

    }


   public static void createtable( )

    {
		
		
        Connection c = null;
		 Statement stmt = null;

        try {

            Class.forName("org.sqlite.JDBC");

            c = DriverManager.getConnection("jdbc:sqlite:databse.db");

            System.out.println("Database Opened...\n");

            stmt = c.createStatement();
			 System.out.println("creating employee created");

            String sql = "CREATE TABLE employee " +

                    "(id TEXT PRIMARY KEY ," +

                    " firstname TEXT , " +

                    " lastname TEXT  " +

                    " ) " ;

            stmt.executeUpdate(sql);

            stmt.close();

            c.close();
          //  insert("112","111","111");
	//		 insert("113","111","111");
		//	  insert("114","111","111");
			getDatabase();
		//	remove("114");




        }

        catch ( Exception e ) {

            System.err.println( e.getClass().getName() + ": " + e.getMessage() );

          

        }

        System.out.println("table successfully created");

    }

    public static String insert(String id, String fname, String lname)
    {
		 System.out.println("database successfully created");
        try {
            Connection c = null;
            String sql = "";
            Statement stmt = null;
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:databse.db");
			 System.out.println("insert successfully created");
            c.setAutoCommit(false);
            stmt = c.createStatement();


            sql = "INSERT INTO employee (id,firstname,lastname) " +

                    "VALUES ('" + id + "','" +

                    fname + "','" + lname + "')";
System.out.println("sql statement is "+sql);
            stmt.executeUpdate(sql);
			 System.out.println("database successfully created");

            System.out.println("Inserted Successfully!!!");
            stmt.close();
            c.commit();
            c.close();
			return "Inserted Successfully";
        }
        catch(Exception e)
        {
 System.out.println("Inserted Successfully!!!"+e.getMessage());
 return "Insertion error : Please check"+e.getMessage();
        }
    }


    public static String update(String id, String fname, String lname)
    {
		 System.out.println("update called");
        try {
            Connection c = null;
            String sql = "";
            Statement stmt = null;
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:databse.db");
			 System.out.println("database successfully created");
            c.setAutoCommit(false);
            stmt = c.createStatement();

			sql = "UPDATE employee SET firstname = '"+ fname + "',lastname='" + lname +

			"' WHERE id='" +id+"'" ;
			System.out.println("sql statement is "+sql);

			stmt.executeUpdate(sql);
			 System.out.println("database successfully created");

            System.out.println("Inserted Successfully!!!");
            stmt.close();
            c.commit();
            c.close();
			return "Updated Successfully";
        }
        catch(Exception e)
        {
 System.out.println("Inserted Successfully!!!"+e.getMessage());
  return "Updation error : Please check"+e.getMessage();
        }
    }



    public static String getDatabase()
    {
        try {
            Connection c = null;
            String sql = "";
            Statement stmt = null;
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:databse.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employee;");
            System.out.println("ID\t Name\t\t Price\t Qty ");
			String result = "";

            while ( rs.next() ) {


               String name = rs.getString("id");

              String  quantity = rs.getString("firstname");

              String  price = rs.getString("lastname");
			  result = result+" ID : "+name+"\t"+"  Firstname : "+quantity+"\t  LastName : "+price+"\n";

                System.out.println(rs);

            }

            rs.close();
            stmt.close();
            c.commit();
            c.close();
			return result;
        }
        catch(Exception e)
        {
			 return "Getting List error : Please check"+e.getMessage();

        }
        
    }
	
	
	    public static String getcount()
    {
        try {
            Connection c = null;
            String sql = "";
            Statement stmt = null;
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:databse.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employee;");
            System.out.println("ID\t Name\t\t Price\t Qty ");
			int res = 0;

            while ( rs.next() ) {
			res++;

             
            }

            rs.close();
            stmt.close();
            c.commit();
            c.close();
			return ""+res;
        }
        catch(Exception e)
        {
			 return "Getting List error : Please check"+e.getMessage();

        }
        
    }
	
	public static String remove(String id)
    {
        try {
            Connection c = null;
            String sql = "";
            Statement stmt = null;
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:databse.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
       
			sql="DELETE FROM employee WHERE id='" + id+"';";

			stmt.executeUpdate(sql);

            //rs.close();
            stmt.close();
            c.commit();
            c.close();
			return "Removed Successfully";
        }
        catch(Exception e)
        {
			 return "Removing error : Please check"+e.getMessage();

        }
    }
	
	
	
}


