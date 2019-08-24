

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
* Cette classe affiche la liste des clients reli�s au conseiller qui s'est connect�
*/
public class Acces_accueil extends HttpServlet{
	
	private static Logger logger = Logger.getLogger(view.Acces_accueil.class);
	
	/**
	 * M�thode effectuant l'affichage des clients lors de l'appel de cette classe par une methode GET
	 * Elle cr�� une instance de la classe MySQL_accueil qui va rechercher les parametres de la table client dans la BDD
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
			out.println("<h1>Veuillez v�rifier les param�tres de votre requete</h1>");
		}	
		out.println("</body></html>");
	}
	
	/**
	 * M�thode redirigeant vers la method doGet() lors de l'appel de la classe par une m�thode POST
	 * @param rq requ�te entrante
	 * @param rs r�ponse sortante 
	 */
	public void doPost(HttpServletRequest rq, HttpServletResponse rs)
	throws ServletException, IOException  {
		doGet(rq,rs);		
	}
}