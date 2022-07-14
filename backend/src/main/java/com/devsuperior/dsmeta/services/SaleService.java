package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {
	
	@Autowired
	private SaleRepository repository;
	
	public Page<Sale> findSales(String minDate, String maxDate, Pageable pageable) {
		
		//Para capturar a data atual dentro da variável today:
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		
		/* Expressão condicional ternária. Se minDate.equals("") for verdade, ou seja, seja nulo/vazio, max receberá 
		 * today.minusDays(365), que é a data atual menos 365 dias. 
		 * Se minDate.equals("") não seja vazio, ele receberá o LocalDate.parse(minDate) */
		//O LocalDate.parse(minDate) serve para converter o tipo de dado String, do minDate, para o formato LocalDate
		LocalDate min = minDate.equals("") ? today.minusDays(365) : LocalDate.parse(minDate);
		
		/* Expressão condicional ternária. Se maxDate.equals("") for verdade, ou seja, seja nulo/vazio, max receberá 
		 * today. Se maxDate.equals("") não seja vazio, ele receberá o LocalDate.parse(maxDate) */
		// O LocalDate.parse(maxDate) serve para converter o tipo de dado String, do maxDate, para o formato LocalDate
		LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);
		
		return repository.findSales(min, max, pageable);
	}

}
