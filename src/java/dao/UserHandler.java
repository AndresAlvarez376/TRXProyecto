package dao;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.userDao;
import dao.userBean;
public class UserHandler extends HttpServlet {
	private static final String UserRecord = null;
	private static String INSERT = "/AjouterUtilisateur.jsp";//variable qui indique l'URL
	private static String edit = "/ModifierUtilisateur.jsp";
	private static String delete = "/SupprimerUtilisateur.jsp";
	private static String liste = "/AfficherLesUtlisateurs.jsp";
	private userDao dao;//variable qui le type de userDao
		public UserHandler() {
			super();
			dao = new userDao();	
		}
		/*D�claration de m�thode doGet*/
		/*La m�thode doGet permet de r�cup�rer les informations */
		protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
			//Une variable utilis� a la fin pour la redirection
			String redirect = "";
			//On r�cup�re la valeur de param�tres
			//On r�cup�re la valeur de paramm�tres ID
			String uIdClase = request.getParameter("idClase");
                        String uIdUsuario = request.getParameter("idUsuario");
			//On r�cup�re la valeur du paramm�tre action qui prend les valeurs
			//insert,edit,editForm,listUser ou delete les cas que nous allons trait� par la suite
			String action = request.getParameter("action");
				// Le 1ere cas ou l'action est insert : ajoute un nouveau utilisateur 
			//On v�rifie que la variable uId est <> de null
			//Et l'action est 'insert'
			if(  uIdUsuario != null && uIdClase != null && action.equalsIgnoreCase("insert")){
				int idClase = Integer.parseInt(uIdClase);
                                int idUsuario = Integer.parseInt(uIdUsuario);
			/*
			 * On cr�e une instance de la classe userBean
			 * qui contient tous les attributs ID nom prenom
			 * */
				ReservaBean utilisateur = new ReservaBean();
			/*
			 * On affecte les valeurs retenues par la m�thode doGet au
			 * attributs de l'instance
			 * */
				System.out.println("idClase : " + idClase);
                                System.out.println("idUsuario : " + idUsuario  );
                                utilisateur.setIdClase(idClase);
                                utilisateur.setIdUsuario(idUsuario);
                               
                                
			/*
			 * On fait appel a la m�thode addUser da la classe userDao.java
			 * */
            		dao.addUser(utilisateur);
			  redirect = liste;
				request.setAttribute("utilisateurs",dao.getAllUsers());
				System.out.println("le utilisateur est bien cr�e");
			}
                        
			else if(action.equalsIgnoreCase("delete")){
				String utilistaeurId = request.getParameter("ID");
				int uid = Integer.parseInt(utilistaeurId);
				dao.removeUser(uid);
				redirect = liste;
				request.setAttribute("utilisateurs",dao.getAllUsers());
				System.out.println("utilisateurs est bien supprimer");
			}
			/*elle fait seulement la redirection vers une page jsp ou il y'a
				 * le formulaire de modification 
				 * */
			else if(action.equalsIgnoreCase("editForm")){
                                request.setAttribute("id", request.getParameter("ID"));
                                redirect = edit;
			}
			/*Le 3�me cas l'action est modifier*/
			/*La m�me chose que la m�thode pr�c�dente sauf que cette fois 
			 * on fait appel � une autre m�thode qui fait la modification 
			 * C'est editUser(), qui nous avons deja cr�e dans la classe userDao
			 *  */
			else if(action.equalsIgnoreCase("edit")){
				/*On r�cup�re l'ID de l'utilisateur qui peut modifier
				 * */
				String utilisateurId = request.getParameter("id");
				//On le tronsforme a une variable de type entier (cast)
				int uid = Integer.parseInt(utilisateurId);
				/*Apr�s on cr�e une inctance dans lequelles on va affecter nouveaux modifications
				 * */
				userBean utilisateur = new userBean();
				//On affecte les informations
					utilisateur.setID(uid);
					utilisateur.setNom(request.getParameter("nom"));
					utilisateur.setPrenom(request.getParameter("prenom")); 
				/*On fait appel � la m�thode qui permet de modifier un enregistrement et on 
				 *donne comme argument l'instance que nous avons cr�e
				 * */
					dao.editUser(utilisateur);
					request.setAttribute("utilisateur", utilisateur);
					redirect = liste;
					System.out.println("utilisateur est bien modifier");
			}
			/* Le cas on fait appel a la liste des utilisateurs */
			else if(action.equalsIgnoreCase("ListUser")){
				// redirect = UserRecord;
				request.setAttribute("utilisateurs", dao.getAllUsers());
			}else{
				redirect = INSERT;
			}
			/*	request Dispatcher
			 * 	1- requ�te : c'est le requ�te qui arrive par le client  
			 *  2- dispatcher : la redirection
			 *  Donc c'est la redirection vers les pages associ�es apr�s le traitement
			 * */
			/* La variable redirect qu'on utilise c'est � la fin de chaque cas et on la met dans l'url de la page ou on veut la redirection
			 * */
			RequestDispatcher rd = request.getRequestDispatcher(redirect);
							  rd.forward(request, response);
		}
		protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException {
			doGet(request,response);
		}
}

























