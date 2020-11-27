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
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author George
 */
@WebServlet(urlPatterns = {"/printMyprofile"})
public class printMyprofile extends HttpServlet {

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
        HttpSession session = request.getSession();
         System.out.println("ehem");
        String user = session.getAttribute("ulogin").toString(); 
        System.out.println(user);
        JSONObject response_json = new JSONObject();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
       List<Post> postsOutput;
            int i = 0;
            Iterator posts;
            Post post;
            postsOutput = PostDB.getTop10RecentPostsOfUser(user);  
            System.out.println("*************");
             System.out.println(postsOutput);
            
              System.out.println("*************");
            posts = postsOutput.iterator();
            JSONArray userPosts = new JSONArray();
            while(posts.hasNext())
            {
                i++;
                JSONObject singlePost  = new JSONObject();// set the data of the user
                post = (Post)posts.next();
                singlePost.put("username",user);
                singlePost.put("date",post.getCreatedAt());
                singlePost.put("id",post.getPostID());
                singlePost.put("description",post.getDescription());
                singlePost.put("base64",post.getImageBase64());
                userPosts.add(singlePost);
            }
            response_json.put("posts",userPosts);  
             response_json.put("length",i); // set number of posts
            System.out.println(response_json.toString());
            out.print(response_json.toString());
            out.close();
          
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(printMyprofile.class.getName()).log(Level.SEVERE, null, ex);
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
