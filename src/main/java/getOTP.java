import com.google.appengine.repackaged.org.apache.commons.codec.binary.Base32;
import com.google.appengine.repackaged.org.apache.commons.codec.binary.Hex;


import java.util.Scanner;

public class getOTP {

    public static String getTOTPCode(String secretKey) {
        String normalizedBase32Key = secretKey.replace(" ", "").toUpperCase();
        Base32 base32 = new Base32();
        byte[] bytes = base32.decode(normalizedBase32Key);
        String hexKey = Hex.encodeHexString(bytes);
        long time = (System.currentTimeMillis() / 1000) / 30;
        String hexTime = Long.toHexString(time);
        return TOTP.generateTOTP(hexKey, hexTime, "6");

    }

    public static void main(String[] args){

        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter a Key: ");
        String key = reader.nextLine();
        //once finished
        reader.close();

        //String secretKey = "6CNXAYNFD3AXQPADVSNGL5UQZ52GNPIOLNAIV4UVHXYZUAOUGZGQ";
        //String secretKey = "5BMNN5KJLRFPHRRHVXBDLPHDLI3HZ3VDK7AIG5IWUA3HB5NR77UQ";

        if(!key.isEmpty()){
            String secretKey = key.trim();

            String lastCode = null;
            while (true) {
                String code = getTOTPCode(secretKey);
                if (!code.equals(lastCode)) {
                    // output a new 6 digit code
                    System.out.println(code);

                }
                lastCode = code;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {};
            }

        }



    }
}
