package com.rappi.travel.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="RTT_TIQUETE")
public class Tiquete implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5835748328420120208L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TIQUETE")
    @SequenceGenerator(sequenceName = "SEQ_CVE_TIQUETE", allocationSize = 1, name = "SEQ_TIQUETE")   
	private Long idTiquete;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private Short edad;
	private Date fechaSalida;
	private Date fechaLlegada;
	private String ciudadOrigen;
	private String ciudadDestino;
	private Boolean tieneEquipaje;
	private BigDecimal precio;
	
	
	
}
