package model;

import java.util.ArrayList;

public class Classe {

    private String              nom;
    private ArrayList<Attribut> attributs;
    private ArrayList<Methode>  methodes;
    private ArrayList<Relation> relations;
    
    private ArrayList<ClassProperty> properties;
    
    public Classe(String nom) {
        super();
        this.nom = nom;
        attributs = new ArrayList<Attribut>();
        methodes = new ArrayList<Methode>();
        relations = new ArrayList<Relation>();
    }
    
	/**
     * Represente une classe UML
     *
     * @param nom
     * @param attributs
     * @param methodes
     * @param relations
     */
    

    public String getNom() {

		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ArrayList<Attribut> getAttributs() {
		return attributs;
	}

	public void setAttributs(ArrayList<Attribut> attributs) {
		this.attributs = attributs;
	}

	public ArrayList<Methode> getMethodes() {
		return methodes;
	}

	public void setMethodes(ArrayList<Methode> methodes) {
		this.methodes = methodes;
	}

	public ArrayList<Relation> getRelations() {
		return relations;
	}

	public void setRelations(ArrayList<Relation> relations) {
		this.relations = relations;
	}

	
	
	// M�thodes ajout
	
	public void ajoutAttribut(Attribut a) {
		
		attributs.add(a);
		
	}
	
	
	public void ajoutMethode(Methode m) {
		
		methodes.add(m);
		
	}
	
	
	public void ajoutRelation(Relation r) {
		
		relations.add(r);
		
	}
	
	
	// M�thode supprime
	
	public void suprAttribut(Attribut a) {
		
		attributs.remove(a);
		
	}
	
	
	public void suprMethode(Methode m) {
		
		methodes.remove(m);
		
	}
	

	public void suprRelation(Relation r) {
		
		relations.remove(r);
		
	}


	
	// trad

	public String traductionJava() {
		String trad="";
		String entete = "";
		String constructeur = "";
		String attributsJava = "";
		
	/*	for (int i = 0; i < properties.size(); i++) {
			if(properties.get(i) == ClassProperty.ABSTRACT)
				entete += ClassProperty.ABSTRACT.javaText();
		} */
		 entete += "Public class "+this.nom+" {\r\n";
		 for (int i = 0; i < this.attributs.size(); i++) {
			 attributsJava += this.getAttributs().get(i).toJava()+"\r\n";
		 }
		 constructeur = "Public "+this.nom+"(";
		 constructeur += this.getAttributs().get(0).getType()+" "+this.getAttributs().get(0).getNom();
		 for (int i = 1; i < this.attributs.size(); i++) {
			 constructeur += ", "+this.attributs.get(i).getType()+" "+this.attributs.get(i).getNom();
		 }
		 constructeur += "){\r\n "; 
		 for (int i = 0; i < this.attributs.size(); i++) {
			 constructeur += "this."+this.getAttributs().get(i).getNom()+" = "+this.getAttributs().get(i).getNom()+";\r\n";
		 }
		 constructeur += "}";
		 trad += entete+" "+attributsJava+" "+constructeur;
		return trad;
	}
	
	public static void main(String args[]) {
		Classe Voiture = new Classe("Voiture");
		Attribut Moteur = new Attribut("Moteur","Composant","private");
		Attribut Volant = new Attribut("Volant","Composant","private");
		Voiture.ajoutAttribut(Moteur);
		Voiture.ajoutAttribut(Volant);
		System.out.println(Voiture.traductionJava()+"\n}");		
	}
}