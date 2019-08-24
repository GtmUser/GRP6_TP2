package controler;
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
 * Classe Control_modif
 * 
 * @author Florent et Cyril
 * @version 1.0
 *
 * Cette classe va écrire dans la base de données les informations récupérées dans le formulaire de modification du client
 */
public class Control_modif extends HttpServlet{ 
	
	private static Logger logger = Logger.getLogger(controler.Control_modif.class);
	
		/**
		* Methode écrivant dans la table client de la BDD les modifications entrées dans le formulaire de modification
	 	* et rajoutant une ligne dans la table modif_client lors de l'appel de cette classe par une methode GET
	 	* Elle créé une instance de la classe MySQL_modif qui va permettre d'identifier le conseiller effectuant les modifications
	 	* ainsi que d'écrire dans la base de données.
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
			
			MySQL_modif base = new MySQL_modif();
			LocalDateTime currentTime = LocalDateTime.now();
			out.println("<html><header><link rel='stylesheet' href='css/authentification.css' /></header><body>");
			out.println("<h1>Résumé des modifications</h1>");
			
			String requetePrenom = rq.getParameter("requetePrenom");
			String requeteId = rq.getParameter("idclient");
			String conseiller= base.select("select conseiller from compte where client='"+requeteId+"';");
			String resPrenom=null;
			String ancienPrenom = null;
			if (requetePrenom != "") {
			ancienPrenom=base.select("select prenom from client where id_client='"+requeteId+"';");
			resPrenom=base.update("update client set prenom='"+requetePrenom+"' where id_client='"+requeteId+"';");
			resPrenom=base.update("insert into modif_client(conseiller,client,date_modif_client,type_modif,ancienne_valeur,nouvelle_valeur) values('"+conseiller+"','"+requeteId+"','"+currentTime+"','prénom','"+ancienPrenom+"','"+requetePrenom+"');");
			out.println("<h2>La valeur "+requetePrenom+" du prénom a bien été entrée.</h2>");
			}
			String requeteNom = rq.getParameter("requeteNom");
			String resNom=null;
			String ancienNom = null;
			if (requeteNom != "") {
			ancienNom=base.select("select nom from client where id_client='"+requeteId+"';");
			resNom=base.update("update client set nom='"+requeteNom+"' where id_client='"+requeteId+"';");
			resNom=base.update("insert into modif_client(conseiller,client,date_modif_client,type_modif,ancienne_valeur,nouvelle_valeur) values('"+conseiller+"','"+requeteId+"','"+currentTime+"','nom','"+ancienNom+"','"+requeteNom+"');");
			out.println("<h2>La valeur "+requeteNom+" du nom a bien été entrée.</h2>");
			}
			String requeteEmail = rq.getParameter("requeteEmail");
			String resEmail=null;
			String ancienEmail = null;
			if (requeteEmail != "") {
			ancienEmail=base.select("select email from client where id_client='"+requeteId+"';");
			resEmail=base.update("update client set email='"+requeteEmail+"' where id_client='"+requeteId+"';");
			resEmail=base.update("insert into modif_client(conseiller,client,date_modif_client,type_modif,ancienne_valeur,nouvelle_valeur) values('"+conseiller+"','"+requeteId+"','"+currentTime+"','email','"+ancienEmail+"','"+requeteEmail+"');");
			out.println("<h2>La valeur "+requeteEmail+" de l'email a bien été entrée.</h2>");
			}
			String requeteAdresse = rq.getParameter("requeteAdresse");
			String resAdresse=null;
			String ancienAdresse = null;
			if (requeteAdresse != "") {
			ancienAdresse=base.select("select adresse from client where id_client='"+requeteId+"';");
			resAdresse=base.update("update client set adresse='"+requeteAdresse+"' where id_client='"+requeteId+"';");
			resAdresse=base.update("insert into modif_client(conseiller,client,date_modif_client,type_modif,ancienne_valeur,nouvelle_valeur) values('"+conseiller+"','"+requeteId+"','"+currentTime+"','adresse','"+ancienAdresse+"','"+requeteAdresse+"');");
			out.println("<h2>La valeur "+requeteAdresse+" de l'adresse a bien été entrée.</h2>");
			}
			String requeteVille = rq.getParameter("requeteVille");
			String resVille=null;
			String ancienVille = null;
			if (requeteVille != "") {
			ancienVille=base.select("select ville from client where id_client='"+requeteId+"';");
			resVille=base.update("update client set ville='"+requeteVille+"' where id_client='"+requeteId+"';");
			resVille=base.update("insert into modif_client(conseiller,client,date_modif_client,type_modif,ancienne_valeur,nouvelle_valeur) values('"+conseiller+"','"+requeteId+"','"+currentTime+"','ville','"+ancienVille+"','"+requeteVille+"');");
			out.println("<h2>La valeur "+requeteVille+" de la ville a bien été entrée.</h2>");
			}
			String requeteCP = rq.getParameter("requeteCP");
			String resCP=null;
			String ancienCP = null;
			if (requeteCP != "") {
			ancienCP=base.select("select cp from client where id_client='"+requeteId+"';");
			resCP=base.update("update client set cp='"+requeteCP+"' where id_client='"+requeteId+"';");
			resCP=base.update("insert into modif_client(conseiller,client,date_modif_client,type_modif,ancienne_valeur,nouvelle_valeur) values('"+conseiller+"','"+requeteId+"','"+currentTime+"','code postal','"+ancienCP+"','"+requeteCP+"');");
			out.println("<h2>La valeur "+requeteCP+" du code postal a bien été entrée.</h2>");
			base.deconnecte();
			}
			}
			catch(ClassNotFoundException e){
				out.print("<html><head><link rel='stylesheet' href='css/authentification.css' /><title>Probleme acces Base de donnée</title></head><body>");
				out.println("<h1>Veuillez vérifier vos paramètres d'acces a la base de donnée</h1>");
			}
			catch(SQLException e){
				out.print("<html><head><link rel='stylesheet' href='css/authentification.css' /><title>Probleme de requete</title></head><body>");
				out.println("<h1>Veuillez vérifier les paramètres de votre requete</h1>");
			}		
			out.println("<a id='retour' href='/TP2/auth'>Retour</a></body></html>");
		}
		
		/**
		 * Méthode redirigeant vers la méthode doGet() lors de l'appel de la classe par une méthode POST
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

	

