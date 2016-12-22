import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

import org.json.JSONObject; //https://github.com/stleary/JSON-java

/**
 * 
 * JDK 1.8 浠ヤ笂, 鍚﹀垯璇蜂慨鏀笲ase64鏂瑰紡
 */
public class LoginRsaVerify {

	private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA/VweA5KME/PR7QIwe+Bo"
			+ "Wf+yM5tRVpaXWOZC7S4SeLT5zyd1gNzKjLHCxAIhsxYUnXHRCdsC+cnKTVfCBuew"
			+ "v7N2kZCKF+/gMiqSdfiJo3XE7lmrxpIKO6YnWt7Itq/VvMJoTO7g0KkjF/irzI+O"
			+ "KUj5DSZORHwulA6OXNjUJbGeMdIGX7VHgbk7dv8oqx+FE0bAQ4APtOiSs5agBRdg"
			+ "9De92tURcj15jztYtPvaRVn5O8ozTEui4Kh2Cmf1fPFKbv5yQyNhHaqMdI2tGPRE"
			+ "s78wGIiHT1yOmCMeKHCVgElwwgmFnmxKYsBD9XZ9GM6wzt/95M53jh/aNp/+9Y1m"
			+ "0QIDAQAB";

	public static void main(String[] args) {

		String jsonStr = "{\"entity\":{\"openid\":\"34-70086000145733010\",\"account\":\"\u7ea2\u4e3d\u662f\u732a\ud83d\udc37\",\"time\":1479810865},\"sign\":\"9c754z1NGburQlfCwmls18mNokRMOWCAIYIrxYZN0ocGqOiarH8fVMoO9q8rIzF2wa//TxuKitJ93NL0HDw+YfM6b5Uo9rf7rQmeWDFatnaFOJIvrbN4EJkNxEMZy8mJ5leUsIuGPoCEvtFATf5haXizQ9YJoNbQj8W377Xkfb9yzmGsmbyNoBRU8++K8adxPsOm+l/eQvUEvDQsnIkfEIgyfe8mXEpZFXpOFvCmIrTeTbekLjF7+8K6c44uugCx664FX2ejT0QeJ7dxZwWgR1BmcN7RFlOMQG4v4IHVxmCLdjsklKgAIYQ6B5DtyeXIA/UB4NPesEOu+rNUAC477g==\"}";

        JSONObject params = new JSONObject(jsonStr);
		String signString = genSignStr(params.getJSONObject("entity"));
		String sign = params.getString("sign");

		System.out.println(signString);
		System.out.println(rsaVerify(signString, sign));
	}

	public static String genSignStr(JSONObject entity) {
		Object[] keys = entity.keySet().toArray();
		Arrays.sort(keys);

		String key;
		String val;
		String out = "";
		for (int i = 0; i < keys.length; i++) { 
			key = (String) keys[i];
			val = entity.isNull(key) ? "" : "" + entity.get(key);
			out += key + "=" + val + "&";
		}

		return out.substring(0, out.length() -1);
	}

	public static String join(String join, String[] strAry){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < strAry.length; i++){
             if ( i == (strAry.length - 1)) {
                 sb.append(strAry[i]);
             } else {
                 sb.append(strAry[i]).append(join);
             }
        }
        
        return new String(sb);
    }


	public static boolean rsaVerify(String data, String sign) {

		// 瑙ｅ瘑鐢眀ase64缂栫爜鐨勫叕閽�
		byte[] keyBytes = Base64.getDecoder().decode(PUBLIC_KEY);

		// 鏋勯�燲509EncodedKeySpec瀵硅薄
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

		try {
			// KEY_ALGORITHM 鎸囧畾鐨勫姞瀵嗙畻娉�
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");

			// 鍙栧叕閽ュ寵瀵硅薄
			PublicKey pubKey = keyFactory.generatePublic(keySpec);

			Signature signature = Signature.getInstance("SHA1withRSA");
			signature.initVerify(pubKey);
			signature.update(data.getBytes());

			// 楠岃瘉绛惧悕鏄惁姝ｅ父
			return signature.verify(Base64.getDecoder().decode(sign));
		} catch (Exception e) {
			System.out.println("Error:" + e.getMessage());
			return false;
		}
	}
}
