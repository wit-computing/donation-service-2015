package controllers;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import models.Donation;
import models.Donor;
import play.mvc.Controller;

public class DonationsAPI extends Controller
{
  static Gson gson = new Gson();
  
  public static void getAllDonations()
  {
    List<Donation> donations = Donation.findAll();
    renderJSON(gson.toJson(donations));
  }
  
  public static void deleteAllDonations()
  {
    Donation.deleteAll();
    renderText("success");
  }

  public static void getDonations (Long id)
  {
    Donor donor = Donor.findById(id);
    renderJSON (gson.toJson(donor.donations));
  }
  
  public static void getDonation (Long id, Long donationId)
  {
   Donation donation = Donation.findById(donationId);
   renderJSON (gson.toJson(donation));
  }

  public static void createDonation(Long id, JsonElement body)
  {
    Donor donor = Donor.findById(id);
    Donation donation = gson.fromJson(body.toString(), Donation.class);
    donation.id = null;
    donor.donations.add(donation);
    donor.save();
    renderJSON (gson.toJson(donation));
  }  

  public static void deleteDonation(Long id, Long donationId)
  {
    Donation donation = Donation.findById(donationId);
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
}
