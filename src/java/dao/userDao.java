package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.userBean;
import dao.dbconnection.Connexion;;
public class userDao {
	//Cr�er une variable de connexion
	private Connection conn;
	public userDao(){
		// ouvrir une connexion
		conn = Connexion.getConnection();
	}
	//D�finit la m�thode addUser qui permet d'ajouter un nouveau utilisateur
	//elle admet en argumen un variavle de type userBean ,
	//La classe qui repr�sente tout les utilisateurs
	public void addUser(userBean utilisateur){
		try {
			//La requ�te sql qui permet d'ajouter un enregistrement
			String sql = "INSERT INTO utilisateur(ID,NOM,PRENOM)" + "VALUES(?,?,?)";
			//Une methode qui pr�pare la requ�te sql
			PreparedStatement ps = conn.prepareStatement(sql);
			/*Les 3 instructions suivantes servent a remplacer les 3 '?' 
			 * Les num�rps 1,2 et 3 donnent a la variable laquelle parmi les 
			 * '?' doit la remplacer ,1 c'est-a-dire que la variable remplace le 1er 
			 */
			ps.setInt(1, utilisateur.getID());
			ps.setString(2,utilisateur.getNom());
			ps.setString(3,utilisateur.getPrenom());
			/* ex�cuter la requ�te */
                        ps.executeUpdate();
			/* cette partie pour g�rer les exceptions */
		} catch (SQLException e) {
                    System.out.println(" inserer execption");
			e.printStackTrace();
		}
	}
	/* D�finit la m�thode remove user qui permet de supprimer un utilisateur,
	 * elle admet en argument un variable de type userBean la classe qui repr�sente 
	 * tout les utilisateurs
	 *  */
	public void removeUser(int userId){
		try {
			/* La requ�te sql qui permet de supprimer un enregistrement  */
				String sql = "DELETE FROM utilisateur WHERE ID=?";
			//Une methode qui prepare la requ�te sql
				PreparedStatement ps = conn.prepareStatement(sql);
			// La m�me chose: l'instruction suivante qui remplace la '?' 
				ps.setInt(1, userId);
			// ex�cuter la requ�te 
					ps.executeUpdate();
			// g�rer les exceptions	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//La methode qui permet de modifier un enregistrement
	public void editUser(userBean utilisateur){
		try {
		//La requ�te sql qui permet de modifier un enregistrement
			String sql = "UPDATE utilisateur set nom=? ,prenom=? where id=?;";
		//toujours en fait appelle a la methode PrepareStatement
		//pour pr�parer la requ�te sql et permettre de remplacer les '?' par 
		//Les variables correspondantes
			PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, utilisateur.getNom());
				ps.setString(2, utilisateur.getPrenom());
				ps.setInt(3, utilisateur.getID());
			// ex�cuter la requ�te 
				ps.executeUpdate();
			//g�rer les exceptions
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/* Maintenant on a vu les 3 methodes ajouter, supprimer, modifier  
	 * (insert, delete, update) pour terminer le terme CRUD, il reste un seule 
	 * methode (read) qui permet de lire les enregistrement  
	 * 
	 */
	/* le methode suivant permet de r�cup�rer tous les enregistrement  */
	public List getAllUsers(){
		/* on declare une variable de type liste pour mettre  
		 * les enregisrement de la table utilisateur dans cette liste
		 * */
		List liste_utilisateur = new ArrayList();
			try {
				//La requ�te sql qui permet de permet de lire les enregistrement
					String sql = " 	 	SELECT  se.direccion, cl.idClase, cl.fecha, cl.horaInicio, cl.HoraFin, (cl.cupos-count(re.clase_idClase)) cupos\n" +
"	FROM clase AS cl INNER JOIN reserva AS re ON cl.idClase = re.clase_idClase\n" +
"	INNER JOIN sede AS se ON se.idSede = cl.sede_idSede\n" +
"\n" +
"	GROUP BY cl.idClase\n" +
"	HAVING cupos > 0;";
				//on fait appel a la m�thode PrepareStatement pour pr�parer la requ�te sql
					PreparedStatement ps = conn.prepareStatement(sql);
				//on ex�cute la requ�te et on effecte le resultat dans la variable rs
					ResultSet rs = ps.executeQuery();
				//dans cette partie on r�cup�re les enregistrements et on les mettent dans la liste
				//La boucle while v�rifie s'il ya des enregistrement dans la variables rs
				//S'il ye en a, elle, parcourt tout les enregistrement un par un
					while(rs.next()){
				//Pour chaque enregistrement on cr�e une instance de la classe userBean
						userBean utilisateur = new userBean();
						utilisateur.setID(rs.getInt("idClase"));
						utilisateur.setNom(rs.getString("direccion"));
						utilisateur.setPrenom(rs.getString("fecha"));
                                                utilisateur.setCupos(rs.getInt("cupos"));
                                                utilisateur.setHoraInicio(rs.getString("horaInicio"));
                                                utilisateur.setHoraFin(rs.getString("horaFin"));
						/* on met cette instance dans la liste */
						liste_utilisateur.add(utilisateur);
					}
					/* g�rer les excepions */
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return liste_utilisateur;
	}
	//cette methode permet de chercher un utilisateur 
		public userBean getUserById(int userId){
			//On cr�e une instance pour lui affecter apr�s les informations 
			// r�cup�r�es par la recherche 
			userBean utilisateur = new userBean();
			try {
				String sql = "SELECT * FROM utilisateur WHERE id=?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, userId);
				ResultSet rs = ps.executeQuery();
					if(rs.next()){
						utilisateur.setID(rs.getInt("id"));
						utilisateur.setNom(rs.getString("nom"));
						utilisateur.setPrenom(rs.getString("prenom"));
					}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return utilisateur;
		}
}










