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

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Creates a spark context for local running and cleans up after all is done.
  *
  * @author olli
  */
object LocalSparkRunner {
    def run(block: SparkContext => Any) = {
        val conf = new SparkConf().
                setMaster("local[*]").
                setAppName("puzzle").
                set("spark.ui.enabled", "false").
                set("spark.app.id", "myid")
        val sc = new SparkContext(conf)
        try {
            block(sc)
        } catch {
            case t: Throwable => System.err.println(t)
        } finally {
            sc.stop()
        }
    }
}
