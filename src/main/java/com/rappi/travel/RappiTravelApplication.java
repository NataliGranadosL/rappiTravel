package com.rappi.travel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;



@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class RappiTravelApplication {
	
	@Bean
	public MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource
	      = new ReloadableResourceBundleMessageSource();
	    
	    messageSource.setBasename("classpath:messages");
	    messageSource.setDefaultEncoding("UTF-8");
	    return messageSource;
	}
	
	@Bean	
	public LocalValidatorFactoryBean getValidator() {
	    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	    bean.setValidationMessageSource(messageSource());
	    return bean;
	}
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper =  new ModelMapper();
		Converter<Date, String> dateToString = new Converter<Date, String>() {	      
			@Override
			public String convert(MappingContext<Date, String> context) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	            return  dateFormat.format(context.getSource());
			}
	    };
	    
	    Converter<String, Date> stringToDate = new Converter<String, Date>() {	      
			@Override
			public Date convert(MappingContext<String, Date> context) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	            try {
					return  dateFormat.parse(context.getSource());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return null;
			}
	    };
		modelMapper.createTypeMap(Date.class, String.class);
		modelMapper.addConverter(dateToString);
		
		modelMapper.createTypeMap(String.class, Date.class);
		modelMapper.addConverter(stringToDate);
		
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	    return modelMapper;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(RappiTravelApplication.class, args);
	}
	
}
