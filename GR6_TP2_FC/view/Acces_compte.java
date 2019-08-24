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
* Cette classe affiche la liste des comptes reliés au client de la ligne du bouton sur lequel on a cliqué
*/
public class Acces_compte extends HttpServlet{
	
	private static Logger logger = Logger.getLogger(view.Acces_compte.class);
	
	/**
	 * Méthode effectuant l'affichage des comptes lors de l'appel de cette classe par une methode GET
	 * Elle créé une instance de la classe MySQL_compte qui va rechercher les parametres de la table compte dans la BDD
	 * @param rq requête entrante
	 * @param rs réponse sortante 
	 * @throws ServletException Exception liée à une erreur lors du traitement de la requête 
	 * @throws IOException Exception due à un problème lié au flux d'entrée et/ou de sortie
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
			out.println("<h1>Veuillez vérifier les paramètres de votre requete</h1>");
		}
		out.println("<a id='retour' href='auth'>Retour</a></body></html>");
	}
	
	/**
	 * Méthode redirigeant vers la méthode doGet() lors de l'appel de la classe par un methode POST
	 * @param rq requête entrante
	 * @param rs réponse sortante 
	 * @throws ServletException Exception liée à une erreur lors du traitement de la requête 
	 * @throws IOException Exception due à un problème lié au flux d'entrée et/ou de sortie
	 */
	public void doPost(HttpServletRequest rq, HttpServletResponse rs)
	throws ServletException, IOException  {
		doGet(rq,rs);		
	}
}