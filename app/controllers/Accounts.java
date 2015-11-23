package controllers;

import play.*;
import play.mvc.*;
import models.*;

public class Accounts extends Controller
{
  public static void index()
  {
    render();
  }

  public static void signup()
  {
    render();
  }

  public static void register(boolean usCitizen, String firstName, String lastName, String email, String password)
  {
    Logger.info( firstName + " " + lastName + " " + email + " " + password);
    Donor user = new Donor(firstName, lastName, email, password);
    user.save();
    index();
  }

  public static void login()
  {
    render();
  }

  public static void logout()
  {
    session.clear();
    index();
  }

  public static void authenticate(String email, String password)
  {
    Logger.info("Attempting to authenticate with " + email + ":" + password);

    Donor user = Donor.findByEmail(email);
    if ((user != null) && (user.checkPassword(password) == true))
    {
      Logger.info("Successfull authentication of  " + user.firstName + " " + user.lastName);
      session.put("logged_in_userid", user.id);
      Donations.index();
    }
    else
    {
      Logger.info("Authentication failed");
      login();
    }
  }

  public static Donor getCurrentUser()
  {
    String userId = session.get("logged_in_userid");
    if (userId == null)
    {
      index();
    }
    Donor logged_in_user = Donor.findById(Long.parseLong(userId));
    Logger.info("In Accounts controller: Logged in user is " + logged_in_user.firstName);
    return logged_in_user;
  }
}