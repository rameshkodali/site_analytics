create schema testdatabase authorization dba;

set schema testdatabase;

create table WebAnalytics(
 id numeric(22) IDENTITY,
 domain_name varchar(256),
 country varchar(20),
 state varchar(20),
 city varchar(20),
 browser varchar(15),
 ip_address varchar(15),
);


create table PageViews(
 id numeric(22) IDENTITY,
 pagePath varchar(4000),
 website_id numeric(22) not null,
 daysSinceLastVisit numeric(22),
 lastVisitedDate date,
 pageViewCounter numeric(10),
 visitors numeric(10),
 uniqueVisitors numeric(10),
 timeOnPage time,
 averageTimeOnPage time,
);

ALTER TABLE PageViews ADD FOREIGN KEY (website_id) REFERENCES WebAnalytics(id);