package com.apap.tutorial4.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tutorial4.model.PilotModel;
import com.apap.tutorial4.repository.PilotDB;

@Service
@Transactional
public class PilotServiceImpl implements PilotService{
	@Autowired
	private PilotDB pilotDB;
	
	@Override
	public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
		return pilotDB.findByLicenseNumber(licenseNumber);
	}
	
	@Override
	public void addPilot(PilotModel pilot) {
		pilotDB.save(pilot);
	}
	public void deletePilot(Long id) {
		pilotDB.deleteById(id);
	}
	
	public void updatePilot(PilotModel pilot) {
		PilotModel pilotlama = pilotDB.findByLicenseNumber(pilot.getLicenseNumber());
		pilotlama.setName(pilot.getName());
		pilotlama.setFlyHour(pilot.getFlyHour());
		pilotDB.save(pilotlama);
	}
}
