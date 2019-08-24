package model;
import java.sql.*;

import org.apache.log4j.Logger;

/**
 * Classe MySQL_compte
 * 
 * @author Florent et Cyril
 * @version 1.0
 *
 * Cette classe va rechercher les informations necessaires à l'affichage de la page d'affichage des comptes dans la base de données
 */
public class MySQL_compte {
	
	private static Logger logger = Logger.getLogger(model.MySQL_compte.class);
	
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
	 */
	public MySQL_compte()  {
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
	 */
	public MySQL_compte(String adresse,String base,String user,String passwd) {
		this.resultat=true;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://"+adresse+"/"+base,user,passwd);
			stmt = con.createStatement();
			}
			catch(Exception e){
				this.resultat=false;
			}		
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
	public int update(String ordre)  {
		try {
			return stmt.executeUpdate(ordre);
		} catch (SQLException e) {return -1;}
	}
	
	/**
	 * Méthode permettant la selection de données dans la base de données
	 * @param ordre ordre SQL
	 * @return retour table contenant la requête
	 */
	public String select(String ordre)  {
		String retour = null;
		try{
		rs = stmt.executeQuery(ordre);
		if( rs != null){
			rsmd = rs.getMetaData();
			retour="<br><center><table bgcolor='#219c30' width='100%' border='1'>";
			retour += "<tr>";
			for (int a=1;a<= rsmd.getColumnCount();a++){
				retour += "<th>"+rsmd.getColumnLabel(a)+"</th>";
			}
			retour += "</tr>";
			int nombre = 0;
			while(rs.next()){
				retour += "<tr>";
				String color = (nombre==0)?"'#52b6de'":"'#588cee'";
				String b= rs.getString(1);
				for (int a=1;a<= rsmd.getColumnCount();a++){
					retour += "<td bgcolor="+color+"><font color='black'><center>"+rs.getString(a)+"</center></font></td>";
				}
				retour += "</tr>";
				nombre=(nombre==1)?0:1;
			}
			retour += "</table></center>";
		}else{
			retour = "";
		}}
		catch(Exception e){
			retour="gross katastrof";
		}
		return retour;
	}

	public boolean isResultat() {
		return resultat;
	}
}
