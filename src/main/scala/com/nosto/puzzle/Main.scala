/** *****************************************************************************
  * Copyright (c) 2016 Nosto Solutions Ltd All Rights Reserved.
  * <p>
  * This software is the confidential and proprietary information of
  * Nosto Solutions Ltd ("Confidential Information"). You shall not
  * disclose such Confidential Information and shall use it only in
  * accordance with the terms of the agreement you entered into with
  * Nosto Solutions Ltd.
  * *****************************************************************************/

package com.nosto.puzzle

import org.apache.spark.SparkContext

/**
  * A main class for data engineer puzzle.
  * Creates a spark context an calls the actual implementation
  *
  * @author olli
  */
object Main {
    def instantiate(sc: SparkContext): Puzzle = {// <-- ADD YOUR IMPLEMENTATION HERE



    }

    def main(args: Array[String]): Unit = LocalSparkRunner.run{sc => {
        val puzzle = instantiate(sc)
        println(s"Recos: ${puzzle.recommend("452f3d89851837d0ad3e02268115c73a").mkString(" ")}")
    }}
}
