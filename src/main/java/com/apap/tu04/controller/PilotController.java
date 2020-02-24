package com.apap.tu04.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tu04.model.PilotModel;
import com.apap.tu04.service.PilotService;

/**
 * PilotController
 * 
 * @author Dwi Ana Ambar Rofiqoh
 *
 */

@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;
	
	@RequestMapping("/")private String home() {
		return "home";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("pilot", new PilotModel());
		return "addPilot";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot, @RequestParam(value = "licenseNumber") String licenseNumber) {
		PilotModel pilotCheck = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		if(pilotCheck != null) {
			return "exist";
		}
		pilotService.addPilot(pilot);
		return "add";
	}
	
	
	//Search Pilot
	@RequestMapping(value = "/pilot/view")
	public String search(@RequestParam(value = "licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		if(pilot == null) {
			return "notfound";
		}
		model.addAttribute("pilot", pilot);
		model.addAttribute("listFlight", pilot.getPilotFlight());
		return "view-pilot";
	}
	
	// Latihan 1
    // Path Variable Berdasarkan License Number
	@RequestMapping(value = "/pilot/view/{licenseNumber}", method = RequestMethod.GET)
	private String viewPilot(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		if(pilot == null) {
			return "notfound";
		}
		model.addAttribute("pilot", pilot);
		model.addAttribute("listFlight", pilot.getPilotFlight());
		return "view-pilot";
	}
	
	// Latihan 3
    // Membuat Fitur Delete Pilot
	@RequestMapping(value = "/pilot/delete/{licenseNumber}", method = RequestMethod.GET)
	private String deletePilot(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		PilotModel pilotDelete = pilotService.getPilotDetailByLicenseNumber(licenseNumber); 
		if(pilotDelete.getPilotFlight().size() == 0) {
			pilotService.deletePilot(licenseNumber);
		}else {
			pilotDelete.getPilotFlight().clear();
			pilotService.deletePilot(licenseNumber);
		} return "delete";
	}
	
	// Latihan 4
    // Membuat Fitur Update Pilot
	@RequestMapping(value = "/pilot/update/{licenseNumber}", method = RequestMethod.GET)
	private String updatePilot(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		PilotModel oldPilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("oldPilot", oldPilot);
		model.addAttribute("newPilot", new PilotModel());
		return "updatePilot";
	}
	
	@RequestMapping(value = "/pilot/update/{licenseNumber}", method = RequestMethod.POST)
	private String updatePilot(@ModelAttribute PilotModel newPilot, @PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
		pilotService.updatePilot(licenseNumber, newPilot);
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		model.addAttribute("newPilot", newPilot);
		return "update";
	}
	
}
