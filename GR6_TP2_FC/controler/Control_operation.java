package controler;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import model.MySQL_operation;

import java.sql.*;
import java.time.LocalDateTime;

/**
 * Classe Control_operation
 * 
 * @author Florent et Cyril
 * @version 1.0
 *
 * Cette classe va �crire dans la base de donn�es les informations r�cup�r�es dans le formulaire d'op�ration
 */
public class Control_operation extends HttpServlet{
	
	private static Logger logger = Logger.getLogger(controler.Control_operation.class);
	
	/**
	 * Methode �crivant dans la table operation de la BDD les modifications entr�es dans le formulaire d'op�ration
	 * lors de l'appel de cette classe par une methode GET
	 * Elle cr�� une instance de la classe MySQL_operation qui va permettre d'identifier le conseiller effectuant l'op�ration 
	 * ainsi que d'�crire dans la base de donn�es.
	 * @param rq requ�te entrante
	 * @param rs r�ponse sortante 
	 * @throws ServletException Exception li�e � une erreur lors du traitement de la requ�te 
	 * @throws IOException Exception due � un probl�me li� au flux d'entr�e et/ou de sortie
	 */
	public void doGet(HttpServletRequest rq, HttpServletResponse rs)
	throws ServletException, IOException  {
		PrintWriter out = rs.getWriter();
		logger.debug("Passage dans la servlet");
		try{
		MySQL_operation base = new MySQL_operation();
		LocalDateTime currentTime = LocalDateTime.now();		
		String requeteCompte = rq.getParameter("requeteCompte");
		String requeteCompte2 = rq.getParameter("requeteCompte2");
		String requeteDate = rq.getParameter("requeteDate");		
		int requeteMontant = Integer.parseInt(rq.getParameter("requeteMontant"));		
		String requeteType = rq.getParameter("requeteType");
		String conseiller= base.select("select conseiller from compte where id_compte='"+requeteCompte+"';");				
		String res=null;
		res=base.update("insert into operation (conseiller,compte,date_operation,montant, type_operation) values ('"+conseiller+"','"+requeteCompte+"','"+currentTime+"', -"+requeteMontant+",'"+ requeteType+"');") ;
		res=base.update("insert into operation (conseiller,compte,date_operation,montant, type_operation) values ('"+conseiller+"','"+requeteCompte2+"','"+currentTime+"', "+requeteMontant+",'"+ requeteType+"');") ;
		out.print("<html><header><link rel='stylesheet' href='css/authentification.css' /><header><body>");
		out.println(res);
		out.println("<br>");		
		base.deconnecte();
		}
		catch(ClassNotFoundException e){
			out.print("<html><head><link rel='stylesheet' href='css/authentification.css' /><title>Probleme acces Base de donn�e</title></head><body>");
			out.println("<h1>Veuillez v�rifier vos param�tres d'acces a la base de donn�e</h1>");
		}
		catch(SQLException e){
			out.print("<html><head><link rel='stylesheet' href='css/authentification.css' /><title>Probleme de requete</title></head><body>");
			out.println("<h1>Veuillez v�rifier les param�tres de votre requete</h1>");
		}
		out.println("<a id='retour' href='auth'>Retour</a></body></html>");
	}
	
	/**
	 * M�thode redirigeant vers la m�thode doGet() lors de l'appel de la classe par une m�thode POST
	 * @param rq requ�te entrante
	 * @param rs r�ponse sortante 
	 * @throws ServletException Exception li�e � une erreur lors du traitement de la requ�te 
	 * @throws IOException Exception due � un probl�me li� au flux d'entr�e et/ou de sortie
	 */
	public void doPost(HttpServletRequest rq, HttpServletResponse rs)
	throws ServletException, IOException  {
		doGet(rq,rs);		
	}

}
