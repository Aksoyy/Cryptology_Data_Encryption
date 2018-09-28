import java.io.File;
import java.nio.file.*;

import java.nio.charset.Charset;

import java.security.MessageDigest;
public class grup6v3{
   
   private static Charset UTF8 = Charset.forName("UTF-8");
   
   /**
    * This method does mathematical ‘xor’ operation on two byte of arrays. 
    * @param a byte of array
    * @param b byte of array
    * @return c result of a xor b
    */
   private static byte[] XOR(byte[] a,byte[] b){
      if(a.length!=b.length){
         System.err.println("ERROR -> Array lengthes are different.");
         return null;}
      byte[] c = new byte[a.length];
      for(int i=0;i<c.length;i++)
         c[i]=(byte) (a[i] ^ b[i]);
      return c;
   }
   public static void printArray(byte[] arr){
      System.out.print("[");
      for(int i=0;i<arr.length;i++){
         if(i==arr.length-1)
            System.out.print(arr[i]+"]");
         else
            System.out.print(arr[i]+",");
      }
      System.out.println();
   }
   public static byte[] subArray(byte[] arr,int a,int b){
      
      byte[] temp= new byte[b-a+1];
      int index =0;
      for(int i=a;i<=b;i++)
      {
         temp[index] = arr[i];
         index++;
      }
      return temp; 
      
   }
   
   public static byte[] decryptBlock(byte[] cipher,byte[] key)throws Exception{
      
      MessageDigest digest = MessageDigest.getInstance("SHA-256");            
      byte[] hash = digest.digest(key);
      if(cipher.length!=hash.length){   
         System.err.println("ERROR -> Array lengthes are different.");
         return null;
      } 
      return XOR(XOR(hash,key),cipher);  
   }
   public static byte[] decrypt(byte[] cipher,byte[] key)throws Exception{
      
      int num = 35; 
      int numOfBlock = (cipher.length-num) / 32; 
      byte[] output = new byte[cipher.length];
      
      for(int i=0;i<num;i++)
         output[i]=cipher[i];
      
      for(int i=0;i<numOfBlock;i++){
         
         byte[] outputBlock = decryptBlock(subArray(cipher,i*32+num,(i+1)*32+num-1),key);
         
         for(int j=0;j<outputBlock.length;j++){
            output[num+j+32*i]=outputBlock[j];
         }
         MessageDigest digest = MessageDigest.getInstance("SHA-256");            
         byte[] hash = digest.digest(key);
         key = hash;
      }
      return output;
   }
   public static byte[] encryptBlock(byte[] mess,byte[] key)throws Exception{
      
      MessageDigest digest = MessageDigest.getInstance("SHA-256");            
      byte[] hash = digest.digest(key);
      if(mess.length!=hash.length){   
         System.err.println("ERROR -> Array lengthes are different.");
         return null;
      }
      
      return XOR(XOR(hash,key),mess);  
   }
   public static byte[] encrypt(byte[] mess,byte[] key)throws Exception{
      
      int num = 35; 
      int numOfBlock = (mess.length-num) / 32; 
      byte[]cipher = new byte[mess.length];
      
      for(int i=0;i<num;i++)
         cipher[i]=mess[i];
      for(int i=0;i<numOfBlock;i++){
         
         byte[] cipherBlock = encryptBlock(subArray(mess,i*32+num,(i+1)*32+num-1),key);
         for(int j=0;j<cipherBlock.length;j++){
            cipher[num+j+32*i]=cipherBlock[j];
         }
         MessageDigest digest = MessageDigest.getInstance("SHA-256");            
         byte[] hash = digest.digest(key);
         key = hash;
      }
      
      return cipher;
   }
   private static String UTF8_text(byte[] num)throws Exception{
      // String str = "";
      String str = new String(num,UTF8);
      return str;  
   }
   public static void main(String []args)throws Exception{
      
      
      String file_path = "/Users/ahmetmalal/Desktop/KerasDeepLearning.pdf";  
      File input_file = new File(file_path);     
      byte[] plainText = Files.readAllBytes(input_file.toPath());  
      
      printArray(plainText);
      
      String key = "asdfgytasdfgytrfghtuytorfghtuyto"; 
      long startTime = System.nanoTime();  
      
      //String a = "kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma kriptoloji yaz okulu tübitak grup altı cuma ";
      byte[] cipher = encrypt(plainText,key.getBytes());
      
      //System.out.println("-----: "+UTF8_text(cipher));
      ///Users/ahmetmalal/Desktop/Keras\ Deep\ Learning.pdf
      
      Path path = Paths.get("/Users/ahmetmalal/Desktop/cipher1.pdf");
      Files.write(path, cipher);
      
      
      byte[]output = decrypt(cipher,key.getBytes());  
      Path path2 = Paths.get("/Users/ahmetmalal/Desktop/output1.pdf");
      Files.write(path2,output);
      
      //   System.out.println(UTF8_text(output));
      long endTime = System.nanoTime();
      long duration = (endTime - startTime);   
      System.out.println((double)duration/1000000000 +" seconds."); 
      System.out.println("done!");
      
      
      
      
      
      
   }
}