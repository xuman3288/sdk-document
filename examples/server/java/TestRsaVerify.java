import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * 
 * JDK 1.8 以上, 否则请修改Base64方式
 */
public class TestRsaVerify {

	private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA/VweA5KME/PR7QIwe+Bo"
			+ "Wf+yM5tRVpaXWOZC7S4SeLT5zyd1gNzKjLHCxAIhsxYUnXHRCdsC+cnKTVfCBuew"
			+ "v7N2kZCKF+/gMiqSdfiJo3XE7lmrxpIKO6YnWt7Itq/VvMJoTO7g0KkjF/irzI+O"
			+ "KUj5DSZORHwulA6OXNjUJbGeMdIGX7VHgbk7dv8oqx+FE0bAQ4APtOiSs5agBRdg"
			+ "9De92tURcj15jztYtPvaRVn5O8ozTEui4Kh2Cmf1fPFKbv5yQyNhHaqMdI2tGPRE"
			+ "s78wGIiHT1yOmCMeKHCVgElwwgmFnmxKYsBD9XZ9GM6wzt/95M53jh/aNp/+9Y1m"
			+ "0QIDAQAB";

	public static void main(String[] args) {

		String account = "abcd";
		String amount = "6.00";
		String channel = "1";
		String extra = "123";
		String game_id = "GMG001";
		String openid = "1-1234";
		String order_id = "1399633295037630";
		String product_id = "HWDPID0006";
		String time = "1404975144";
		String transaction_id = "1000000110081354";
		String version = "3.0";
		String zone_id = "1";

		String signString = account + amount + channel + extra + game_id
				+ openid + order_id + product_id + time + transaction_id
				+ version + zone_id;
		String sign = "m2S0nl5i/bNqUqPMH8+qL3f4PgG/bINqDafdxo1pzVP/oXiItT4hKNkuXVXbBOlCrsG3wDkIMfWgBeo3JHrDqlS4wF6MPNVscc8JnaHn2IzeYnbew8r+dyLB0XDBkugUcEQHlO4Aat82PRWoRWFRDbdBK9mClbBsgEObW7+kcAMFoJlpxedjzk7LzDcPf8qXfLmr0HfU70RRj6/fhlKA3ZA+PL7bzluKg53v3HARRV5MPm90U92iWuL8zecyZ1M775k3pEswW8IllQNc4EuihY5zUJozUxeRmoqLxgZJI3aECy/dKlDWcHwWg083TGX3/nynYF6JlCj5tZ6WE3zuUQ==";
System.out.println(PUBLIC_KEY);
		System.out.println(signString);
		System.out.println(rsaVerify(signString, sign));
	}

	public static boolean rsaVerify(String data, String sign) {

		// 解密由base64编码的公钥
		byte[] keyBytes = Base64.getDecoder().decode(PUBLIC_KEY);

		// 构造X509EncodedKeySpec对象
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

		try {
			// KEY_ALGORITHM 指定的加密算法
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");

			// 取公钥匙对象
			PublicKey pubKey = keyFactory.generatePublic(keySpec);

			Signature signature = Signature.getInstance("SHA1withRSA");
			signature.initVerify(pubKey);
			signature.update(data.getBytes());

			// 验证签名是否正常
			return signature.verify(Base64.getDecoder().decode(sign));
		} catch (Exception e) {
			System.out.println("Error:" + e.getMessage());
			return false;
		}
	}
}
