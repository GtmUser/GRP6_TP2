package model;
import java.sql.*;

import org.apache.log4j.Logger;

/**
 * Classe MySQL_operation
 * 
 * @author Florent et Cyril
 * @version 1.0
 *
 * Cette classe va rechercher les informations necessaires à l'affichage de la page de réalisation d'opération
 *  dans la base de données
 */
public class MySQL_operation {
	
	private static Logger logger = Logger.getLogger(model.MySQL_operation.class);
	
	/**
	 * Nom de la connexion
	 */
	private Connection con = null;
	
	/**
	 * id du Nom du statement de la connexion
	 */
	private Statement stmt = null;
	
	/**
	 * Résultat de l'ordre SQL envoyé par le statement
	 */
	private ResultSet rs = null;
	
	/**
	 * Meta données du resultat de l'ordre SQL envoyé par le statement
	 */
	private ResultSetMetaData rsmd = null;
	
	/**
	 * Booléen servant à identifier les exceptions
	 */
	private boolean resultat = false;
	
	/**
	 * Méthode permettant la connexion à la base de données lors de l'appel de la classe sans paramètres
	 * @throws ClassNotFoundException Exception liée au fait que la classe n'a pas été trouvée
	 * @throws SQLException Exception liée à une erreur de requête SQL
	 */
	public MySQL_operation() throws ClassNotFoundException,SQLException {
		this.resultat=true;
		logger.debug("Connexion à la BDD");
		try{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://192.168.1.98/pro_grp6","aud6","password");
		stmt = con.createStatement();
		}
		catch(Exception e){
			this.resultat=false;
		}
	}
	
	/**
	 * Méthode permettant la connexion à la base de données lors de l'appel de la classe avec paramètres
	 * @param adresse adresse de connexion
	 * @param base table utilisée
	 * @param user identifiant pour se connecter
	 * @param passwd mot de passe pour se connecter
	 * @throws ClassNotFoundException Exception liée au fait que la classe n'a pas été trouvée
	 * @throws SQLException Exception liée à une erreur de requête SQL
	 */	
	public MySQL_operation(String adresse,String base,String user,String passwd) throws ClassNotFoundException,SQLException {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://"+adresse+"/"+base,user,passwd);
			stmt = con.createStatement();
	}
	
	/**
	 * Méthode permettant la déconnexion de la base de données
	 */
	public void deconnecte() {
		if(con != null){
			try {con.close();} catch (SQLException e){}
		}
	}
	
	/**
	 * Méthode permettant la mise à jour de la base de données 
	 * @param ordre ordre SQL
	 * @return stmt retourne l'ordre
	 */
	public String update(String ordre) throws SQLException {
		int a= stmt.executeUpdate(ordre);
		return("<center><br><br><h1><font color=black>Données mises à jour</font></h1></center><br>");
	}
	
	/**
	 * Méthode permettant la selection de données dans la base de données
	 * @param ordre ordre SQL
	 * @return retour retourne le contenu de la requête
	 */
	public String select(String ordre) throws SQLException {
		String retour = null;
		rs = stmt.executeQuery(ordre);
		if( rs != null){
			rsmd = rs.getMetaData();
			while(rs.next()){
				for (int a=1;a<= rsmd.getColumnCount();a++){
					retour = rs.getString(a);
				}				
			}
		}else{
			retour = "";
		}
		return retour;
	}	
	public boolean isResultat() {
		return resultat;
	}
}
