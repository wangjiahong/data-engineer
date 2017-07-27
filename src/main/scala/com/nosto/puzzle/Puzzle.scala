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

/**
  * Implement this trait in your solution!
  *
  * @author olli
  */
trait Puzzle {
    /**
      * Calculates recommendations for a given user.
      * For sake of simplicity, it can be assumed that the
      * given user id is contained in the given data set.
      *
      * @param customerId The customer id
      * @return A list of recommended product ids
      */
    def recommend(customerId: String): List[String] = {
      
      import org.apache.spark.{SparkConf, SparkContext}

      // Products
      val products = sqlContext.read.json("/home/jiahong/Desktop/data-engineer/src/main/resources/products")

      val tenCheapProducts = products
            .filter($"price" > 100)
            .filter($"price" < 500)
            .orderBy(rand())
            .select ("id")
            .limit(10)
            .collect
            .map(r => r.getString(0))
            .toList

      val tenExpensiveProducts = products
            .filter($"price" > 500)
            .orderBy(rand())
            .select ("id")
            .limit(10)
            .collect
            .map(r => r.getString(0))
            .toList

      //visits 
      val visits = sqlContext.read.json("/home/jiahong/Desktop/data-engineer/src/main/resources/visit/*")

      val n_visit_log = visits
          .filter($"customer_id" === customerId)
          .count()

      val recommeded = if(n_visit_log > 0) tenExpensiveProducts; else tenCheapProducts;

      return recommeded

    }
}
