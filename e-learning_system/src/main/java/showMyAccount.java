/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static gr.csd.uoc.cs359.winter2019.logbook.db.UserDB.getUser;
import gr.csd.uoc.cs359.winter2019.logbook.model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author George
 */
@WebServlet(urlPatterns = {"/showMyAccount"})
public class showMyAccount extends HttpServlet {

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
      
        HttpSession session = request.getSession();
        String username = session.getAttribute("ulogin").toString();
        System.out.println(username);
         User me = null;
        try {
            me = getUser(username);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(showMyAccount.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        try (PrintWriter out = response.getWriter()) {
        response.setStatus(200);
        
            out.println( "<h3>" + "Your account data" + "</h3>" + "\n"+
                    "<input class=\"sign\" type =\"submit\" value =\"Edit Account\" onClick = \"editAccount()\">"+
                       "<input class=\"sign\" type =\"submit\" value =\"Delete Account\" onClick = \"deleteAccount()\">"+
                    "<table class=\"table table-striped\">" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Username: </th>" + "<th>" + me.getUserName() + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Email: </th>" + "<th>" + me.getEmail() + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Password: </th>" + "<th>" +me.getPassword() + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>FirstName: </th>" + "<th>" + me.getFirstName() + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>LastName: </th>" + "<th>" + me.getLastName() + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Bithday: </th>" + "<th>" + me.getBirthDate() + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Gender: </th>" + "<th>" + me.getGender() + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Country: </th>" + "<th>" + me.getCountry() + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>City: </th>" + "<th>" + me.getTown() + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Address: </th>" + "<th>" + me.getAddress() + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Profession: </th>" + "<th>" + me.getOccupation() + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>Interests: </th>" + "<th>" + me.getInterests() + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "<tr>" + "\n"
                    + "<th>General Info: </th>" + "<th>" + me.getInfo() + "</th>" + "\n"
                    + "</tr>" + "\n"
                    + "</table>" + "\n"
                    
            );
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
