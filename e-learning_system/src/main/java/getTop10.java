/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import gr.csd.uoc.cs359.winter2019.logbook.db.PostDB;
import gr.csd.uoc.cs359.winter2019.logbook.model.Post;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author George
 */
@WebServlet(urlPatterns = {"/getTop10"})
public class getTop10 extends HttpServlet {

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
        response.setContentType("application/json;charset=UTF-8");
        
        
        JSONObject response_json = new JSONObject();
           
        try (PrintWriter out = response.getWriter()) {
        List<Post> postsOutput;
        Iterator posts;
        Post post;
            try {
                 postsOutput = PostDB.getTop10RecentPosts();

                posts = postsOutput.iterator();

                JSONArray top10Posts = new JSONArray();

                while(posts.hasNext())
                {
                    

                    JSONObject singlePost  = new JSONObject();

                    post = (Post)posts.next();
                        
                    singlePost.put("username",post.getUserName());
                    singlePost.put("date",post.getCreatedAt());
                    singlePost.put("id",post.getPostID());
                    singlePost.put("base64",post.getImageBase64());
                    singlePost.put("description",post.getDescription());
                    System.out.println(singlePost);
                   top10Posts.add(singlePost);
                 
                }
                     response_json.put("posts",top10Posts);
                    out.print(response_json.toString());
                out.close();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(getTop10.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.close();
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
