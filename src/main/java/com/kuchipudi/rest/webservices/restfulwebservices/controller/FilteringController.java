package com.kuchipudi.rest.webservices.restfulwebservices.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.kuchipudi.rest.webservices.restfulwebservices.model.FilterBean;

@RestController
public class FilteringController {
	
	@GetMapping(path = "/filtering")
	public MappingJacksonValue filtering() {
		FilterBean filterBean = new FilterBean("value1", "value2", "value3");
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(filterBean);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("FilterBeanFilter",filter);
		mappingJacksonValue.setFilters(filters );
		return mappingJacksonValue;
	}

	@GetMapping(path = "/filtering-list")
	public MappingJacksonValue filteringList() {
		List<FilterBean> filterBeanList = Arrays.asList(new FilterBean("value1", "value2", "value3"),
				new FilterBean("value4", "value5", "value6"));
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(filterBeanList);
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("FilterBeanFilter",filter);
		mappingJacksonValue.setFilters(filters );
		
		return mappingJacksonValue;
	}
}
