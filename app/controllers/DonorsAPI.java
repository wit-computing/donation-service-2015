package controllers;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import models.Donor;
import play.mvc.Controller;

public class DonorsAPI extends Controller
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
}
