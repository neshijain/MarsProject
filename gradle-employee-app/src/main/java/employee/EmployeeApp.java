

/******************************************************************************

 Online Java Compiler.
 Code, Compile, Run and Debug java program online.
 Write your code in this editor and press "Run" button to execute it.

 *******************************************************************************/
import java.util.Scanner;
import java.util.ArrayList; // import the ArrayList class
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.InputStreamReader;
import java.io.*;
import org.json.simple.parser.*;
// import org.json.simple.*;
import org.json.*;
import org.json.simple.JSONObject;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import javax.swing.*;



class EmployeeApp  extends JFrame{

    static ArrayList<Employee> emp = new ArrayList<Employee>();
    static String url = "jdbc:sqlite:C://sqlite/db/test.db";
    static Connection conn = null;
    String s = "Enter Commands\nAdd:[1 id firstname lastname]\nEdit:[2 id firstname lastname]\nCount:[3]\nList:[4]\nRemove:[5 id]\nAdd person using JSON:[6]\nExit:[7]\n";
    JLabel label = new JLabel("<html>" +"</html>");
    JTextField textField = new JTextField("", 20);
    JButton button = new JButton("OK");

    public EmployeeApp() {
        super("Enter your options");

        JTextArea textArea = new JTextArea(2, 20);
        textArea.setText(s);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setBackground(UIManager.getColor("Label.background"));
        textArea.setFont(UIManager.getFont("Label.font"));
        textArea.setBorder(UIManager.getBorder("Label.border"));
        setLayout(new FlowLayout());
        textField.setFont(new java.awt.Font("Arial", Font.ITALIC | Font.BOLD, 12));
        textField.setForeground(Color.BLUE);
        textField.setBackground(Color.YELLOW);
        textField.setSelectionColor(Color.CYAN);
        textField.setSelectedTextColor(Color.RED);
        textField.setSelectionStart(8);
        textField.setSelectionEnd(12);
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            }
        });

        // adds key event listener
        textField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent event) {
                String content = textField.getText();
                if (!content.equals("")) {
                    button.setEnabled(true);
                } else {
                    button.setEnabled(false);
                }
            }
        });

        // adds action listener for the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String txt = textField.getText();
                String[] arr = txt.split(" ");
                if(arr.length <= 0)
                {
                    JOptionPane.showMessageDialog(EmployeeApp.this,
                            "please enter valid command");
                }
                String ret =dispatch(arr);
                JOptionPane.showMessageDialog(EmployeeApp.this,
                        ret );
            }
        });
        add(label);
        add(textArea);
        add(textField);
        add(button);

        //////////////////Add note for json ///////////////////

        JTextArea note = new JTextArea(2, 20);
        note.setText("NOTE : To parse json add a file in C:\\code\\file.json in the  format given in read.me   " );
        note.setWrapStyleWord(true);
        note.setLineWrap(true);
        note.setOpaque(false);
        note.setEditable(false);
        note.setFocusable(false);
        note.setBackground(UIManager.getColor("Label.background"));
        note.setFont(UIManager.getFont("Label.font"));
        note.setBorder(UIManager.getBorder("Label.border"));
        add(note);

        ////////////////////////////////////////////////////
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
		
		DBUsingJava.createDatabase();
		//DBUsingJava.insert("1","1","1");
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EmployeeApp();
            }
        });
    }

    public static String dispatch( String[] arr){
        Employee employee = new Employee();
        int choice = 0;
        try {
            choice = Integer.parseInt(arr[0]);
        } catch (NumberFormatException e) {
            return("Enter correct format of command");
        }
        try{
            switch(choice)
            {
                case 1 :
					
					if(arr.length!=4)
						 return("Please enter proper command");
                    Employee temp = new Employee(arr[1],arr[2],arr[3]);
                    emp.add(temp);
					return (DBUsingJava.insert(arr[1],arr[2],arr[3]));
                   // return("Employee added");
                case 2 :
                    	
						if(arr.length!=4)
						 return("Please enter proper command");
							return (DBUsingJava.update(arr[1],arr[2],arr[3]));
                      
                case 4 :
                    int length = emp.size();
                    String data ="";
                    for(int i=0;i<length;i++)
                    {
                        data = "Listing employee "+(i+1)+" id is "+emp.get(i).id+" first name is "+emp.get(i).FirstName+" last name is "+emp.get(i).LastName;
                    }
                    return(DBUsingJava.getDatabase());
                case 3 :
                    return("Total count is "+DBUsingJava.getcount());
                case 5 :
                  	if(arr.length!=2)
						 return("Please enter proper command");
                    
							return (DBUsingJava.remove(arr[1]));
                     
                case 6 :

                    String sss= decode();
                    return(sss);
                case 7 :
                    System.exit(0);
                    break;

                default :
                    return("Enter correct format of command");
            }
            return "";
        }

        catch(Exception e)
        {
        }
        return "";
    }

    public static  Connection connect() {
        // SQLite connection string


        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Insert a new row into the warehouses table
     *
     * @param name
     * @param capacity
     */
    public static void insert(String id, String firstname, String lastname) {
        String sql = "INSERT INTO employees(id,firstname,lastname) VALUES(?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, firstname);
            pstmt.setString(3, lastname);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String decode() {
        try{
            JSONParser parser = new JSONParser();
            JSONArray a = (JSONArray) parser.parse(new FileReader("c:\\code\\file.json"));

            for (Object o : a)
            {
                JSONObject person = (JSONObject) o;

                String ID = (String) person.get("id");
                String FNAME = (String) person.get("first");
                String LNAME = (String) person.get("last");
                DBUsingJava.insert(ID,FNAME,LNAME);
            }
			
			return "Added Successfully";
        }
        catch(Exception e)
        {

        }

        return "Employee Added";
    }


}


