package com.rappi.travel.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class TiqueteRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2502417190942981465L;
	
	private Long idTiquete;

	@NotBlank(message = "{nombre.notblank}")
	private String nombre;
	
	@NotBlank(message = "{apellidoPaterno.notblank}")
	private String apellidoPaterno;
	
	@NotBlank(message = "{apellidoMaterno.notblank}")
	private String apellidoMaterno;
	
	@NotBlank(message = "{ciudadOrigen.notblank}")
	private String ciudadOrigen;
	
	@NotBlank(message = "{ciudadDestino.notblank}")
	private String ciudadDestino;
	
	@NotBlank(message = "{fechaSalida.notblank}")
	@Pattern(regexp="^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$", message = "{fechaSalida.pattern}")
	private String fechaSalida;

	@NotBlank(message = "{fechaLlegada.notblank}")
	@Pattern(regexp="^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$", message = "{fechaLlegada.pattern}")
	private String fechaLlegada;
	
	@NotBlank(message = "{horaSalida.notblank}")
	@Pattern(regexp="^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "{fechaSalida.pattern}")
	private String horaSalida;

	@NotBlank(message = "{horaLlegada.notblank}")
	@Pattern(regexp="^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "{fechaLlegada.pattern}")
	private String horaLlegada;

	@NotNull(message = "{edad.notnull}")
	private Short edad;
	
	@NotNull(message = "{tieneEquipaje.notnull}")
	private Boolean tieneEquipaje;

	@NotNull(message = "{precio.notnull}")
	private BigDecimal precio;
	

}
