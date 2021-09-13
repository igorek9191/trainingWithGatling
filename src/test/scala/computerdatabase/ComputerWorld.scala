package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class ComputerWorld extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8080/app/") // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36")


  val myScn = scenario("Video game DB")
    .exec(http("Get all video games, 1st call")
      .get("videogames"))
      .pause(5)
    .exec(http("Get specific game")
      .get("videogames/1"))
      .pause(1, 20)
    .exec(http("Get all video games, 2nd call")
    .get("videogames"))
    .pause(3000.milliseconds)



  setUp(myScn.inject(
    constantUsersPerSec(2) during (1 seconds)
  ).protocols(httpProtocol))

//  val computerDbScn = scenario("Computer Scenario")
//    .exec(http("getComputersForOtus")
//      .get("/computers")
//      .check(
//        status is 200,
//        regex("""\d+ computers found"""),
//        css("#add", "href").saveAs("addComputer")))
//
//    .exec(http("addNewComputer")
//      .get("${addComputer}")
//      .check(substring("Add a computer")))
//
//    .exec(_.set("homeComputer", s"homeComputer_${ThreadLocalRandom.current.nextInt(Int.MaxValue)}"))
//    .exec(http("postComputers")
//      .post("/computers")
//      .formParam("name", "${homeComputer}")
//      .formParam("introduced", "2015-10-10")
//      .formParam("discontinued", "2017-10-10")
//      .formParam("company", "")
//      .check(substring("${homeComputer}")))

//  setUp(computerDbScn.inject(
//    constantUsersPerSec(2) during (1 minute)
//  ).protocols(httpProtocol))

//  setUp(
//    computerDbScn.inject(
//      nothingFor(4 seconds), // 1
//      atOnceUsers(10), // 2
//      rampUsers(10) during (5 seconds), // 3
//      constantUsersPerSec(20) during (15 seconds), // 4
//      constantUsersPerSec(20) during (15 seconds) randomized, // 5
//      rampUsersPerSec(10) to 20 during (10 minutes), // 6
//      rampUsersPerSec(10) to 20 during (10 minutes) randomized, // 7
//      heavisideUsers(1000) during (20 seconds) // 8
//    ).protocols(httpProtocol)
//  )
//
//  setUp(computerDbScn.inject(constantUsersPerSec(1000) during (30 minutes))).throttle(
//    reachRps(100) in (10 seconds),
//    holdFor(1 minute)
//  )


}
