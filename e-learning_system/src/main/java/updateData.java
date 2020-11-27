/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import gr.csd.uoc.cs359.winter2019.logbook.db.UserDB;
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
@WebServlet(urlPatterns = {"/updateData"})
public class updateData extends HttpServlet {

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
   System.out.println("here\n");
        HttpSession session = request.getSession();
        String usern = session.getAttribute("ulogin").toString();
       
        User user = null;
        try {
            user = getUser(usern);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(updateData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String password = request.getParameter("password");      
        String email = request.getParameter("email");
        String firstname = request.getParameter("fname");
        String lastname = request.getParameter("lname");
        String gender = request.getParameter("gender");
        String work = request.getParameter("work");
        String city = request.getParameter("city");
        String addr = request.getParameter("addr");
        String country = request.getParameter("country");
        String date = request.getParameter("date");
        String intrs = request.getParameter("intrs");
        System.out.println(intrs);
        String info = request.getParameter("info");
   
        System.out.println(info);
        int flagv = 1;

        if (flagv == 1){
        int flag = 0;
        String pattern = ".{6,}";
        
         
        System.out.println(flag);
        pattern = "[a-z0-9._%+-]+@[a-z0-9.-]+|\\.[a-z]{2,}$";
        if(flag == 0)
            if( !email.matches(pattern))
            {
                flag = 1;
            }
         System.out.println(flag);
        pattern = "(?=.*\\d)(?=.*\\W+)(?=.*[a-zA-Z]).{8,10}";   
        if(flag == 0)
            if( !password.matches(pattern))
            {
                flag = 1;
            }
         System.out.println(flag);

        pattern = ".{3,15}";
        if(flag == 0)
            if( !firstname.matches(pattern))
            {
                flag = 1;
            }
         System.out.println(flag);
        pattern =  ".{3,15}";   
        if(flag == 0)
            if( !lastname.matches(pattern))
            {
                flag = 1;
            }
         System.out.println(flag);
     
       
        
      
        try (PrintWriter out = response.getWriter()) {  
      
        if(flag == 0)
        {
            response.setStatus(200);
                    out.println(
                            "<table class=\"table table-striped\">" + "\n"
                            + "<tr>" + "\n"
                            + "<th>Username: </th>" + "<th>" + usern + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>Email: </th>" + "<th>" + email + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>Password: </th>" + "<th>" + password + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>Firstname: </th>" + "<th>" + firstname + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>Lastname: </th>" + "<th>" + lastname + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>Gender: </th>" + "<th>" + gender + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>Date: </th>" + "<th>" + date + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>Country: </th>" + "<th>" + country + "</th>" + "\n"
                            + "</tr>" + "\n"
                             + "<tr>" + "\n"
                            + "<th>Address: </th>" + "<th>" + addr + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>City: </th>" + "<th>" + city + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>Work: </th>" + "<th>" + work + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>Interests: </th>" + "<th>" + intrs + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>Info: </th>" + "<th>" + info + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "</table>" + "\n");
                    out.close();
                   
    
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setBirthDate(date);
        user.setCountry(country);
        user.setTown(city);
        user.setAddress(addr);
        user.setOccupation(work);
        user.setGender(gender);
        user.setInterests(intrs);
        user.setInfo(info);
            try {
                UserDB.updateUser(user);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(updateData.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
                }    }
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
