package com.car.tests

import com.car._
import junit.framework.Assert._
import _root_.android.test.AndroidTestCase

class AndroidTests extends AndroidTestCase {
  def testPackageIsCorrect() {
    assertEquals("com.car", getContext.getPackageName)
  }
}
