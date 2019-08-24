package model;
import java.sql.*;

import org.apache.log4j.Logger;

/**
 * Classe MySQL_operation
 * 
 * @author Florent et Cyril
 * @version 1.0
 *
 * Cette classe va rechercher les informations necessaires � l'affichage de la page de r�alisation d'op�ration
 *  dans la base de donn�es
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
	 * R�sultat de l'ordre SQL envoy� par le statement
	 */
	private ResultSet rs = null;
	
	/**
	 * Meta donn�es du resultat de l'ordre SQL envoy� par le statement
	 */
	private ResultSetMetaData rsmd = null;
	
	/**
	 * Bool�en servant � identifier les exceptions
	 */
	private boolean resultat = false;
	
	/**
	 * M�thode permettant la connexion � la base de donn�es lors de l'appel de la classe sans param�tres
	 * @throws ClassNotFoundException Exception li�e au fait que la classe n'a pas �t� trouv�e
	 * @throws SQLException Exception li�e � une erreur de requ�te SQL
	 */
	public MySQL_operation() throws ClassNotFoundException,SQLException {
		this.resultat=true;
		logger.debug("Connexion � la BDD");
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
	 * M�thode permettant la connexion � la base de donn�es lors de l'appel de la classe avec param�tres
	 * @param adresse adresse de connexion
	 * @param base table utilis�e
	 * @param user identifiant pour se connecter
	 * @param passwd mot de passe pour se connecter
	 * @throws ClassNotFoundException Exception li�e au fait que la classe n'a pas �t� trouv�e
	 * @throws SQLException Exception li�e � une erreur de requ�te SQL
	 */	
	public MySQL_operation(String adresse,String base,String user,String passwd) throws ClassNotFoundException,SQLException {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://"+adresse+"/"+base,user,passwd);
			stmt = con.createStatement();
	}
	
	/**
	 * M�thode permettant la d�connexion de la base de donn�es
	 */
	public void deconnecte() {
		if(con != null){
			try {con.close();} catch (SQLException e){}
		}
	}
	
	/**
	 * M�thode permettant la mise � jour de la base de donn�es 
	 * @param ordre ordre SQL
	 * @return stmt retourne l'ordre
	 */
	public String update(String ordre) throws SQLException {
		int a= stmt.executeUpdate(ordre);
		return("<center><br><br><h1><font color=black>Donn�es mises � jour</font></h1></center><br>");
	}
	
	/**
	 * M�thode permettant la selection de donn�es dans la base de donn�es
	 * @param ordre ordre SQL
	 * @return retour retourne le contenu de la requ�te
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
