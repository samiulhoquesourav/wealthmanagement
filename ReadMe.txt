Run the project--

1. Please run the DB script below to create the table,sequence in postgres 
2. Configure the db crededntials in application.yml file
3. Goto the project directory and run "mvn clean install".
4. It will create a jar file in the target folder.
5. Run java -jar WealthManagement-0.0.1-SNAPSHOT.jar .
6. The application will be started and will listen on port 8080.


DB script--


Please run the script below to create the table,sequence in postgres 
Configure the db crededntials in application.yml file

CREATE TABLE public.time_series
(
    close double precision NOT NULL,
    high double precision NOT NULL,
    id bigint NOT NULL,
    low double precision NOT NULL,
    open double precision NOT NULL,
    "time" timestamp without time zone NOT NULL,
    volume double precision NOT NULL,
    CONSTRAINT time_series_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE public.time_series
    OWNER to postgres;


CREATE SEQUENCE public.time_series_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 99999
    CACHE 1;

ALTER SEQUENCE public.time_series_seq
    OWNER TO postgres; 










Api doc--

1. Get timeseries data by id

GET http://localhost:8080/wealth/manage/timeseries/1

response::

{
    "close": 123.0,
    "high": 123.0,
    "low": 1123.0,
    "open": 123.0,
    "time": "2016-06-22 19:10:25",
    "volume": 321.0
}


2. Get time series data by filter

GET http://localhost:8080/wealth/manage/timeseries?filters={"close": 123.0}&first=0&max=10&orderBy=id&orderDir=asc

response::

{
    "total": 1,
    "items": [
        {
            "close": 123.0,
            "high": 123.0,
            "low": 1123.0,
            "open": 123.0,
            "time": "2016-06-22 19:10:25",
            "volume": 321.0
        }
    ]
}


3. Insert time series data

POST  http://localhost:8080/wealth/manage/timeseries

request body:

{
    "close": 123.0,
    "high": 123.0,
    "low": 1123.0,
    "open": 123.0,
    "time": "2016-06-22 19:10:25",
    "volume": 321.0
}

response: 200 ok


4. Update time series data

PUT  http://localhost:8080/wealth/manage/timeseries/1

request body:


{
    "close": 123.0,
    "high": 123.0,
    "low": 1123.0,
    "open": 123.0,
    "time": "2016-06-22 19:10:25",
    "volume": 324.0
}

response: 200 ok

