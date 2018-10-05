package com.apap.tutorial4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tutorial4.model.FlightModel;
import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.service.PilotService;
import com.apap.tutorial4.service.PilotServiceImpl;


@Controller
public class PilotController {
	@Autowired
	private PilotService pilotService;

	@RequestMapping(value="/")
	private String home() {
	return "home";
	}
	
	
	@RequestMapping(value = "/pilot/add", method=RequestMethod.GET)
	private String add(Model model) {
		model.addAttribute("pilot", new PilotModel());
		return "addPilot";
	}
	
	@RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
	private String addPilotSubmit(@ModelAttribute PilotModel pilot) {
		pilotService.addPilot(pilot);
		return "add";
	}
	@RequestMapping(value ="/pilot/view-pilot", method=RequestMethod.GET)
	public String viewLicense(@RequestParam(value = "licenseNumber") String licenseNumber, Model model) {
		
		PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
		 
		
		if(pilot != null) {
			model.addAttribute("pilot",pilot);
			model.addAttribute("flight",pilot.getPilotFlight());
			
			return "view-pilot";
		}else {
			model.addAttribute("errorMessage","Data not found");
			return "error-message";
		}
	
	}
	@RequestMapping(value = "/pilot/delete/{id}", method = RequestMethod.GET)
	private String delete(@PathVariable Long id, Model model) {
		pilotService.deletePilot(id);
		
		model.addAttribute("errorMessage","Delete Success!");
		
		return "error-message";
	}
	@RequestMapping(value = "/pilot/updatePilot/{licenseNumber}", method=RequestMethod.GET)
	private String updatePIlot(@PathVariable String licenseNumber, Model model) {
		model.addAttribute("pilot",pilotService.getPilotDetailByLicenseNumber(licenseNumber));
		return "updatePilot";
	}
	
	@RequestMapping(value = "/pilot/updatePilot", method = RequestMethod.POST)
	private String updatePilot(@ModelAttribute PilotModel pilot,Model model) {
		pilotService.updatePilot(pilot);
		model.addAttribute("errorMessage","Data have been update");
		return "error-message";
	}
}