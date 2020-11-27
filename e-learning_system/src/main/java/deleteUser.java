/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static gr.csd.uoc.cs359.winter2019.logbook.db.CommentDB.deleteComment;
import static gr.csd.uoc.cs359.winter2019.logbook.db.CommentDB.getComments;
import gr.csd.uoc.cs359.winter2019.logbook.db.PostDB;
import gr.csd.uoc.cs359.winter2019.logbook.db.RatingDB;
import gr.csd.uoc.cs359.winter2019.logbook.db.UserDB;
import gr.csd.uoc.cs359.winter2019.logbook.model.Comment;
import gr.csd.uoc.cs359.winter2019.logbook.model.Post;
import gr.csd.uoc.cs359.winter2019.logbook.model.Rating;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(urlPatterns = {"/deleteUser"})
public class deleteUser extends HttpServlet {

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
        try {
            System.out.println("what");
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
               List<Rating> ratings = RatingDB.getRatings(); 
            String username = session.getAttribute("ulogin").toString();
            System.out.println(username);
            List<Post> posts = PostDB.getPosts();
            //System.out.println(posts);
            int id;
            String user;
             List<Post> posts_to_delete = PostDB.getPosts();
                  List<Comment> comments = new ArrayList<>();
                Comment comment = new Comment();

                for (Post post_to_delete : posts_to_delete) {
                    comments = getComments(post_to_delete.getPostID());

                    for (int i = 0; i < comments.size(); i++) {
                        comment = comments.get(i);
                        if (comment.getUserName().equals(username)) {
                        
                            comment = comments.get(i);
                            deleteComment(comment.getID());
                          
                        }
                    }
                }
                   for (Rating rating : ratings) {
                        user = rating.getUserName();

                        if (user.equals(username)) {
                            id = rating.getID();
                            RatingDB.deleteRating(rating);
                            //RatingDB.deleteRating(id);
                        }
                    }
                
            for (Post post : posts){
            user =  post.getUserName();
             System.out.println(user);
                if(user.equals(username)){
                  
              id = post.getPostID();
                    // System.out.println(id);
               PostDB.deletePost(post);
               }
               
            }
            UserDB.deleteUser(username);
            
            
            try (PrintWriter out = response.getWriter()) {

                out.println("User deleted.");
                /* TODO output your page here. You may use following sample code. */
                
            }
        }   catch (ClassNotFoundException ex) {
            Logger.getLogger(deleteUser.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(deleteUser.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(deleteUser.class.getName()).log(Level.SEVERE, null, ex);
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
