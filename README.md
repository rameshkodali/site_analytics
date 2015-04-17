# site_analytics
1. Created a maven multimodule structure for this web analytics project. Please download this as maven project 
  and setup maven environment in the local dev environment.
2. API module contains for entities and interface definitions.
3. core module contains the business logic with service,repository and dao implementations. 
4. web module contains the rest end points with integration tests.  Used Spring MVC rest frame work to build the rest services.
   Embedded maven jetty profile to start the web container and test the rest end points.
5. Used HSQL in memory DB and added scripts to create DB and insert test data.
6. To test the rest points individually, go to web module and start Jetty as mvn jetty:run. Starts the web container and you can run the
   RestFuncTest from eclipse.
7. Assumed to have two seperate tables in database WebAnalytics and Page Views with one to many relation ship
  (each web site has to multiple page paths) and representing each web site with website_id.
8. Added different rest end points for multiple ways of reporting in permitted time. Still can add more.
9. Addd unit tests at core module and integration tests at web module which are integrated as part of the build. 
   To run the full build, mvn clean install.
10. Assumed Payload comes as XML for create operation. Used Spring OXM marshallers/unmarshllers to parse Java Object to XML
    and viceversa.
11. Data retrieval rest end points are GET and create is POST operation.
