package com.ebanking.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ebanking.dto.CMNDWrapper;
import com.ebanking.entities.CMND;

@Service
public interface ICMNDService {
	public List<CMNDWrapper> getAllCMND();

	public CMNDWrapper getCMNDById(String cmndId);

	public CMND createCMND(CMNDWrapper cmndWrapper);

	public boolean updateCMND(CMNDWrapper cmndWrapper);

	public boolean deleteCMND(String cmndId);
}
