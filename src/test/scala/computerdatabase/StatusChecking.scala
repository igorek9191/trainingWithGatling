package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class StatusChecking extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8080/app/") // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36")


  val myScn = scenario("Video game DB")
    .exec(http("Get all video games, 1st call")
      .get("videogames")
      .check(status.is(200)))
    .exec(http("Get specific game")
      .get("videogames/1")
      .check(status.in(200 to 210)))
    .exec(http("Get all video games, 2st call")
      .get("videogames")
      .check(status.not(404), status.not(500)));

  setUp(myScn.inject(
    constantUsersPerSec(2) during (1 seconds)
  ).protocols(httpProtocol))
}
