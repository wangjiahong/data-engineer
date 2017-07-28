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

      // 10 products with price between 100 and 500 euro
      val tenCheapProducts = products
            .filter($"price" > 100)
            .filter($"price" < 500)
            .orderBy(rand())
            .select ("id")
            .limit(10)
            .collect
            .map(r => r.getString(0))
            .toList

      // 10 products with price more than 500 euro
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

      // number of logs that this customer_id has
      val n_visit_log = visits
          .filter($"customer_id" === customerId)
          .count()


      // if this user has 2 or more than 2 visit logs, recommend him/her random 10 expensive prducts;
      // if this user has only 0 or 1 visit logs, recommend him/her 10 random cheap products;
      val recommeded = if(n_visit_log > 1) tenExpensiveProducts; else tenCheapProducts;

      return recommeded

    }
}
