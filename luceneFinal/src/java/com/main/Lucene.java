/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main;

import com.bean.Bean;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

/**
 *
 * @author piyush
 */
public class Lucene extends HttpServlet {

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
            throws ServletException, IOException, TikaException, SAXException, ParseException, InvalidTokenOffsetsException {
        response.setContentType("text/html;charset=UTF-8");
          HttpSession session = request.getSession();
          String s1 = request.getParameter("query1");
          String s2 = request.getParameter("query2");
          String s3 = request.getParameter("radioBtn");
      
        Indexer indexer = new Indexer();
        indexer.indexing();
        Searcher searcher = new Searcher();
        List<Bean> printList = searcher.searching(s1,s2,s3);
       
        session.setAttribute("printList", printList);
        
        if(printList.isEmpty()){
            response.sendRedirect("empty.jsp");
        }
        else{
        response.sendRedirect("result.jsp");
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
        } catch (TikaException ex) {
            Logger.getLogger(Lucene.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Lucene.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Lucene.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidTokenOffsetsException ex) {
            Logger.getLogger(Lucene.class.getName()).log(Level.SEVERE, null, ex);
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
            HttpSession session = request.getSession();
            String s1 = request.getParameter("query1");
            Indexer indexer = new Indexer();
            try {
                indexer.indexing();
            } catch (TikaException ex) {
                Logger.getLogger(Lucene.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(Lucene.class.getName()).log(Level.SEVERE, null, ex);
            }
            Searcher searcher = new Searcher();
            List<Bean> printList1 = searcher.searching(s1);
            
            session.setAttribute("printList", printList1);
            
            if(printList1.isEmpty()){
                response.sendRedirect("empty.jsp");
            }
            else{
                response.sendRedirect("result.jsp");
            }
        } catch (ParseException ex) {
            Logger.getLogger(Lucene.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidTokenOffsetsException ex) {
            Logger.getLogger(Lucene.class.getName()).log(Level.SEVERE, null, ex);
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
