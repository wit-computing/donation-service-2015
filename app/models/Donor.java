package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

import play.db.jpa.Model;

@Entity
public class Donor extends Model
{
  public String firstName;
  public String lastName;
  public String email;
  public String password;

  public Donor(String firstName, String lastName, String email, String password)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
  } 
}