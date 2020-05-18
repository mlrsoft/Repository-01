package dk.leh;

import ch.qos.logback.classic.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

   private final boolean[] swDebug = {true};

   private final static Logger logger = (Logger) LoggerFactory.getLogger(ApplicationTest.class);

   private PointsEntity pointsEntity_01;
   private int[][] points_01 = {{3, 7}, {10, 0}, {8, 2}, {8, 1}, {10, 0}, {3, 4}, {7, 0}, {5, 5}, {3, 2}, {2, 5}};
   private int[] pointsCalculated_01 = {20, 40, 58, 67, 84, 91, 98, 111, 116, 123};

   private final PointsAlgorithm pointsAlgorithm = new PointsAlgorithm();

   @Autowired
   private ApiController apiController;

   @Before
   public void init() throws Exception {

      pointsEntity_01 = new PointsEntity();
      pointsEntity_01.setPoints(points_01);
   }

   @Ignore
   public void testCalculatePoints() throws Exception {
      String methodName = "testCalculatePoints";

      String expected;
      String resultStr;

      int[] pointsResult = pointsAlgorithm.calculatePoints(pointsEntity_01);

      expected = intArrayToStr(pointsCalculated_01);
      resultStr = intArrayToStr(pointsResult);

      if (swDebug[0]) {
         logger.info("++" + methodName + ":" + "Expected:" + expected);
         logger.info("++" + methodName + ":" + "Result..:" + resultStr);
      }

      assertNotNull(pointsCalculated_01);
      assertNotNull(pointsResult);
      assertEquals(expected, resultStr);
   }

   @Test
   public void testGetPointsValidated_success() throws Exception {
      String methodName = "testGetPointsValidated_success";

      boolean swForceFail = false;
      boolean swOk;

      for (int i = 0; i < 1; i++) {

         swOk = apiController.getPointsValidated(swForceFail);

         if (swDebug[0]) {
            logger.info("++" + methodName + ":" + "Expected " + i + ":" + true);
            logger.info("++" + methodName + ":" + "Result.. " + i + ":" + swOk);
         }

         assertTrue(swOk);

         Thread.sleep(1200);
      }
   }

   @Test
   public void testGetPointsValidated_fail() throws Exception {
      String methodName = "testGetPointsValidated_fail";

      boolean swForceFail = true;
      boolean swOk = apiController.getPointsValidated(swForceFail);

      if (swDebug[0]) {
         logger.info("++" + methodName + ":" + "Expected:" + false);
         logger.info("++" + methodName + ":" + "Result..:" + swOk);
      }

      assertTrue(!swOk);
   }

   private String intArrayToStr(int[] intArray) {

      String str = "";

      if (intArray == null) {
         str += "null";
      }
      else if (intArray.length < 1) {
         str += "empty";
      }
      else {
         for (int i = 0; i < intArray.length; i++) {
            str += intArray[i];
            if (i < intArray.length - 1) {
               str += ",";
            }
         }
      }

      return str;
   }
}
