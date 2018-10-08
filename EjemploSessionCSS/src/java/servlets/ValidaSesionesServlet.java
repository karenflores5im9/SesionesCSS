/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ValidaSesionesServlet extends HttpServlet {
   
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

      response.setContentType("text/html");
      String titulo = null;
      String nom = null;
      String aplld = null;
      String nombre = null;
      String apellido = null;
      
        try {
            Connection con = null;
            Statement sntnc = null;
            ResultSet rset = null;
            
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Lab3", "root", "n0m3l0");
                sntnc = con.createStatement();
                System.out.println("Conecta");
            } catch (Exception e) {
                System.out.println("No conecta");
            }

            rset = sntnc.executeQuery("select * from Usuarios where nombre = '" + nombre + "' and apellido = '" + apellido + "'");
            nom = rset.getString("nombre");
            aplld = rset.getString("apellido");
            
            if(nombre.equals(nom)&&apellido.equals(aplld)){
                titulo = "llave correcta continua la sesion";
            }else{
                titulo = "llave incorrecta inicie nuevamente sesion";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ValidaSesionesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }



      //Mostramos los  valores en el cliente
      PrintWriter out = response.getWriter();
      out.println("¿Continua la Sesion y es valida?: " + titulo);
      out.println("<br>");
      out.println("ID de la sesi&oacute;n JSESSIONID: ");
  
    }

}
