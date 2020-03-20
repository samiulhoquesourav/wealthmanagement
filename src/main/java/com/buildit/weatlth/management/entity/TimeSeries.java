package com.buildit.weatlth.management.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the time_series database table.
 * 
 */
@Entity
@Table(name="time_series")
@NamedQuery(name="TimeSeries.findAll", query="SELECT t FROM TimeSeries t")
public class TimeSeries implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "time_series_id_generator")
    @SequenceGenerator(name = "time_series_id_generator", sequenceName = "time_series_seq", initialValue = 3, allocationSize = 1)
	private Long id;

	private double close;

	private double high;

	private double low;

	private double open;

	private Timestamp time;

	private double volume;

	public TimeSeries() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getClose() {
		return this.close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public double getHigh() {
		return this.high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return this.low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getOpen() {
		return this.open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public double getVolume() {
		return this.volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}

}