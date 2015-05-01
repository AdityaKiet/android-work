import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor {
	public static String encryptSHA512(String target) {
		try {
			MessageDigest sh = MessageDigest.getInstance("SHA-512");
			sh.update(target.getBytes());
			StringBuffer sb = new StringBuffer();
			for (byte b : sh.digest())
				sb.append(Integer.toHexString(0xff & b));
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	public static void main(String[] args) {
		System.out.println(encryptSHA512("aditya"));
		System.out.println("5ec6ca19b65067a02c7a6ed317dee4b51fee3785e87baeaa6114b5578faf51f4db8466b63bfc99ad993b7d6ad77bf585ca9cbcae0".length());
	}
}
