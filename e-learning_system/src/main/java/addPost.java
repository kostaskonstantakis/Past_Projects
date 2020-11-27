/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import gr.csd.uoc.cs359.winter2019.logbook.db.PostDB;
import gr.csd.uoc.cs359.winter2019.logbook.model.Post;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
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
@WebServlet(urlPatterns = {"/addPost"})
public class addPost extends HttpServlet {

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
        
       String description = request.getParameter("description");
        String image64    = request.getParameter("image64");
        String lang         = request.getParameter("lang");
        String longt        = request.getParameter("long");
        
         HttpSession session = request.getSession();
         String username = session.getAttribute("ulogin").toString();
         System.out.println(username);
         Post post = new Post();
         post.setUserName(username);
         Date date = new Date();
         Timestamp time = new Timestamp(date.getTime());
        post.setDescription(description);
        post.setCreatedAt(time.toString());
        if (image64 != null)
        {
            var stringLength =image64.length() ;
        var bytes = 4 * Math.ceil((stringLength / 3))*0.5624896334383812;
        var kb=bytes/1000;
        if(kb<50)
            post.setImageBase64(image64);
        }
        post.setLatitude(lang);
        post.setLongitude(longt);
        
        try {
            PostDB.addPost(post);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addPost.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println(" <header>\n"+
                    "<div id = \"signup_box\" class=\"head\">\n" +
                        "	<input type=\"button\" class = \"hbutton \" value = \"Logout\" onClick = \"logout()\">\n" +
                        "	<input type=\"button\" class = \"hbutton \"  value = \"Show users\" onClick = \"showAllUsers()\">\n" +
                         "	<input type=\"button\" class = \"hbutton \"  value = \"My Posts\" onClick = \"showAllUsers()\">\n" +
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
                        "	\n" +
                        " </header>\n" +
                    
                     "    <body>\n" +
                        "        <div id = \"logindex\"> "
                        +   "<h1> POST ADDED</h1>"
                            + "</div>\n" +
                        "    </body>");
            
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
