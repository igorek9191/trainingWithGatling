package computerdatabase

import io.gatling.core.Predef.{Simulation, _}
import io.gatling.http.Predef._

class CheckResponseBody extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8080/app/") // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36")

  val myScn = scenario("Video game DB")
    .exec(http("Get specific game")
      .get("videogames/1")
      .check(xpath("videoGame/name").is("Resident Evil 4")))

    .exec(http("Get all video games")
      .get("videogames")
      .check(xpath("videoGames/videoGame[2]/id").saveAs("gameId")))

    .exec(http("Get specific game")
      .get("videogames/${gameId}")
      .check(xpath("videoGame/name").is("Gran Turismo 3"))
      .check(bodyString.saveAs("responseBody")))


  setUp(myScn.inject(atOnceUsers(1)))
    .protocols(httpProtocol)
}
