package com.rappi.travel.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rappi.travel.entity.Tiquete;
import com.rappi.travel.model.TiqueteRequest;
import com.rappi.travel.model.TiqueteResponse;
import com.rappi.travel.services.TiqueteService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/v1")
@RestController
public class TiqueteController {
	
	@Autowired
	private TiqueteService tiqueteService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/tiquete/{idTiquete}")
	public ResponseEntity<Object> obtienerPorIdTiquete(@PathVariable("idTiquete") Long idTiquete) {
		
		try {
			Tiquete tiquete = tiqueteService.obtenerTiquetePorId(idTiquete);
			if(tiquete != null) {
				TiqueteRequest tiqueteModel = modelMapper.map(tiquete, TiqueteRequest.class);	
				SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
				tiqueteModel.setHoraLlegada(dateFormat.format(tiquete.getFechaLlegada()));
				tiqueteModel.setHoraSalida(dateFormat.format(tiquete.getFechaSalida()));
				return new ResponseEntity<>(tiqueteModel,  HttpStatus.OK);				
			}
			return new ResponseEntity<>(HttpStatus.OK);							

		} catch (Exception e) {
			log.error("Ocurrio un error al consultar el tiquete", e.getMessage());
			return new ResponseEntity<>("Ocurrio un error al consultar el tiquete", HttpStatus.INTERNAL_SERVER_ERROR);
		}
				
	}
	
	@PostMapping("/tiquete")
	public ResponseEntity<Object> guardarTiquete(@Valid @RequestBody TiqueteRequest tiqueteModel) {
		try {
			tiqueteModel.setFechaLlegada(tiqueteModel.getFechaLlegada().concat(" ").concat(tiqueteModel.getHoraLlegada()));
			tiqueteModel.setFechaSalida(tiqueteModel.getFechaSalida().concat(" ").concat(tiqueteModel.getHoraSalida()));

			Tiquete tiqueteEntity = modelMapper.map(tiqueteModel, Tiquete.class);
			
			TiqueteResponse response = new TiqueteResponse();
			response.setIdTiquete(tiqueteService.guardarTiquete(tiqueteEntity).getIdTiquete());
			
			return new ResponseEntity<>(response, HttpStatus.CREATED);		

		} catch (Exception e) {
			log.error("No pudo ser guardado el tiquete", e.getMessage());
			return new ResponseEntity<>("No pudo ser guardado el tiquete", HttpStatus.INTERNAL_SERVER_ERROR);		

		}
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
	
}
