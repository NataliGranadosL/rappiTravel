package com.rappi.travel.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rappi.travel.entity.Tiquete;
import com.rappi.travel.repository.TiqueteRepository;
import com.rappi.travel.services.TiqueteService;


@Service
public class TiqueteServiceImpl implements TiqueteService {
	
	@Autowired
	private TiqueteRepository tiqueteRepository;

	@Override
	public Tiquete obtenerTiquetePorId(Long idTiquete) throws Exception{
		Optional<Tiquete> optional = tiqueteRepository.findById(idTiquete);
		Tiquete tiquete = null;
		if(optional.isPresent()) { 
			tiquete = optional.get();
		}
		return tiquete;
	}

	@Override
	public Tiquete guardarTiquete(Tiquete tiquete) throws Exception {
		return tiqueteRepository.save(tiquete);
	}	
	
}
