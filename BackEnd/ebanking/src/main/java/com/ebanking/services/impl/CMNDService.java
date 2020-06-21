package com.ebanking.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ebanking.dto.CMNDWrapper;
import com.ebanking.entities.CMND;
import com.ebanking.repositories.CMNDRepository;
import com.ebanking.services.ICMNDService;

@Component
public class CMNDService implements ICMNDService {

	private Logger logger = LoggerFactory.getLogger(CMNDService.class);

	@Autowired
	private CMNDRepository cmndRepository;

	@Override
	public List<CMNDWrapper> getAllCMND() {
		List<CMNDWrapper> listCMNDWrapper = new ArrayList<CMNDWrapper>();
		try {
			List<CMND> listCMND = cmndRepository.findAll();

			if (listCMND != null) {

				for (CMND cmnd : listCMND) {
					CMNDWrapper cmndWrapper = new CMNDWrapper();
					BeanUtils.copyProperties(cmnd, cmndWrapper);

					listCMNDWrapper.add(cmndWrapper);
				}

			}
		} catch (Exception e) {
			logger.error("Get all cmnd has error >>> " + e.getMessage(), e);
		}
		return listCMNDWrapper;
	}

	@Override
	public CMNDWrapper getCMNDById(String cmndId) {
		CMNDWrapper cmndWrapper = new CMNDWrapper();
		try {
			Optional<CMND> cmndDB = cmndRepository.findById(cmndId);
			if (cmndDB.isPresent()) {
				BeanUtils.copyProperties(cmndDB.get(), cmndWrapper);

			}
		} catch (Exception e) {
			logger.error("Get cmnd by id has error >>> " + e.getMessage(), e);
		}
		return cmndWrapper;
	}

	@Override
	public boolean createCMND(CMNDWrapper cmndWrapper) {
		boolean response = false;
		try {
			CMND cmnd = new CMND();
			cmnd.setId(cmndWrapper.getId());
			cmnd.setDateGranted(cmndWrapper.getDateGranted());
			cmnd.setPlaceGranted(cmndWrapper.getPlaceGranted());
			cmnd.setFullname(cmndWrapper.getFullname());
			cmnd.setDateOfBirth(cmndWrapper.getDateOfBirth());

			cmndRepository.save(cmnd);
			response = true;
		} catch (Exception e) {
			logger.error("Create cmnd has error >>> " + e.getMessage(), e);
		}
		return response;
	}

	@Override
	public boolean updateCMND(CMNDWrapper cmndWrapper) {
		boolean response = false;
		try {
			Optional<CMND> cmndDB = cmndRepository.findById(cmndWrapper.getId());
			if (cmndDB.isPresent()) {
				CMND cmnd = cmndDB.get();
				cmnd.setId(cmndWrapper.getId());
				cmnd.setDateGranted(cmndWrapper.getDateGranted());
				cmnd.setPlaceGranted(cmndWrapper.getPlaceGranted());
				cmnd.setFullname(cmndWrapper.getFullname());
				cmnd.setDateOfBirth(cmndWrapper.getDateOfBirth());

				cmndRepository.save(cmnd);
				response = true;
			}
		} catch (Exception e) {
			logger.error("Update cmnd has error >>> " + e.getMessage(), e);
		}
		return response;
	}

	@Override
	public boolean deleteCMND(String cmndId) {
		boolean response = false;
		try {
			cmndRepository.deleteById(cmndId);
			response = true;
		} catch (Exception e) {
			logger.error("Delete cmnd has error >>> " + e.getMessage(), e);
		}
		return response;
	}

}
