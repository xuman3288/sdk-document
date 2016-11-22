<?php

//示例公钥
$publicKey = <<<EOT
-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA/VweA5KME/PR7QIwe+Bo
Wf+yM5tRVpaXWOZC7S4SeLT5zyd1gNzKjLHCxAIhsxYUnXHRCdsC+cnKTVfCBuew
v7N2kZCKF+/gMiqSdfiJo3XE7lmrxpIKO6YnWt7Itq/VvMJoTO7g0KkjF/irzI+O
KUj5DSZORHwulA6OXNjUJbGeMdIGX7VHgbk7dv8oqx+FE0bAQ4APtOiSs5agBRdg
9De92tURcj15jztYtPvaRVn5O8ozTEui4Kh2Cmf1fPFKbv5yQyNhHaqMdI2tGPRE
s78wGIiHT1yOmCMeKHCVgElwwgmFnmxKYsBD9XZ9GM6wzt/95M53jh/aNp/+9Y1m
0QIDAQAB
-----END PUBLIC KEY-----
EOT;

$data = json_decode('{"entity":{"openid":"34-70086000145733010","account":"\u7ea2\u4e3d\u662f\u732a\ud83d\udc37","time":1479810865},"sign":"9c754z1NGburQlfCwmls18mNokRMOWCAIYIrxYZN0ocGqOiarH8fVMoO9q8rIzF2wa//TxuKitJ93NL0HDw+YfM6b5Uo9rf7rQmeWDFatnaFOJIvrbN4EJkNxEMZy8mJ5leUsIuGPoCEvtFATf5haXizQ9YJoNbQj8W377Xkfb9yzmGsmbyNoBRU8++K8adxPsOm+l/eQvUEvDQsnIkfEIgyfe8mXEpZFXpOFvCmIrTeTbekLjF7+8K6c44uugCx664FX2ejT0QeJ7dxZwWgR1BmcN7RFlOMQG4v4IHVxmCLdjsklKgAIYQ6B5DtyeXIA/UB4NPesEOu+rNUAC477g=="}', true);

$signData = genSignStr($data['entity']);
echo $signData , "\n";

//$pkeyid = openssl_pkey_get_private(file_get_contents(__DIR__ . '/../example_private_key.txt'));
//openssl_sign($signData, $signature, $pkeyid);
// free the key from memory
//openssl_free_key($pkeyid);
//echo base64_encode($signature);exit;

$keySource = openssl_pkey_get_public($publicKey); 
$sign = base64_decode($data['sign']);
if (openssl_verify($signData, $sign, $keySource) === 1) {

    if ($data['entity']['openid'] < time() - 43200) {
        echo "登录认证过期";
    } else {
        echo '签名验证到成功, openid: ' . $data['entity']['openid'];
    }

} else { 
    echo '签名验证失败'; //签名验证失败返回
}

function genSignStr(array $entity) {

    $signStrs = [];
    ksort($entity);
    foreach ($entity as $k => $v) {
        $signStrs[] = $k . '=' . $v;
    }

    return implode('&', $signStrs);
}
