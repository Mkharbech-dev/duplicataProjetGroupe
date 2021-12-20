package model;

public class User {

	private String nom;
	private String prenom;
	private String email;
	private String motPasse;
	private String idStatut;
	private int idUser;
	
	public User(String nom, String prenom, String email, String motPasse, String idStatut) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motPasse = motPasse;
		this.idStatut = idStatut;
	}

	
	public User(String nom, String prenom, String email, String motPasse) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motPasse = motPasse;
	}


	public User(String nom, String prenom, String email, String motPasse, String idStatut, int idUser) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motPasse = motPasse;
		this.idStatut = idStatut;
		this.idUser = idUser;
	}


	public String getNom() {
		return nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public String getEmail() {
		return email;
	}


	public String getMotPasse() {
		return motPasse;
	}


	public String getIdStatut() {
		return idStatut;
	}


	public int getIdUser() {
		return idUser;
	}




	
	
}
