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

import org.scalatest.FunSuite
import org.scalatest.Matchers._

/**
  * A test for [[LocalSparkRunner]]. Implementing tests
  * is not required for this puzzle. However, feel free
  * to write some if you find them helpful.
  */
class Test extends FunSuite {

    test("localSparkRunner")(LocalSparkRunner.run(sc => {
        sc.parallelize(1 :: 2 :: 3 :: Nil).sum() shouldBe 6
    }))

}
