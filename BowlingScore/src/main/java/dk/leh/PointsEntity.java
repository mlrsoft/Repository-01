package dk.leh;

public class PointsEntity {

   private final String className = this.getClass().getSimpleName();

   private int[][] points;
   private String token;

   public int[][] getPoints() {
      return points;
   }

   public void setPoints(int[][] points) {
      if (points == null) {
         this.points = null;
      }
      else if (points.length < 1) {
         this.points = new int[0][0];
      }
      else {
         this.points = new int[points.length][points[0].length];
         System.arraycopy(points, 0, this.points, 0, points.length);
      }
   }

   public String getToken() {
      return token;
   }

   public void setToken(String token) {
      this.token = token;
   }

   @Override
   public String toString() {

      String str = "";

      str += className + "[";
      str += "points=";
      if (points == null) {
         str += "null";
      }
      else if (points.length < 1) {
         str += "empty";
      }
      else {
         str += "[";
         for (int i = 0; i < points.length; i++) {
            str += "[";
            if (points[i].length > 1) {
               str += points[i][0];
               str += ",";
               str += points[i][1];
            }
            str += "]";
            if (i < points.length - 1) {
               str += ",";
            }
         }
         str += "]";
      }
      str += ",";
      str += "token=";
      if (token == null) {
         str += "null";
      }
      else {
         str += token;
      }
      str += "]";
      return str;
   }
}
