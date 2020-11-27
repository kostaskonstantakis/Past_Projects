/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import gr.csd.uoc.cs359.winter2019.logbook.db.UserDB;
import gr.csd.uoc.cs359.winter2019.logbook.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author George
 */
@WebServlet(urlPatterns = {"/login"})
public class login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        User user;
        String username = request.getParameter("ulog");
        System.out.println(username);
        String password = request.getParameter("plog");
        System.out.println(password);
          
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                  try{
                    System.out.println(UserDB.checkValidUserName(username)); 
                      
            if (!UserDB.checkValidUserName(username))// here we check that if the name is not valid it exists so it means the user exists
            {
                user = UserDB.getUser(username);
               Random rand = new Random();
            Cookie userCookie;
            userCookie = new Cookie ("oreo",""+rand.nextInt());
            int time = 600;// a month
            userCookie.setValue(username);
            userCookie.setMaxAge(time);
            System.out.println("cookie");
            response.addCookie(userCookie);
                 if (password.equals(user.getPassword())) {
                      response.setStatus(200);
                      
                      HttpSession session = request.getSession();
                        
                        session.setAttribute("ulogin", username);
                        session.setAttribute("plogin", password);
                        out.println(" <header>\n"+
                        "<div id = \"signup_box\" class=\"head\">\n" +
                        "	<input type=\"button\" class = \"hbutton \" value = \"Logout\" onClick = \"logout()\">\n" +
                        "	<input type=\"button\" class = \"hbutton \"  value = \"Show users\" onClick = \"showAllUsers()\">\n" +
                         "	<input type=\"button\" class = \"hbutton \"  value = \"My Posts\" onClick = \"showMyPofile()\">\n" +
                        "	</div>\n" +
                        " <div id = \"signup_box2\" class = \"head\" >"+
                        " <input type=\"button\" class = \"hbutton2 \"  value = \"Create Post\" onClick = \"showPost()\">\n" +
                        " <input type=\"button\" class = \"hbutton2 \"  value = \"My Account\" onClick = \"showMyAccount()\">\n" +
                        " <input type=\"button\" class = \"hbutton2 \"  value = \"Top Posts\" onClick = \"showTopPost()\">\n" +
                        " </div>"+
                        "   <div id = \"title\" class=\"head\">\n" +
                        "	<h1>GLORIOUS WEBSITE</h1>\n" +
                        "   </div>\n" +
                        "	\n" +
                        " </header>\n" +
                        "    <body>\n" +
                        "        <div id = \"logindex\"> "
                            + "<h1> Login Succesful!</h1> \n" 
                            + "</div>\n" +
                        "    </body>");
                    
                 }
                 else
                 {
                       System.out.println("the end is near");
                    
                   
                      out.println(" <h1>FAILED TO LOGIN</h1>  ");
                 }
                
            } else
                 {
                     System.out.println("the end is near");
                    
                     
                      out.println(" <h1>FAILED TO LOGIN</h1>  ");
                 }
                    
                   
               } catch (ClassNotFoundException e) {
            }
                }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
