import java.net.InetAddress;
 
class GetIPAddress
{
   public static void main(String args[]) throws Exception
   {
      System.out.println(InetAddress.getLocalHost());
   }
}