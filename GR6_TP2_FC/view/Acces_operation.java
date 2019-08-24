package view;
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
* Classe Acces_operation
* 
* @author Florent et Cyril
* @version 1.0
*
* Cette classe affiche le formulaire permettant de r�aliser une op�ration * 
*/
public class Acces_operation extends HttpServlet{
	
	private static Logger logger = Logger.getLogger(view.Acces_operation.class);
	
	/**
	 * M�thode effectuant l'affichage du formulaire permettant de r�aliser une op�ration 
	 * lors de l'appel de cette classe par une methode GET
	 * Elle cr�e une instance de la classe MySQL_op�ration qui permet d'identifier le client 
	 * poss�dant le compte depuis lequel on veut effectuer l'op�ration 
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
		String requeteId = rq.getParameter("vireclient");
		String titi=base.select("select id_compte from compte where client='"+requeteId+"' ORDER BY  id_compte LIMIT 1;" );
		String pioupiou=base.select("select id_compte from compte where client='"+requeteId+"' ORDER BY  id_compte DESC LIMIT  1");		
		
		out.println("<html>");
		out.println("<head><link rel='stylesheet' href='css/authentification.css' />");
		out.println("</head>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>Virement</h1>");		
		out.println("<h2>Veuillez saisir votre requete</h2><br>");
		
		out.println("<form action='operation2' method='GET' name='form1'>");
		out.println("Compte d�bit�<br>");
		out.println("<input type='radio' name='requeteCompte' value='"+titi+"' id='compte1' checked='checked'/>");
		out.println("<label for='compte1'>"+titi+"</label><br>");
		if (!titi.equals(pioupiou)){
		out.println("<input type='radio' name='requeteCompte' value='"+pioupiou+"' id='compte2'>");
		out.println("<label for='compte2'>"+pioupiou+"</label><br>");}
		out.println("<br><input type='number' required='required' name='requeteMontant' placeholder='Montant transf�r�' size='40'><br><br>");
		out.println("<input type='text' required='required' name='requeteType' placeholder='Type operation' size='40'><br><br>");
		out.println("<input type='text' required='required' name='requeteCompte2' placeholder='Identifiant compte cr�dit�' size='40'><br><br>");
		out.println("<input type='submit' name='OK' value='Executer'><br>");
		out.println("</form><br><hr><br>");
		out.println("</center>");
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
