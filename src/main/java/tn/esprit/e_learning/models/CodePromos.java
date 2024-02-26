package tn.esprit.e_learning.models;


import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;
public class CodePromos {
    private int code;
    private float valeur;
    private Date DateExpiration;
    private boolean active;

    public CodePromos() {
    }

    public CodePromos(int code, float valeur, Date dateExpiration, boolean active) {
        this.code = code;
        this.valeur = valeur;
        DateExpiration = dateExpiration;
        this.active = active;
    }

    public CodePromos(float valeur, Date dateExpiration, boolean active) {
        this.valeur = valeur;
        DateExpiration = dateExpiration;
        this.active = active;
    }

    public int getCode() {
        return code;
    }

    public float getValeur() {
        return valeur;
    }

    public Date getDateExpiration() {
        return DateExpiration;
    }

    public boolean isActive() {
        return active;
    }

    public void setCode(int code) { this.code = code; }
    public void setValeur(float valeur) {
        this.valeur = valeur;
    }

    public void setDateExpiration(Date dateExpiration) {
        DateExpiration = dateExpiration;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    public int generateRandomCode() {
        Random random = new Random();
        // Generate a random number between 10000000 and 99999999 (inclusive)
        return random.nextInt(90000000) + 10000000;
    }

    @Override
    public String toString() {
        return //"CodePromos{" +
                "code=" + code +
                ", valeur=" + valeur +
                ", DateExpiration=" + DateExpiration +
                //", active=" + active +
                '.';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodePromos that = (CodePromos) o;
        return code == that.code && Float.compare(valeur, that.valeur) == 0 && active == that.active && Objects.equals(DateExpiration, that.DateExpiration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, valeur, DateExpiration, active);
    }
}
