package com.buildit.wealth.management.service;

import com.buildit.wealth.management.model.TimeSeriesDataModel;
import com.buildit.wealth.management.response.BaseResponse;

public interface TimeSeriesService {

	
	
	public BaseResponse getTimeSeriesById(final Long id);
	
	public BaseResponse getTimeSeriesByFilter(final String filter, final int first, final int max, final String orderBy,
            final String orderDir);
	
	public BaseResponse insertTimeSeries(TimeSeriesDataModel timeSeriesDataModel);
	
	public BaseResponse updateTimeSeries(TimeSeriesDataModel timeSeriesDataModel,long id);
}
