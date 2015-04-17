insert into WebAnalytics (id, domain_name, country, state,city, browser,ip_address) values (1, 'www.google.com', 'USA', 'CA', 'Los Angeles',  'FF','123.2.2.2');
insert into WebAnalytics (id, domain_name, country, state,city, browser,ip_address) values (2, 'www.google.com', 'USA', 'CA', 'SFO',  'FF','123.2.2.3');

insert into PageViews (id, pagePath, website_id, daysSinceLastVisit, lastVisitedDate, pageViewCounter, visitors, uniqueVisitors, timeOnPage,averageTimeOnPage) 
values (1, '/testPage/Page1',1, 10, to_date('14-APR-2015', 'DD-MON-YYYY'), 10, 5, 5, to_date('01:02:44', 'HH24:MI:SS'),to_date('01:02:44', 'HH24:MI:SS'));
insert into PageViews (id, pagePath, website_id, daysSinceLastVisit, lastVisitedDate, pageViewCounter, visitors, uniqueVisitors, timeOnPage,averageTimeOnPage) 
values (2, '/testPage/Page2',1, 9, to_date('14-APR-2015', 'DD-MON-YYYY'), 9, 3, 6, to_date('01:02:44', 'HH24:MI:SS'),to_date('01:02:44', 'HH24:MI:SS'));
insert into PageViews (id, pagePath, website_id, daysSinceLastVisit, lastVisitedDate, pageViewCounter, visitors, uniqueVisitors, timeOnPage,averageTimeOnPage) 
values (3, '/testPage/Page3',1, 8, to_date('14-APR-2015', 'DD-MON-YYYY'), 8, 4, 4, to_date('01:02:44', 'HH24:MI:SS'),to_date('01:02:44', 'HH24:MI:SS'));

insert into PageViews (id, pagePath, website_id, daysSinceLastVisit, lastVisitedDate, pageViewCounter, visitors, uniqueVisitors, timeOnPage,averageTimeOnPage) 
values (4, '/testPage/Page3',2, 10, to_date('14-APR-2015', 'DD-MON-YYYY'), 6, 1, 5, to_date('01:02:44', 'HH24:MI:SS'),to_date('01:02:44', 'HH24:MI:SS'));
insert into PageViews (id, pagePath, website_id, daysSinceLastVisit, lastVisitedDate, pageViewCounter, visitors, uniqueVisitors, timeOnPage,averageTimeOnPage) 
values (5, '/testPage/Page4',2, 9, to_date('14-APR-2015', 'DD-MON-YYYY'), 10, 2, 8, to_date('01:02:44', 'HH24:MI:SS'),to_date('01:02:44', 'HH24:MI:SS'));