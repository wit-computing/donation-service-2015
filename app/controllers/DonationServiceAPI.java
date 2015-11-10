package controllers;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import models.Donation;
import models.Donor;
import play.mvc.Controller;

public class DonationServiceAPI extends Controller
{
  static Gson gson = new Gson();

  public static void getAllDonors()
  {
    List<Donor> Donors = Donor.findAll();
    renderJSON(gson.toJson(Donors));
  }

  public static void getDonor(Long id)
  {
    Donor donor = Donor.findById(id);  
    if (donor == null)
    {
      notFound();
    }
    else
    {
      renderJSON(gson.toJson(donor));
    }
  }

  public static void createDonor(JsonElement body)
  {
    Donor donor = gson.fromJson(body.toString(), Donor.class);
    donor.id = null;
    donor.save();
    renderJSON(gson.toJson(donor));
  }

  public static void deleteDonor(Long id)
  {
    Donor donor = Donor.findById(id);
    if (donor == null)
    {
      notFound("No Doner with ID" + id);
    }
    else
    {
      donor.delete();
      renderJSON(gson.toJson(donor));
    }
  }

  public static void deleteAllDonors()
  {
    Donor.deleteAll();
    renderText("success");
  }    

  public static void getAllDonations()
  {
    List<Donation> donations = Donation.findAll();
    renderJSON(gson.toJson(donations));
  }

  public static void getDonation (Long id)
  {
   Donation donation = Donation.findById(id);
   renderJSON (gson.toJson(donation));
  }

  public static void createDonation(JsonElement body)
  {
    Donation donation = gson.fromJson(body.toString(), Donation.class);
    Donation newDonation = new Donation (donation.amount, donation.method);
    newDonation.id = null;
    newDonation.save();
    renderJSON (gson.toJson(newDonation));
  }  

  public static void deleteDonation(Long id)
  {
    Donation donation = Donation.findById(id);
    if (donation == null)
    {
      notFound();
    }
    else
    {
      donation.delete();
      renderJSON (gson.toJson(donation));
    }
  }  

  public static void deleteAllDonations()
  {
    Donation.deleteAll();
    renderText("success");
  }
}
