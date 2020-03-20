package com.buildit.wealth.management.dao;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.buildit.weatlth.management.entity.TimeSeries;




@Transactional
@Repository("timeSeriesDao")
public class TimeSeriesDaoImpl extends CommonDaoImpl implements TimeSeriesDao {

    public static final Logger LOGGER = LogManager.getLogger(TimeSeriesDaoImpl.class.getName());
    
    private static final String FROM_DATE = "fromDate";
    private static final String TO_DATE = "toDate";
    private static final String ORDER_BY = " order by ";

    
    @Override
    public TimeSeries getTimeSeriesById(final Long id) {

        return getCurrentSession().get(TimeSeries.class, id);
    }
    
    
    @Override
    public List<TimeSeries> getTimeSeriesByCriteria(final Map<String, Object> filterMap, final Timestamp fromDate,
                                                                  final Timestamp toDate, final int firstResult, final int maxResult,
                                                                  final String orderBy, final String order) {

        final StringBuilder queryStr = new StringBuilder();
        queryStr.append("SELECT t FROM TimeSeries t where 1=1");

        final Set<String> keyStr = filterMap.keySet();
        for (final String key : keyStr) {

            queryStr.append(" and t." + key + " = :" + key);

        }

        if (fromDate != null && toDate != null) {
            queryStr.append(" and t.time >= :fromDate and t.time <= :toDate");
        }

        if (orderBy != null && !orderBy.isEmpty()) {

            queryStr.append(ORDER_BY + orderBy + " " + order);

        } else {

            queryStr.append(" order by id desc");
        }

        final Session session = getCurrentSession();

        final Query query = session.createQuery(queryStr.toString(), TimeSeries.class);

        final Set<String> keySet = filterMap.keySet();
        for (final String key : keySet) {

            query.setParameter(key, filterMap.get(key));
        }

        if (fromDate != null && toDate != null) {
            query.setParameter(FROM_DATE, fromDate);
            query.setParameter(TO_DATE, toDate);
        }

        query.setFirstResult(firstResult);
        query.setMaxResults(maxResult);
        return query.getResultList();
    }

    @Override
    public long getTImeSeriesByCriteriaCount(final Map<String, Object> filterMap, final Timestamp fromDate, final Timestamp toDate) {

        final StringBuilder queryStr = new StringBuilder();
        queryStr.append("SELECT count(t.id) FROM TimeSeries t where 1=1");

        final Set<String> keyStr = filterMap.keySet();
        for (final String key : keyStr) {

            queryStr.append(" and t." + key + " = :" + key);

        }

        if (fromDate != null && toDate != null) {
            queryStr.append(" and t.time >= :fromDate and t.time <= :toDate");
        }

        final Session session = getCurrentSession();

        LOGGER.info("#####Running query:: {}", queryStr.toString());

        final Query query = session.createQuery(queryStr.toString());

        final Set<String> keySet = filterMap.keySet();
        for (final String key : keySet) {

            query.setParameter(key, filterMap.get(key));
        }

        if (fromDate != null && toDate != null) {
            query.setParameter(FROM_DATE, fromDate);
            query.setParameter(TO_DATE, toDate);
        }
        return (long) query.getSingleResult();
    }

    @Override
    public List<TimeSeries> getTimeSeriesAll() {

        final Session session = getCurrentSession();

        final CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        final CriteriaQuery<TimeSeries> criteriaQuery = criteriaBuilder.createQuery(TimeSeries.class);
        criteriaQuery.from(TimeSeries.class);
        final TypedQuery<TimeSeries> typedQuery = session.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }
    
    @Override
    public  TimeSeries insertTimeSeriesData(TimeSeries timeSeries)
    {
    	try {
            
            insert(timeSeries);
        } catch (final Exception e) {
            LOGGER.info(e.toString());
        }
        return timeSeries;
    	
    }
    
    @Override
    public  TimeSeries updateTimeSeriesData(TimeSeries timeSeries)
    {
    	try {
            TimeSeries timeSeriesToUpdate = getTimeSeriesById(timeSeries.getId());
            
            timeSeriesToUpdate.setClose(timeSeries.getClose());
            timeSeriesToUpdate.setHigh(timeSeries.getHigh());
            timeSeriesToUpdate.setLow(timeSeries.getLow());
            timeSeriesToUpdate.setOpen(timeSeries.getOpen());
            timeSeriesToUpdate.setTime(timeSeries.getTime());
            timeSeriesToUpdate.setVolume(timeSeries.getVolume());
            
            update(timeSeriesToUpdate);
        } catch (final Exception e) {
            LOGGER.info(e.toString());
        }
        return timeSeries;
    	
    }

}
