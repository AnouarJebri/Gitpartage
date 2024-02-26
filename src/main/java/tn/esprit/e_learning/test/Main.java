package tn.esprit.e_learning.test;

import tn.esprit.e_learning.models.CodePromos;
import tn.esprit.e_learning.models.Mail;
import tn.esprit.e_learning.models.Utilisateur;
import tn.esprit.e_learning.services.CodePromosService;
import tn.esprit.e_learning.services.UtilisateurService;
import tn.esprit.e_learning.utils.MyDatabase;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws ParseException {
        //MyDatabase db = MyDatabase.getInstance();
        /*UtilisateurService us = new UtilisateurService();
        CodePromosService cd = new CodePromosService();*/
       /* Mail mail = new Mail();
        mail.sendRecoveryCode("anouar.jebri@gmail.com","Salemou3alaykom");
*/
      /*try {
            us.ajouterUtilisateur(new Utilisateur("Jebri","Anouar",true,"IT Engeneer Student",false,false,"anouar.jebri@gmail.com","passwordTest"));

        } catch (SQLException e){
            System.err.println(e.getMessage());
        }*/
       /* try {
            us.modifierUtilisateur(new Utilisateur(2,"Hammemi","Momen",true,"IT Engeneer Student",true,false,"momen.hammemi@gmail.com","passwordTest1"));

        } catch (SQLException e){
            System.err.println(e.getMessage());
        }*/
        /*try {
            us.supprimerUtilisateur(1);

        } catch (SQLException e){
            System.err.println(e.getMessage());
        }*/
        /*try {
            System.out.println(us.recupererU());
        } catch (SQLException e){
            System.err.println(e.getMessage());
        }*/

        /*try {

            //DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            //java.util.Date utilDate = df.parse("2024_02_00");
            //sqlDate = new Date(utilDate.getTime());
            cd.ajouterCodePromos(new CodePromos(500,Date.valueOf("2024-02-07"),true));
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }*/
    }
}
