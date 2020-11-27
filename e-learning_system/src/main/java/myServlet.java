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

/**
 *
 * @author George
 */
@WebServlet(name="myServlet" ,urlPatterns = {"/myServlet"})
public class myServlet extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("here\n");
        String msg = "";
        String username = request.getParameter("username");
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
        String info = request.getParameter("info");
        int flagv = 0;
        try {
            
            if(UserDB.checkValidUserName(username)== false)
            {
                 msg = "Username exists";
            }
            else
                if(UserDB.checkValidEmail(username)== false)
                {
                  msg = "Email exists";
                }
                else
                {
                    flagv = 1;
                }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(myServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (flagv == 1){
        int flag = 0;
        String pattern = ".{6,}";
        if(flag == 0)
            if( !username.matches(pattern))
            {
                flag = 1;
            }
         
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
                            + "<th>Username: </th>" + "<th>" + username + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>Email: </th>" + "<th>" + email + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>Password: </th>" + "<th>" + password + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>FirstName: </th>" + "<th>" + firstname + "</th>" + "\n"
                            + "</tr>" + "\n"
                            + "<tr>" + "\n"
                            + "<th>LastName: </th>" + "<th>" + lastname + "</th>" + "\n"
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
                    User user = new User();
        user.setUserName(username);
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
         UserDB.addUser(user);
         Random rand = new Random();
         Cookie userCookie;
            userCookie = new Cookie ("oreo",""+rand.nextInt());
            int time = 60*24*3600;// a month
            userCookie.setValue(username);
            userCookie.setMaxAge(time);
            response.addCookie(userCookie);
           
        }
        else
        {
            
            response.setStatus(400);
            if(msg.contentEquals(""))
            {
                msg = "Wrong data input";
            }
            out.println("<p>"+msg+"<p>");
             out.close();
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(myServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(myServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
