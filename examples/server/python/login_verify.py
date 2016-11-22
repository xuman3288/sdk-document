# -*- coding: utf-8 -*-
# Python 3
# pip install rsa
import rsa
import base64

pubPem = '''-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA/VweA5KME/PR7QIwe+Bo
Wf+yM5tRVpaXWOZC7S4SeLT5zyd1gNzKjLHCxAIhsxYUnXHRCdsC+cnKTVfCBuew
v7N2kZCKF+/gMiqSdfiJo3XE7lmrxpIKO6YnWt7Itq/VvMJoTO7g0KkjF/irzI+O
KUj5DSZORHwulA6OXNjUJbGeMdIGX7VHgbk7dv8oqx+FE0bAQ4APtOiSs5agBRdg
9De92tURcj15jztYtPvaRVn5O8ozTEui4Kh2Cmf1fPFKbv5yQyNhHaqMdI2tGPRE
s78wGIiHT1yOmCMeKHCVgElwwgmFnmxKYsBD9XZ9GM6wzt/95M53jh/aNp/+9Y1m
0QIDAQAB
-----END PUBLIC KEY-----'''

entity = {"openid":"34-70086000145733010","account":"\u7ea2\u4e3d\u662f\u732a\ud83d\udc37","time":1479810865}
sign  = "9c754z1NGburQlfCwmls18mNokRMOWCAIYIrxYZN0ocGqOiarH8fVMoO9q8rIzF2wa//TxuKitJ93NL0HDw+YfM6b5Uo9rf7rQmeWDFatnaFOJIvrbN4EJkNxEMZy8mJ5leUsIuGPoCEvtFATf5haXizQ9YJoNbQj8W377Xkfb9yzmGsmbyNoBRU8++K8adxPsOm+l/eQvUEvDQsnIkfEIgyfe8mXEpZFXpOFvCmIrTeTbekLjF7+8K6c44uugCx664FX2ejT0QeJ7dxZwWgR1BmcN7RFlOMQG4v4IHVxmCLdjsklKgAIYQ6B5DtyeXIA/UB4NPesEOu+rNUAC477g=="

data = account + amount + channel + extra + game_id + openid + order_id + product_id + time + transaction_id + version + zone_id;

sign = "m2S0nl5i/bNqUqPMH8+qL3f4PgG/bINqDafdxo1pzVP/oXiItT4hKNkuXVXbBOlCrsG3wDkIMfWgBeo3JHrDqlS4wF6MPNVscc8JnaHn2IzeYnbew8r+dyLB0XDBkugUcEQHlO4Aat82PRWoRWFRDbdBK9mClbBsgEObW7+kcAMFoJlpxedjzk7LzDcPf8qXfLmr0HfU70RRj6/fhlKA3ZA+PL7bzluKg53v3HARRV5MPm90U92iWuL8zecyZ1M775k3pEswW8IllQNc4EuihY5zUJozUxeRmoqLxgZJI3aECy/dKlDWcHwWg083TGX3/nynYF6JlCj5tZ6WE3zuUQ=="
pubKey = rsa.PublicKey.load_pkcs1_openssl_pem(pubPem);
sign = base64.b64decode(sign);

print(rsa.verify(data.encode("utf-8"), sign, pubKey));

def genSignStr(entity):

