package dk.leh;

import java.util.Arrays;

import com.google.gson.Gson;

import ch.qos.logback.classic.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ApiController {

   private final boolean[] swDebug = {true};

   private final static Logger logger = (Logger) LoggerFactory.getLogger(ApiController.class);

   private final String API_ENDPOINT = "http://13.74.31.101/api/points";

   private final PointsAlgorithm pointsAlgorithm = new PointsAlgorithm();

   @Autowired
   RestTemplate restTemplate;

   @RequestMapping(value = "/template/getpointsvalidated", method = RequestMethod.GET)
   public boolean getPointsValidated(boolean swForceFail) throws Exception {
      String methodName = "getPointsValidated";

      boolean swOk = false;
      boolean swSuccess = true;

      try {
         String pointList = getPoints();

         Gson gson = new Gson();

         PointsEntity pointsEntity = gson.fromJson(pointList, PointsEntity.class);

         if (swDebug[0]) {
            logger.info("++" + methodName + ":" + pointsEntity);
         }

         int[] pointsResult = pointsAlgorithm.calculatePoints(pointsEntity);

         if (swForceFail) {
            if (pointsResult != null && pointsResult.length > 0) {
               // Add one to last element of pointsResult to make it fail
               pointsResult[pointsResult.length - 1]++;
               swSuccess = false;
            }
         }

         String pointsResultStr = formatPoints(pointsEntity.getToken(), pointsResult);

         if (swDebug[0]) {
            logger.info("++" + methodName + ":" + "pointsResultJson=" + pointsResultStr);
         }

         String resultStr = postPointsResult(pointsResultStr);

         if (swDebug[0]) {
            logger.info("++" + methodName + ":" + "resultStr=" + resultStr);
         }

         ResultEntity resultEntity = gson.fromJson(resultStr, ResultEntity.class);

         if (swDebug[0]) {
            logger.info("++" + methodName + ":" + resultEntity);
         }

         if (swDebug[0]) {
            logger.info("++" + methodName + ":" + "Expected:" + swSuccess);
            logger.info("++" + methodName + ":" + "Result..:" + resultEntity.getSuccess());
         }

         swOk = resultEntity.getSuccess();
      }

      catch (Exception ex) {
         logger.warn("++" + methodName + ":" + ex.toString());
      }

      return swOk;
   }

   @RequestMapping(value = "/template/getpoints", method = RequestMethod.GET)
   public String getPoints() throws Exception {

      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);

      return restTemplate.exchange(API_ENDPOINT, HttpMethod.GET, httpEntity, String.class).getBody();
   }

   @PostMapping(value = "/template/postpointsresult", consumes = "application/json", produces = "application/json")
   public String postPointsResult(@RequestBody String pointsResult) throws Exception {

      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.setContentType(MediaType.APPLICATION_JSON);

      HttpEntity<String> httpEntity = new HttpEntity<String>(pointsResult, httpHeaders);

      return restTemplate.exchange(API_ENDPOINT, HttpMethod.POST, httpEntity, String.class).getBody();
   }

   /*
    * {"token":"vR3ujAGBoiXTkbSwsW3zSZVg4juF4YIg","points":[8,27,41,47,52]}
    */
   public String formatPoints(String token, int[] pointsResult) {

      String resultStr = "";

      resultStr += "{";
      resultStr += "\"token\":";
      resultStr += "\"" + token + "\"";
      resultStr += ",";
      resultStr += "\"points\":";
      resultStr += "[" + intArrayToStr(pointsResult) + "]";
      resultStr += "}";

      return resultStr;
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
