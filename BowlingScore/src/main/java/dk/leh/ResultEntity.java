package dk.leh;

public class ResultEntity {

   private final String className = this.getClass().getSimpleName();

   private boolean success;
   private int[] input;

   public boolean getSuccess() {
      return success;
   }

   public void setSuccess(boolean success) {
      this.success = success;
   }

   public int[] getInput() {
      return input;
   }

   public void setInput(int[] input) {
      if (input == null) {
         this.input = null;
      }
      else if (input.length < 1) {
         this.input = new int[0];
      }
      else {
         this.input = new int[input.length];
         System.arraycopy(input, 0, this.input, 0, input.length);
      }
   }

   @Override
   public String toString() {

      String str = "";

      str += className + "[";
      str += "success=" + success;
      str += ",";
      str += "input=";
      if (input == null) {
         str += "null";
      }
      else if (input.length < 1) {
         str += "empty";
      }
      else {
         str += "[";
         for (int i = 0; i < input.length; i++) {
            str += input[i];
            if (i < input.length - 1) {
               str += ",";
            }
         }
         str += "]";
      }
      str += "]";
      return str;
   }
}
