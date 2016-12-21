<?php

$priKey = file_get_contents(__DIR__ . '/../example_private_key.txt');
$pubKey = file_get_contents(__DIR__ . '/../example_public_key.txt');
$priRes = openssl_get_privatekey($priKey);
$pubRes = openssl_get_publickey($pubKey);
$source = genSignStr(json_decode('{"openid":"1-123123","account":"test","time":1482313093}', true));
echo "Source: " . $source, "\n";
$sign = '';
openssl_sign($source, $sign, $priRes); 
echo "Sign: " . json_encode(base64_encode($sign)), "\n";
//Sign: 


$result = openssl_verify($source, $sign, $pubRes);

var_dump($result);


function genSignStr(array $entity) {

    $signStrs = [];
    ksort($entity);
    foreach ($entity as $k => $v) {
        $signStrs[] = $k . '=' . $v;
    }

    return implode('&', $signStrs);
}
