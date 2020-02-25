package com.apap.tu04.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.apap.tu04.model.PilotModel;
import org.springframework.stereotype.Repository;

/**
 * *PilotDb
 *
 * @author Dwi Ana Ambar Rofiqoh
 *
 */

public interface PilotDb extends JpaRepository<PilotModel, Long>{
	PilotModel findByLicenseNumber(String licenseNumber);
	void deleteByLicenseNumber(String licenseNumber);
}
