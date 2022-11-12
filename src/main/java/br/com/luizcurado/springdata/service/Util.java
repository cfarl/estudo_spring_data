package br.com.luizcurado.springdata.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {
	
	//----------------------------------------------------------------
	/** Converte a string no formato "dd/MM/yyyy" para LocalDate */
	//----------------------------------------------------------------		
	public static LocalDate toLocalDate(String data) {
		return LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy")) ;
	}
}
