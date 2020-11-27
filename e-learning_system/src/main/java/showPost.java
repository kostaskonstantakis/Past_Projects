/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import gr.csd.uoc.cs359.winter2019.logbook.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import gr.csd.uoc.cs359.winter2019.logbook.db.*;

/**
 *
 * @author User
 */
@WebServlet(urlPatterns = {"/showPost"})
public class showPost extends HttpServlet {

    private float averageRating;
    private int plithosRatings; //how many ratings are there for this particular post
    private int userRating; //what rate did that particular user with a certain id give

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
            //Maybe the id isn't needed, dunno...
            String id = request.getParameter("id"); //or response?
            Post post = new Post();
            post.setPostID(Integer.parseInt(id));
            try {
                //calculate the average rating for this particular post
                for (Rating r : RatingDB.getRatings(post.getPostID().intValue())) {

                    averageRating += r.getRate();
                }
                plithosRatings = RatingDB.getRatings(post.getPostID().intValue()).size();
                averageRating = averageRating / plithosRatings;


            } catch (ClassNotFoundException ex) {
                System.err.println("Couldn't find the class!");
            }
            //averageRating = RatingDB.getRatings().
            out.println(" <header>\n"
                    +                       "<div id = \"signup_box\" class=\"head\">\n" +
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
                        " </header>\n"  +"    <div id =\"buttonspost\">\n" +"        <div id = \"logindex\"> "+
    "                \n" +"<div id =\"canvas\" > "+ "</div>\n"+
    "                          <div id =\"postbox\">\n"+                 
 
    "   </div>"+                "        <textarea id = \"textpost\" rows=\"4\" cols=\"50\">\n" +
    "        </textarea>\n" + 
    "    <input type=\"button\" class = \"hbuttonpost \"  value = \"Pic by url\" onClick = \"showPostUrl()\">\n" +
    "    <input type=\"button\" class = \"hbuttonpost \"  value = \"Import pic\" onClick = \"showPostFile()\">\n" +
 "    <input type=\"button\" class = \"hbuttonpost \"  value = \"Pic with camera\" onClick = \"showform()\">\n"
                    + //this might be erroneous
    "    </div>"+ "</div>");
            
            
            
            
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

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

}
