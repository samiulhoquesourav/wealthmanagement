package com.buildit.wealth.management.response;

import java.util.List;

import com.buildit.wealth.management.model.TimeSeriesDataModel;
import com.buildit.weatlth.management.entity.TimeSeries;

public class TimeSeriesResponse {

	
	private long total;
	
	private List<TimeSeriesDataModel> items;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<TimeSeriesDataModel> getItems() {
		return items;
	}

	public void setItems(List<TimeSeriesDataModel> items) {
		this.items = items;
	}

	
	
	
	
}
