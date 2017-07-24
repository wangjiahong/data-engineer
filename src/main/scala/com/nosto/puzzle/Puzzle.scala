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
    def recommend(customerId: String): List[String]
}
