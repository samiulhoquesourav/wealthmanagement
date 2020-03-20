package com.buildit.wealth.management.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buildit.wealth.management.model.TimeSeriesDataModel;
import com.buildit.wealth.management.response.BaseResponse;
import com.buildit.wealth.management.response.TimeSeriesResponse;
import com.buildit.wealth.management.service.TimeSeriesService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping("wealth/manage/timeseries")
public class TimeSeriesController {

	@Autowired
	private TimeSeriesService timeSeriesService;


	private final ObjectMapper objectMapper = new ObjectMapper();
	public static final Logger LOGGER = LogManager.getLogger(TimeSeriesController.class.getName());
	
	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<TimeSeriesDataModel> getTimeSeriesByID(final HttpServletRequest httpServletRequest, @PathVariable final Long id) {

        
        final BaseResponse baseResponse = timeSeriesService.getTimeSeriesById(id);

      
            if (baseResponse.getStatus().getHttpStatus() == HttpStatus.OK) {
               
                return new ResponseEntity<>((TimeSeriesDataModel) baseResponse.getData().getDataModel(), baseResponse.getStatus().getHttpStatus());
            } else {
                
                return new ResponseEntity<>(baseResponse.getStatus().getHttpStatus());
            }
        
    }
	
	@GetMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<TimeSeriesResponse> getTimeSeriesByFilter(@RequestParam(value = "filters", defaultValue = "{}") final String filter,
                                                                     @RequestParam(value = "first", required = false) final Integer firstResult,
                                                                     @RequestParam(value = "max", required = false) final Integer maxResult,
                                                                     @RequestParam(value = "orderBy", required = false) final String orderBy,
                                                                     @RequestParam(value = "orderDir", required = false) final String orderDir) {

        

        
            final BaseResponse baseResponse = timeSeriesService.getTimeSeriesByFilter(filter, firstResult, maxResult, orderBy, orderDir);

            if (baseResponse.getStatus().getHttpStatus() == HttpStatus.OK) {
               
                return new ResponseEntity<>((TimeSeriesResponse) baseResponse.getData().getDataModel(), baseResponse.getStatus().getHttpStatus());
            } else {
                
                return new ResponseEntity<>(baseResponse.getStatus().getHttpStatus());
            }

       
    }
	
	@PostMapping(value = "", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<BaseResponse> insertTimeSeries(@RequestBody final TimeSeriesDataModel timeSeriesDataModel) {

       
       

            final BaseResponse baseResponse = timeSeriesService.insertTimeSeries(timeSeriesDataModel);

            if (baseResponse.getStatus().getHttpStatus() == HttpStatus.OK) {
                
                return new ResponseEntity<>( baseResponse.getStatus().getHttpStatus());
            } else {
                
                return new ResponseEntity<>(baseResponse.getStatus().getHttpStatus());
            }
       
    }
	
	@PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<BaseResponse> updateTimeSeries(@RequestBody final TimeSeriesDataModel timeSeriesDataModel, @PathVariable final Long id) {

      

        

            final BaseResponse baseResponse = timeSeriesService.updateTimeSeries(timeSeriesDataModel, id);

            if (baseResponse.getStatus().getHttpStatus() == HttpStatus.OK) {
               
                return new ResponseEntity<>(baseResponse.getStatus().getHttpStatus());
            } else {
                
                return new ResponseEntity<>(baseResponse.getStatus().getHttpStatus());
            }
      
    }


}
