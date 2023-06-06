package com.example.textsharing

import cats.effect.{Async, Resource, Sync}
import com.typesafe.config.Config
import doobie.hikari.HikariTransactor
import doobie.util.ExecutionContexts
import pureconfig._
import pureconfig.generic.auto._

import scala.concurrent.ExecutionContext

case class DatabaseConfig(driverClassName: String, url: String, user: String, password: String)

object DatabaseConfig {
  def createTransactor[F[_] : Sync : Async](config: Config): Resource[F, HikariTransactor[F]] = {
    val databaseConfig = config.getConfig("database")

    val driverClassName = databaseConfig.getString("driverClassName")
    val url = databaseConfig.getString("url")
    val user = databaseConfig.getString("user")
    val password = databaseConfig.getString("password")

    val connectEC = ExecutionContext.global

    for {
      ce <- ExecutionContexts.fixedThreadPool[F](32)
      te <- ExecutionContexts.cachedThreadPool[F]
      xa <- HikariTransactor.newHikariTransactor[F](driverClassName, url, user, password, ce)
    } yield xa
  }
}

