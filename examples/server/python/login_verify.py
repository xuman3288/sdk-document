# -*- coding: utf-8 -*-
# Python 3
# pip install rsa
import rsa
import base64
import json

pubPem = '''-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA/VweA5KME/PR7QIwe+Bo
Wf+yM5tRVpaXWOZC7S4SeLT5zyd1gNzKjLHCxAIhsxYUnXHRCdsC+cnKTVfCBuew
v7N2kZCKF+/gMiqSdfiJo3XE7lmrxpIKO6YnWt7Itq/VvMJoTO7g0KkjF/irzI+O
KUj5DSZORHwulA6OXNjUJbGeMdIGX7VHgbk7dv8oqx+FE0bAQ4APtOiSs5agBRdg
9De92tURcj15jztYtPvaRVn5O8ozTEui4Kh2Cmf1fPFKbv5yQyNhHaqMdI2tGPRE
s78wGIiHT1yOmCMeKHCVgElwwgmFnmxKYsBD9XZ9GM6wzt/95M53jh/aNp/+9Y1m
0QIDAQAB
-----END PUBLIC KEY-----'''

#params = '''{"entity":{"openid":"34-70086000145733010","account":"\u7ea2\u4e3d\u662f\u732a\ud83d\udc37","time":1479810865},"sign":"9c754z1NGburQlfCwmls18mNokRMOWCAIYIrxYZN0ocGqOiarH8fVMoO9q8rIzF2wa//TxuKitJ93NL0HDw+YfM6b5Uo9rf7rQmeWDFatnaFOJIvrbN4EJkNxEMZy8mJ5leUsIuGPoCEvtFATf5haXizQ9YJoNbQj8W377Xkfb9yzmGsmbyNoBRU8++K8adxPsOm+l/eQvUEvDQsnIkfEIgyfe8mXEpZFXpOFvCmIrTeTbekLjF7+8K6c44uugCx664FX2ejT0QeJ7dxZwWgR1BmcN7RFlOMQG4v4IHVxmCLdjsklKgAIYQ6B5DtyeXIA/UB4NPesEOu+rNUAC477g=="}'''
params = '''{"entity":{"openid":"1-123123","account":"test","time":1482313093},"sign":"m7JTn\/y3IpB084vyeqoR9ysZ5\/GeowcsO3KDrDsaa8Fof2Xjq4gKdk5eDLh0nTRxZslfbet5AWs+p1M0rtF8Jan8T2VxCW\/czoCkrj4o\/xnYtb3wdenCXAT7LUoydjTmd+cf0I9kw0DjkPDurrT9kNqxzw6dq1A6EFZAts4f0\/H5+7kn81rzPq1RkWOM6OGm8R2D2WW\/jHfqZDo1mvfZvIzkA\/F0M62z2VsUK821BUkgoT23dByCdeqgx4hauJTHlnvg2\/MplDYMDONHai6gTFWb4FQgY2wJq1BIWCyXgDuid6n9Ck0m6paJEfOffbK7BgwH3ssaF7xYGD2sjEuCFw=="}'''
params = json.loads(params)

#print(params)

def genSignStr(entity):
    vals = []
    items = sorted(entity.items(), key=lambda d:str(d[0]))

    for (k, v) in items:
        v = str(v) if v else ""
        vals.append(k+"="+v)

    return "&".join(vals)

pubKey = rsa.PublicKey.load_pkcs1_openssl_pem(pubPem);
sign = base64.b64decode(params['sign']);
data = genSignStr(params['entity'])

#print(params['entity']['account'])

print(data.encode("unicode_escape"))
print(rsa.verify(data.encode("unicode_escape"), sign, pubKey));
