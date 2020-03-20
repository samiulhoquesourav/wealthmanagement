package com.buildit.wealth.management.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buildit.wealth.management.config.GlobalConfiguration;
import com.buildit.wealth.management.dao.TimeSeriesDao;
import com.buildit.wealth.management.model.TimeSeriesDataModel;
import com.buildit.wealth.management.response.BaseResponse;
import com.buildit.wealth.management.response.TimeSeriesResponse;
import com.buildit.wealth.management.util.Utils;
import com.buildit.weatlth.management.entity.TimeSeries;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("timeSeriesServiceManager")
@Transactional
public class TimeSeriesServiceImpl implements TimeSeriesService {

	public static final Logger LOGGER = LogManager.getLogger(TimeSeriesServiceImpl.class.getName());

	@Autowired
	private TimeSeriesDao timeSeriesDao;

	@Autowired
	private GlobalConfiguration globalConfiguration;

	@Override
	public BaseResponse getTimeSeriesById(final Long id) {

		if (id == null || id <= 0) {

			return Utils.getFailedMessage("Invalid Id", HttpStatus.BAD_REQUEST);
		}

		final TimeSeries timeSeries = timeSeriesDao.getTimeSeriesById(id);

		if (timeSeries == null) {

			return Utils.getFailedMessage("No data found", HttpStatus.NOT_FOUND);

		} else {

			TimeSeriesDataModel timeSeriesDataModel = new TimeSeriesDataModel();
			timeSeriesDataModel.setClose(timeSeries.getClose());
			timeSeriesDataModel.setHigh(timeSeries.getHigh());
			timeSeriesDataModel.setLow(timeSeries.getLow());
			timeSeriesDataModel.setOpen(timeSeries.getOpen());
			timeSeriesDataModel.setTime(Utils.getTimestampToStr(timeSeries.getTime()));
			timeSeriesDataModel.setVolume(timeSeries.getVolume());

			return Utils.getSuccessMessage(timeSeriesDataModel);

		}
	}

	@Override
	public BaseResponse getTimeSeriesByFilter(final String filter, final int first, final int max, final String orderBy,
			final String orderDir) {

		if (orderBy != null && orderBy.length() > 0 && (orderDir == null || orderDir.length() < 1)) {

			LOGGER.info("invalid order or order by parameter");
			return Utils.getFailedMessage("invalid order or order by parameter", HttpStatus.BAD_REQUEST);
		}

		if (orderDir != null && orderDir.length() > 0 && (orderBy == null || orderBy.length() < 1)) {

			LOGGER.info("invalid order or order by parameter");
			return Utils.getFailedMessage("invalid order or order by parameter", HttpStatus.BAD_REQUEST);
		}
		LOGGER.info("Filter string: {}", filter);

		Map<String, Object> filterMap = null;

		try {
			final ObjectMapper mapper = new ObjectMapper();

			filterMap = mapper.readValue(filter, HashMap.class);

		} catch (final Exception ex) {
			LOGGER.info(ex.getMessage());

			return Utils.getFailedMessage("Invalid Filter", HttpStatus.BAD_REQUEST);
		}

		String fromDateStr = null;
		String toDateStr = null;
		if (filterMap != null && filterMap.containsKey("fromDate") && filterMap.containsKey("toDate")) {
			fromDateStr = (String) filterMap.get("fromDate");
			toDateStr = (String) filterMap.get("toDate");

			filterMap.remove("fromDate");
			filterMap.remove("toDate");

		}

		final Timestamp fromDate = Utils.getStrToTimestamp(fromDateStr);
		final Timestamp toDate = Utils.getStrToTimestamp(toDateStr);

		final List<TimeSeries> timeSeriesList = timeSeriesDao.getTimeSeriesByCriteria(filterMap, fromDate, toDate,
				first, max, orderBy, orderDir);

		final long rowCount = timeSeriesDao.getTImeSeriesByCriteriaCount(filterMap, fromDate, toDate);

		if (timeSeriesList == null || timeSeriesList.isEmpty()) {

			LOGGER.info("no data found for this filter criteria");

			final TimeSeriesResponse timeSeriesResponse = new TimeSeriesResponse();
			timeSeriesResponse.setTotal(0);
			timeSeriesResponse.setItems(Collections.emptyList());
			return Utils.getSuccessMessage(timeSeriesResponse);

		} else {

			final List<TimeSeriesDataModel> timeSeriesDAtaModelList = new ArrayList<TimeSeriesDataModel>();

			for (final TimeSeries timeSeries : timeSeriesList) {

				TimeSeriesDataModel timeSeriesDataModel = new TimeSeriesDataModel();
				timeSeriesDataModel.setClose(timeSeries.getClose());
				timeSeriesDataModel.setHigh(timeSeries.getHigh());
				timeSeriesDataModel.setLow(timeSeries.getLow());
				timeSeriesDataModel.setOpen(timeSeries.getOpen());
				timeSeriesDataModel.setTime(Utils.getTimestampToStr(timeSeries.getTime()));
				timeSeriesDataModel.setVolume(timeSeries.getVolume());

				timeSeriesDAtaModelList.add(timeSeriesDataModel);

			}

			final TimeSeriesResponse timeSeriesResponse = new TimeSeriesResponse();
			timeSeriesResponse.setTotal(rowCount);
			timeSeriesResponse.setItems(timeSeriesDAtaModelList);

			return Utils.getSuccessMessage(timeSeriesResponse);

		}

	}

	@Override
	public BaseResponse insertTimeSeries(TimeSeriesDataModel timeSeriesDataModel) {

		if (timeSeriesDataModel == null) {

			return Utils.getFailedMessage("Invalid data", HttpStatus.NOT_FOUND);

		}

		TimeSeries timeSeries = new TimeSeries();
		timeSeries.setClose(timeSeriesDataModel.getClose());
		timeSeries.setHigh(timeSeriesDataModel.getHigh());
		timeSeries.setLow(timeSeriesDataModel.getLow());
		timeSeries.setOpen(timeSeriesDataModel.getOpen());
		timeSeries.setTime(Utils.getStrToTimestamp(timeSeriesDataModel.getTime()));
		timeSeries.setVolume(timeSeriesDataModel.getVolume());

		TimeSeries series = timeSeriesDao.insertTimeSeriesData(timeSeries);

		return Utils.getSuccessMessage(null);

	}
	
	@Override
	public BaseResponse updateTimeSeries(TimeSeriesDataModel timeSeriesDataModel,long id) {

		if (timeSeriesDataModel == null) {

			return Utils.getFailedMessage("Invalid data", HttpStatus.NOT_FOUND);

		}

		TimeSeries timeSeries = new TimeSeries();
		timeSeries.setId(id);
		timeSeries.setClose(timeSeriesDataModel.getClose());
		timeSeries.setHigh(timeSeriesDataModel.getHigh());
		timeSeries.setLow(timeSeriesDataModel.getLow());
		timeSeries.setOpen(timeSeriesDataModel.getOpen());
		timeSeries.setTime(Utils.getStrToTimestamp(timeSeriesDataModel.getTime()));
		timeSeries.setVolume(timeSeriesDataModel.getVolume());

		TimeSeries series = timeSeriesDao.updateTimeSeriesData(timeSeries);

		return Utils.getSuccessMessage(null);

	}

}
