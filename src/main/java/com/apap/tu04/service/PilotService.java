package com.apap.tu04.service;

import com.apap.tu04.model.PilotModel;

/**
 * PilotService
 * 
 * @author Dwi Ana Ambar Rofiqoh
 *
 */
public interface PilotService {
	PilotModel getPilotDetailByLicenseNumber(String licenseNumber);
	void addPilot(PilotModel pilot);
	void deletePilot(String licenseNumber);
	void updatePilot(String licenseNumber,PilotModel pilot);

}
