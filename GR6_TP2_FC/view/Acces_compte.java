package view;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import model.MySQL_compte;

/**
* Classe Acces_compte
* 
* @author Florent et Cyril
* @version 1.0
*
* Cette classe affiche la liste des comptes reli�s au client de la ligne du bouton sur lequel on a cliqu�
*/
public class Acces_compte extends HttpServlet{
	
	private static Logger logger = Logger.getLogger(view.Acces_compte.class);
	
	/**
	 * M�thode effectuant l'affichage des comptes lors de l'appel de cette classe par une methode GET
	 * Elle cr�� une instance de la classe MySQL_compte qui va rechercher les parametres de la table compte dans la BDD
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
		MySQL_compte base = new MySQL_compte();
		String requeteId = rq.getParameter("plop");
		out.println("<html><header><link rel='stylesheet' href='css/authentification.css' /></header><body>");
		out.println("<h1>Liste des comptes</h1>");
		out.println(base.select("select id_compte,type_compte,date_ouverture,categorie_client,taux_remuneration,autorisation_decouvert,type_carte from compte where client="+requeteId+";"));
		out.println("<br>");
		base.deconnecte();
		}
		catch(Exception e){
			out.print("<html><head><link rel='stylesheet' href='css/authentification.css' /><title>Probleme de requete</title></head><body>");
			out.println("<h1>Veuillez v�rifier les param�tres de votre requete</h1>");
		}
		out.println("<a id='retour' href='auth'>Retour</a></body></html>");
	}
	
	/**
	 * M�thode redirigeant vers la m�thode doGet() lors de l'appel de la classe par un methode POST
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