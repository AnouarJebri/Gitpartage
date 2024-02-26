package tn.esprit.e_learning.models;

import java.util.Objects;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private boolean gender;
    private String profession;
    private boolean enligne;
    private boolean verif;
    private String email;
    private String mdp;

    public Utilisateur() {
    }

    public Utilisateur(String nom, String prenom, boolean gender, String profession, boolean enligne, boolean verif, String email, String mdp) {
        this.nom = nom;
        this.prenom = prenom;
        this.gender = gender;
        this.profession = profession;
        this.enligne = enligne;
        this.verif = verif;
        this.email = email;
        this.mdp = mdp;
    }

    public Utilisateur(int id, String nom, String prenom, boolean gender, String profession, boolean enligne, boolean verif, String email, String mdp) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.gender = gender;
        this.profession = profession;
        this.enligne = enligne;
        this.verif = verif;
        this.email = email;
        this.mdp = mdp;
    }
    public String Genre(boolean gender){
        if (gender)
            return "male";
        else
            return "female";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public boolean isEnligne() {
        return enligne;
    }

    public void setEnligne(boolean enligne) {
        this.enligne = enligne;
    }

    public boolean isVerif() {
        return verif;
    }

    public void setVerif(boolean verif) {
        this.verif = verif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    public String Admin(boolean verif){
        if(verif)
            return "Admin";
        else
            return "Utilisateur simple";
    }
    /*public static String doHashing(String mdp){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(mdp.getBytes());
            byte[] resultByteArray = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : resultByteArray){
                sb.append(String.format("%02x",b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }*/

    @Override
    public String toString() {
        return //"Utilisateur{" +
                "id=" + id +
                ",  nom='" + nom + '\'' +
                ",  prenom='" + prenom + '\'' +
                ",  gender=" + Genre(gender) +
                ",  profession='" + profession + '\'' +
                //", enligne=" + enligne +
                ",  verif=" + Admin(verif) +
                ",  email='" + email + '\'' +
              //  ", mdp='" + mdp + '\'' +
                '.';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return id == that.id && gender == that.gender && enligne == that.enligne && verif == that.verif && Objects.equals(nom, that.nom) && Objects.equals(prenom, that.prenom) && Objects.equals(profession, that.profession) && Objects.equals(email, that.email) && Objects.equals(mdp, that.mdp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, prenom, gender, profession, enligne, verif, email, mdp);
    }
}