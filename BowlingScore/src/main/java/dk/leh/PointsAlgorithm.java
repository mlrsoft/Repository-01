package dk.leh;

/**
 * Ten-pin bowling
 * 
 * Traditional scoring
 * 
 * Traditional scoring of a strike:
 * Frame one: 10 + (3 + 6) = 19
 * Frame two: 3 + 6 = 9 → Total = 28
 * 
 * Traditional scoring of a spare:
 * Frame one: (7 + 3) + 4 = 14
 * Frame two: 4 + 2 = 6 → Total = 20
 * 
 * In traditional scoring, one point is scored for each pin that is knocked over, 
 * and when less than all ten pins are knocked down in two rolls in a frame (an open frame),
 * the frame is scored with the total number of pins knocked down. 
 * However, when all ten pins are knocked down with either the first or second rolls of a
 * frame (a mark), bonus pins are awarded as follows.
 * 
 * Strike: When all ten pins are knocked down on the first roll (marked "X" on the scoresheet),
 * the frame receives ten pins plus a bonus of pinfall on the next two rolls
 * (not necessarily the next two frames).
 * A strike in the tenth (final) frame receives two extra rolls for bonus pins.
 * 
 * Spare: When a second roll of a frame is needed to knock down all ten pins
 * (marked "/" on the scoresheet), the frame receives ten pins plus a bonus of pinfall
 * in the next roll (not necessarily the next frame). A spare in the first two rolls in 
 * the tenth (final) frame receives a third roll for bonus pins.
 *
 */
public class PointsAlgorithm {

   public int[] calculatePoints(PointsEntity pointsEntity) {

      int pointsResult[] = null;

      if (pointsEntity != null) {
         int[][] points = pointsEntity.getPoints();
         int pointsBonus;
         int idx;
         if (points != null && points.length > 0) {
            pointsResult = new int[points.length];
            for (int i = 0; i < points.length; i++) {
               for (int j = i; j < points.length; j++) {
                  pointsResult[j] += (points[i][0] + points[i][1]);
               }
            }
            for (int i = 0; i < points.length - 1; i++) {
               pointsBonus = 0;
               if (points[i][0] == 10 && points[i][1] == 0) {
                  // Strike
                  idx = i + 1;
                  pointsBonus += (points[idx][0] + points[idx][1]);
                  if (points[idx][0] == 10 && points[idx][1] == 0) {
                     if (i < points.length - 2) {
                        idx = i + 2;
                        pointsBonus += points[idx][0];
                     }
                  }
               }
               else if ((points[i][0] + points[i][1]) == 10) {
                  // Spare
                  idx = i + 1;
                  pointsBonus += points[idx][0];
               }
               if (pointsBonus > 0) {
                  for (int j = i; j < points.length; j++) {
                     pointsResult[j] += pointsBonus;
                  }
               }
            }
         }
      }
      return pointsResult;
   }

}
