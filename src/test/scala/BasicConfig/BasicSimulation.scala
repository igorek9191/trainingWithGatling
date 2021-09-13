package BasicConfig

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class BasicSimulation extends Simulation {

  val baseHttpProtocol = http
    .baseUrl("http://localhost:8080/app/") // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36")

//  val scn = scenario("Scenario Name") // A scenario is a chain of requests and pauses
//    .exec(http("request_1")
//      .get("/"))
//    .pause(7) // Note that Gatling has recorder real time pauses
//
//  setUp(scn.inject(atOnceUsers(1)).protocols(httpProtocol))
}
