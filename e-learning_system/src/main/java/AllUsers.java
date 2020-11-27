/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static gr.csd.uoc.cs359.winter2019.logbook.db.UserDB.getUsers;
import gr.csd.uoc.cs359.winter2019.logbook.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(urlPatterns = {"/AllUsers"})
public class AllUsers extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
             response.setStatus(200);
               HttpSession session = request.getSession();
                        
                        
			 String users = request.getParameter("users");
                           System.out.print(users);
			
                       System.out.println("reeeeeeeeeee");
                     String[] splitArray = users.split("@");
                         if(users.equals(""))
                         {
                             splitArray = (String[]) session.getAttribute("active");
                         }
                         else
                              session.setAttribute("active", splitArray);
                     
                     for(String w:splitArray){
                         w=w.replaceAll("[\\n\\t ]", "");
                            System.out.println(w);
                        }
                    
                      //System.out.println(splitArray);
			/* TODO output your page here. You may use following sample code. */
            out.print("<table class=\"table table-striped\">" + "\n");
            List<User> list = null;
            String status = "";
            try {
                list = getUsers();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AllUsers.class.getName()).log(Level.SEVERE, null, ex);
            }
            int i = 0;
            for (User user : list) {
                for(String w:splitArray)
                {
                   // System.out.println(user.getUserName());
                    w=w.replaceAll("[\\n\\t ]", "");
                    
                    System.out.println(w);
                    if(w.equals(user.getUserName()))
                    {
                     System.out.println("active");
                        status = "active";
                        break;
                    }
                    else
                    {
                        status="inactive";
                    }
                }
                out.print("<tr>" + "\n"
                        + "<th>User (" + i + ") : </th>" + "<th>" + user.getUserName() + "</th>" + "<th> "+ " <button id =\""+user.getUserName()+"\" class = \"profileb\" onClick = \"showPofile(this.id)\">View Profile</button>\n"+" </th>" +
                        "<th>" +status+"</th"+"\n"
                        + "</tr>" +
                       //"<tr></tr>"+  
                       "\n");
                i++;
            }
            out.println("</table>" + "\n");
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
