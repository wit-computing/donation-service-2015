package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class Donations extends Controller
{
  public static void index()
  {
    Donor donor = Accounts.getCurrentUser();
    String prog = getPercentTargetAchieved();
    String progress = prog;
    Logger.info("Donation ctrler : user is " + donor.email);
    Logger.info("Donation ctrler : percent target achieved " + progress);
    List<Donation> donations = donor.donations;
    render(donor, progress, donations);
  }

  public static void donate(int amountDonated, String methodDonated)
  {
    Donor donor = Accounts.getCurrentUser();
    Logger.info("amount donated " + amountDonated + " " + "method donated " + methodDonated);
    Donation donation = new Donation(amountDonated, methodDonated);
    donor.donations.add(donation);
    donor.save();
    index();
  }

  private static long getDonationTarget()
  {
    return 20000;
  }

  public static String getPercentTargetAchieved()
  {
    List<Donation> allDonations = Donation.findAll();
    long total = 0;
    for (Donation donation : allDonations)
    {
      total += donation.amount;
    }
    long target = getDonationTarget();
    long percentachieved = (total * 100 / target);
    String progress = String.valueOf(percentachieved);
    Logger.info("Percent of target achieved (string) " + progress + " percentachieved (long)= " + percentachieved);
    return progress;
  }

  public static void renderreport()
  {
    Donor donor = Accounts.getCurrentUser();
    List<Donation> donations = donor.donations;
    render(donations);
  }
}