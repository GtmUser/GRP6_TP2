package view;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;

import model.MySQL_modif;

/**
* Classe Acces_form_modif
* 
* @author Florent et Cyril
* @version 1.0
*
* Cette classe affiche le formulaire permettant de modifier les informations du client * 
*/
public class Acces_form_modif extends HttpServlet{
	
	private static Logger logger = Logger.getLogger(view.Acces_form_modif.class);
	
	/**
	 * M�thode effectuant l'affichage du formulaire de modification des donn�es clients lors de l'appel de cette classe par une m�thode GET
	 * Elle cr��e une instance de la classe MySQL_modif qui permet d'identifier le client selectionn�
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
			MySQL_modif base = new MySQL_modif();
			LocalDateTime currentTime = LocalDateTime.now();
			String requeteId = rq.getParameter("modifclient");			
			out.print("<html>");
			out.print("<head><link rel='stylesheet' href='css/authentification.css' />");
			out.print("</head>");
			out.print("<body>");
			out.println("<h1>Modification des informations du client</h1>");
			out.print("<hr><center><h2>Veuillez saisir votre requete</h2>");

			out.print("<form action='MySQL' method='POST' name='form1'>");
			out.print("<input type='hidden' name='idclient' value='"+requeteId+"'><br><br>");
			out.print("<input type='text' name='requetePrenom' placeholder='pr�nom' size='40'><br><br>");
			out.print("<input type='text' name='requeteNom' placeholder='nom' size='40'><br><br>");
			out.print("<input type='text' name='requeteAdresse' placeholder='adresse' size='40'><br><br>");
			out.print("<input type='text' name='requeteCP' placeholder='code postal' size='40'><br><br>");
			out.print("<input type='text' name='requeteVille' placeholder='ville' size='40'><br><br>");
			out.print("<input type='text' name='requeteEmail' placeholder='email' size='40'><br><br>");			
			out.print("<input type='submit' name='OK' value='Executer'><br>");
			out.print("</form><br><hr><br>");
			out.print("</center>");
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
