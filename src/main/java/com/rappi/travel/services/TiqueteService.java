package com.rappi.travel.services;

import com.rappi.travel.entity.Tiquete;

public interface TiqueteService {
	
	Tiquete obtenerTiquetePorId(Long idTiquete) throws Exception;

	Tiquete guardarTiquete(Tiquete tiquete) throws Exception;

}
