

package view;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import model.MySQL_accueil;

/**
* Classe Acces_accueil
* 
* @author Florent et Cyril
* @version 1.0
*
* Cette classe affiche la liste des clients reliés au conseiller qui s'est connecté
*/
public class Acces_accueil extends HttpServlet{
	
	private static Logger logger = Logger.getLogger(view.Acces_accueil.class);
	
	/**
	 * Méthode effectuant l'affichage des clients lors de l'appel de cette classe par une methode GET
	 * Elle créé une instance de la classe MySQL_accueil qui va rechercher les parametres de la table client dans la BDD
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
		MySQL_accueil base = new MySQL_accueil();
		String requeteId = rq.getParameter("identifiant");	
		out.println("<html><header><link rel='stylesheet' href='css/authentification.css' /></header><body>");
		out.println("<h1>Liste des clients</h1>");
		out.println(base.select("select id_client,prenom,nom,adresse,cp,ville,email,telephone from client where conseiller="+rq.getUserPrincipal().getName()+";"));
		out.println("<br>");
		base.deconnecte();
		}
		catch(Exception e){
			out.print("<html><head><link rel='stylesheet' href='css/authentification.css' /><title>Probleme de requete</title></head><body>");
			out.println("<h1>Veuillez vérifier les paramètres de votre requete</h1>");
		}	
		out.println("</body></html>");
	}
	
	/**
	 * Méthode redirigeant vers la method doGet() lors de l'appel de la classe par une méthode POST
	 * @param rq requête entrante
	 * @param rs réponse sortante 
	 */
	public void doPost(HttpServletRequest rq, HttpServletResponse rs)
	throws ServletException, IOException  {
		doGet(rq,rs);		
	}
}