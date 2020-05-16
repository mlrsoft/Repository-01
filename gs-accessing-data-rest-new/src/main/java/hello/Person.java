package hello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {

   private final boolean[] swDebug = {true};

   private final String className = this.getClass().getSimpleName();

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private long id;

   private String firstName;
   private String lastName;

   public String getFirstName() {
      String methodName = "getFirstName";
      if (swDebug[0]) {
         System.out.println("++" + className + "." + methodName + ":" + "Start");
      }
      return firstName;
   }

   public void setFirstName(String firstName) {
      String methodName = "setFirstName";
      if (swDebug[0]) {
         System.out.println("++" + className + "." + methodName + ":" + "Start");
      }
      this.firstName = firstName;
   }

   public String getLastName() {
      String methodName = "getLastName";
      if (swDebug[0]) {
         System.out.println("++" + className + "." + methodName + ":" + "Start");
      }
      return lastName;
   }

   public void setLastName(String lastName) {
      String methodName = "setLastName";
      if (swDebug[0]) {
         System.out.println("++" + className + "." + methodName + ":" + "Start");
      }
      this.lastName = lastName;
   }
}
