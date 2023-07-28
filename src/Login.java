import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login {
    private JPanel rootPanel;
    private JTextField textField1;
    private JTextField textField3;
    private JButton OKButton;
    static final String DB_URL = "jdbc:mysql://localhost/poo"; // Aqui va la ruta de la base de datos
    static final String USER = "root";
    static final String PASS = "root_bas3";
    static final String QUERY = "SELECT * FROM estudiantes";

    public Login() {
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (
                        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(QUERY);
                ){
                    while(rs.next()){
                        System.out.println("Id: " + rs.getLong("Id"));
                        System.out.println("Nombre: " + rs.getString("Nombre"));
                        System.out.println("Edad: " + rs.getInt("Edad"));
                        System.out.println("Ciudad: " + rs.getString("Ciudad"));
                        System.out.println("Cedula: " + rs.getLong("Cedula"));
                        System.out.println("Password: " + rs.getString("Password"));
                        System.out.println();
                    }
                }catch (SQLException a){
                    throw new RuntimeException(a);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
