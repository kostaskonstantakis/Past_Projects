/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import gr.csd.uoc.cs359.winter2019.logbook.db.*;
import gr.csd.uoc.cs359.winter2019.logbook.model.*;
import java.io.IOException;
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
 * @author gussl
 */
@WebServlet(name = "RatePost", urlPatterns = {"/RatePost"})
public class RatePost extends HttpServlet 
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException 
    {
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
                         throws ServletException, IOException 
    {
        String id         = request.getParameter("id");
        String rate_value = request.getParameter("rate_value");
        
        HttpSession rate_post = request.getSession();
        
        String username = rate_post.getAttribute("username").toString();
        
        if(id != null)
        {
            Rating rating = new Rating();
            
            rating.setPostID(Integer.parseInt(id));
            rating.setRate(Integer.parseInt(rate_value));
            try {
                rating.setUserName(username);
                
                RatingDB.addRating(rating);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RatePost.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else
        {
            response.setStatus(400);
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
