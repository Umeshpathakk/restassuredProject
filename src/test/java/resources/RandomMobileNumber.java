package resources;

import java.util.Random;

public class RandomMobileNumber
{
    public static long randomMobileNumber()
     {
         Random rand=new Random();
         long a=  rand.nextInt(10);
         long b=  rand.nextInt(10);
         long c=  rand.nextInt(10);
         long d=  rand.nextInt(10);

         long num=(1000*a)+(100*b)+(10*c)+d;
         long num2=9876540000L+num;
         return num2;

     }
}
