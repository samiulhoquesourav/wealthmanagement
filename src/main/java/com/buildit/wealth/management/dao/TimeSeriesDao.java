package com.buildit.wealth.management.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.buildit.weatlth.management.entity.TimeSeries;

public interface TimeSeriesDao {

	public TimeSeries getTimeSeriesById(final Long id);

	public List<TimeSeries> getTimeSeriesByCriteria(final Map<String, Object> filterMap, final Timestamp fromDate,
			final Timestamp toDate, final int firstResult, final int maxResult, final String orderBy,
			final String order);

	public long getTImeSeriesByCriteriaCount(final Map<String, Object> filterMap, final Timestamp fromDate,
			final Timestamp toDate);

	public List<TimeSeries> getTimeSeriesAll();

	public TimeSeries insertTimeSeriesData(TimeSeries timeSeries);

	public TimeSeries updateTimeSeriesData(TimeSeries timeSeries);
}
