package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Article;
import model.PanelModelArticle;
import model.VarStatic;
import vue.PanelClientArticleSelect;

public class ControllerClientArticle {

	Connection connect = GetConnection.getConnectionWindows();
	//Connection connect = GetConnection.getConnectionMac();

	public void modifPanArticle(PanelClientArticleSelect panelArticle) {
		ArticleDao artDao = new ArticleDao();
		
		if(VarStatic.IdArticleStatic != 0) {
			artDao.readIdArticle(VarStatic.IdArticleStatic);
			
			panelArticle.setLabelNom(artDao.readIdArticle(VarStatic.IdArticleStatic).getNomArticle());
			panelArticle.setLabelNomCategorie(artDao.idCategorieToString(artDao.readIdArticle(VarStatic.IdArticleStatic).getIdCategorie()));
			panelArticle.setLabelNutripoint(String.valueOf(artDao.readIdArticle(VarStatic.IdArticleStatic).getNutripoint()) + " /100");
			panelArticle.setLabelPrix(Double.toString(artDao.readIdArticle(VarStatic.IdArticleStatic).getPrix()) +" �");
			
		}
	}
	
	public int creerCommande() {

		if(VarStatic.idCommandeStatic == 0) {

			int user = VarStatic.currentUserStatic.getIdUser();
			try {
				PreparedStatement req = connect.prepareStatement("INSERT INTO commande (id_commande, prix_total, date_achat, id_user) VALUES (null, null, now(), ?)");
				req.setInt(1, user);
				
				req.executeUpdate();
				
				//cree un count
				PreparedStatement sql = connect.prepareStatement("SELECT MAX(id_commande) FROM commande");
				sql.setInt(1, user);
				
				ResultSet rs = sql.executeQuery();
				
				System.out.println(rs + " rs controlArt");
				while(rs.next()) {
					//VarStatic.idCommandeStatic = rs.getInt("id_commande");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return VarStatic.idCommandeStatic;
	}
	
	public void ajouterDetail(int idCommande, int idArticle, int quantiteArticle) {
		
		//panier id_commande / id_article / quantite
		try {
			PreparedStatement req = connect.prepareStatement("INSERT INTO panier (id_commande, id_article, quantite) VALUE (?, ?, ?)");
			req.setInt(1, idCommande);
			req.setInt(2, idArticle);
			req.setInt(3, quantiteArticle);
			
			req.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
