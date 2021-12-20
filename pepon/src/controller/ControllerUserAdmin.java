package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.User;
import net.proteanit.sql.DbUtils;

public class ControllerUserAdmin {
	
			//Appel de la connection
			//Connection connect = GetConnection.getConnectionMac();
			Connection connect = GetConnection.getConnectionWindows();
			public static User currentUser ;
			
			public boolean mailExist(String mail) {
				Boolean msg = false;
				try {
					PreparedStatement sql = connect.prepareStatement("SELECT * FROM user WHERE email = ?");
					
					sql.setString(1, mail);
					
					ResultSet rs = sql.executeQuery();
					
					if(!rs.next()) {
						msg = true;
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return msg;
			}
			
			//M�thode pour ajouter un utilisateur 
			public void ajouter(User user) {
				
				try {
					PreparedStatement sql = connect.prepareStatement("INSERT INTO user (nom, prenom, email, mot_passe, statut)"
							+ " VALUES (?,?,?,PASSWORD(?),?)	");
					sql.setString(1, user.getNom());
					sql.setString(2, user.getPrenom());
					sql.setString(3, user.getEmail());
					sql.setString(4, user.getMotPasse());
					sql.setString(5, user.getIdStatut().toString());
					
					sql.executeUpdate();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//Activer le bouton Ajouter quant'il tous les champs sont saisis pour plus de s�curit�
			
			public void activerBtnAjouter( JTextField nom, JTextField prenom, JTextField email, JTextField password, JButton btn) {
				String nom_saisie = nom.getText();
				String prenom_saisie = prenom.getText();
				String email_saisie = email.getText();
				String pass_saisie = password.getText();
				
				if((!nom_saisie.isEmpty()) && (!prenom_saisie.isEmpty()) && (!email_saisie.isEmpty())&& (!pass_saisie.isEmpty())) {
					btn.setEnabled(true);
				}else {
					btn.setEnabled(false);
				}
			}
			
			//M�thode  pour vider les champs
			public void viderChamps(JTextField a,JTextField b,JTextField c,JTextField d,JComboBox status,JTextField f) {
				a.setText("");
				b.setText("");
				c.setText("");
				d.setText("");
				status.setToolTipText("");
				f.requestFocus();		
			}
			
			public void afficherTable(JTable table) {
				
				try {
					//r�cup�ration des info de  table user
					PreparedStatement sql = connect.prepareStatement("SELECT * FROM user");
					// Conversion de la requete en tableau d'objets
					ResultSet rs =sql.executeQuery();
					/*
					 * J'appelle la m�thode setModel pour inserer les objets dans notre table
					 * table = objet Jtable 
					 * j'appelle setModel de l'objet Jtable.
					 * setModel a comme parametre :
					 * DbUtils = la libraire ?
					 * la m�thode resultSetToTableModel modifie la table Model.
					 * cette m�thode prend comme param notre tableau d'objet rs.
					 * 
					 * ca Modifie la variable table de type Jtable avec les instructions resultSetToTableModel
					 *  qui contient le rs
					 */
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			//M�thode pour chercher un utilisateur par id
			public void findById(String id, JTextField nom, JTextField prenom, JTextField email, JComboBox status) {
				try { 
					PreparedStatement sql = connect.prepareStatement("SELECT  nom, prenom, email, statut  FROM user WHERE id_user = ?");
					
					sql.setNString(1, id);
					
					ResultSet rs = sql.executeQuery();
					
					if(rs.next()) {
						//String id = rs.getString(1);
						String name = rs.getString(1);
						String lastname = rs.getString(2);
						String mail = rs.getString(3);
						String statut = rs.getString(4);
						
					
						nom.setText(name);
						prenom.setText(lastname);
						email.setText(mail);
						status.setToolTipText(statut);
					}else {
						nom.setText("");
						prenom.setText("");
						email.setText("");
					}
					
				} catch (SQLException event) {
					// TODO Auto-generated catch block
					event.printStackTrace();
				}
			}
			
			//M�thode pour modifier un utilisateur 
			
				public void modifier( String nom, String prenom, String email, String statu, String id) {
					try {
						PreparedStatement sql = connect.prepareStatement("UPDATE user set nom= ?,prenom = ?,email= ?,statut = ?" 
								+ " where id_user =?");
						sql.setString(1, nom);
						sql.setString(2, prenom);
						sql.setString(3, email);
						sql.setString(4,(String) statu);
						sql.setString(5,id);
						
						sql.executeUpdate();
						JOptionPane.showMessageDialog(null, nom + " "+prenom +" a �t� bien modifi�(e)." );
					} catch (SQLException event) {
						// TODO Auto-generated catch block
						event.printStackTrace();
					}
					
				}
				
				//M�thode pour modifier un utilisateur 
				
				public void supprimer(  String id) {
					try {
						PreparedStatement sql = connect.prepareStatement("delete from user where id_user =?");
						
						sql.setString(1,id);
						
						sql.executeUpdate();
						JOptionPane.showMessageDialog(null,"l'utilisateur qui a l'id n�: "+ id +" a �t� bien supprim�." );
					} catch (SQLException event) {
						// TODO Auto-generated catch block
						event.printStackTrace();
					}
					
				}
}
