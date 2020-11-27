/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(urlPatterns = {"/cookieCheck"})
public class cookieCheck extends HttpServlet {

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
            Cookie[] cookies = request.getCookies();
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
          if (cookies != null)
          {
              System.out.println("in");
              for (Cookie cookie: cookies)
              {
                  
                  System.out.println(cookie.getName());
                  if (cookie.getName().equals("oreo"))
                  {
                      response.setStatus(200);
                  out.println(" <header>\n"+
                      "<div id = \"signup_box\" class=\"head\">\n" +
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
                        " </header>\n" +
                        "    <body>\n" +
                        "        <div id = \"logindex\"> "
                            + "<h1> Login Succesful!</h1> \n" 
                            + "</div>\n" +
                        "    </body>");
                  }        
     
              }
          }
          else
          out.println(
                    
                    
		"<div class = \"wrapper\">"
		+"<h3 id= \"message\"><center>"
		+"Get access to exclusive content of the cutest upcoming cat influencer of 2019"
		+"</center></h3>"
		+"<div id = \" info\">"
			+"<input id = \"usernamelog\" class = \"log\" type=\"text\" placeholder=\"Username\" onChange= \"userbutton()\"  name=\"username\" pattern=\"[A-Za-z]{8,}\" title=\"The username must be at least 8 characters long\" required>"
			+"<input id = \"passwordlog\"  class = \"log\" type=\"password\" placeholder=\"Password\" name=\"pw\" pattern=\"(?=^.{8,10}$)(?=.*\\d)(?=.*\\W+)(?=.*[A-Z])(?=.*[a-z]).*$\" title=\"Must contain at least one number and one uppercase and lowercase letter, and at least 8 to 10 characters\" required>"	
		+"<div>"
			+"<input class=\"sign\" type =\"submit\" value =\"LOGIN\" onClick = \"login()\">"
                 +"</div>"
+"	</div>"
+"	</div>"

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
