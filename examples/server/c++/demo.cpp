#include <iostream>
#include <string.h>
#include "CryptHelper.h"



int main(int argc, char ** argv)
{
    //cp公钥
    const std::string cpPkey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA/VweA5KME/PR7QIwe+BoWf+yM5tRVpaXWOZC7S4SeLT5zyd1gNzKjLHCxAIhsxYUnXHRCdsC+cnKTVfCBuewv7N2kZCKF+/gMiqSdfiJo3XE7lmrxpIKO6YnWt7Itq/VvMJoTO7g0KkjF/irzI+OKUj5DSZORHwulA6OXNjUJbGeMdIGX7VHgbk7dv8oqx+FE0bAQ4APtOiSs5agBRdg9De92tURcj15jztYtPvaRVn5O8ozTEui4Kh2Cmf1fPFKbv5yQyNhHaqMdI2tGPREs78wGIiHT1yOmCMeKHCVgElwwgmFnmxKYsBD9XZ9GM6wzt/95M53jh/aNp/+9Y1m0QIDAQAB";
    //cp私钥
    const std::string cpVkey="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQD9XB4DkowT89HtAjB74GhZ/7Izm1FWlpdY5kLtLhJ4tPnPJ3WA3MqMscLEAiGzFhSdcdEJ2wL5ycpNV8IG57C/s3aRkIoX7+AyKpJ1+ImjdcTuWavGkgo7pida3si2r9W8wmhM7uDQqSMX+KvMj44pSPkNJk5EfC6UDo5c2NQlsZ4x0gZftUeBuTt2/yirH4UTRsBDgA+06JKzlqAFF2D0N73a1RFyPXmPO1i0+9pFWfk7yjNMS6LgqHYKZ/V88Upu/nJDI2Edqox0ja0Y9ESzvzAYiIdPXI6YIx4ocJWASXDCCYWebEpiwEP1dn0YzrDO3/3kzneOH9o2n/71jWbRAgMBAAECggEBAOqhKFp+mKLRpZMr9+KAi6LtHQaVy9m4VCjL9SbZB9opHz8sd7ECx8FLzebfYn4K8MRt5vyNik/xBBxkLRdf8VtWw3NCu+A3ERTkQAZoSG7MXbet61pVXIyo7sFEl/Q3GmVYVGcKwb/7IrjMXa1vNGQfZCU9WDYsGqaaZFSvqKfN4dIkT6kySXkVrZuoW9i03EOC5YoxtlvPI/tpSao+C/tIytVf0wLzdy3eOCdxylRdhExNTQX+wh62QzsnXwZZjIyg96YOWZanud5SKB9EydPIEgvvPW0bPnOU7Xa/6C/h/3+7mhY8Rm/ZsYXhaOLhsf4BFysMGXjOndM4s9u6cMECgYEA/sF3gvcqL3QFwlG44Ui0uA6AXlwzXGKDU/eUlgSEt/RrqHKBndu4pB5WrsrV5zW2fSyiMo7X5WL8chnj9+1a0gzfkXgW95HAV4lO+Iubjc1oXWBgh7HcKvWFi117gCSpbvFGUeBG6EQy9TxeOJAGreJ3tOGPDTtY7GpNvcYHVv0CgYEA/pjnsPTk15aFMtnUyFD8QDyKaiY8ULteAgBEW9EhPJcrkhVA6FBr/TPtstmOBptSu2f7ImtwGGiHlJPtctkqGwVZ/yZvo3J//qqkQ86R9O+cXLL86xjq/SRukDrHmc35iG4zG18pxGYOJ1y91q3STLkjEi2IP/h6M1H4olD8+WUCgYEAzfaqYfbkY1MhKNaba4A1o60Nubw/RgzbcnJy7cqfZn404A4bnv26TO2+VTjmMTTq4k7In6l/ARTDVph6B8MdLz97LQfzQZrT77GyaB2u2LleDEhUIsD4aL7xhhhBNfvuBCULHjz99uDUdhKldoNy8vhqoZHO7vqxTQow6iIFYx0CgYEAnSyyZHlbSYjgJDt8dEoi4Fk/rjZ6p/B0IdIeYXyjAy/kMyQx1PnJISDMzswmNQfwtEeGL4KaSkdF5EDKp0tQBixwmecVppcKU6LvE/lT/B1sr4HESDMy5yJ1q07OaLJTKZm2rWA7fcbvV/eWLxFSMhFdiFxZr/xrHIGveC19b00CgYBjY+yTxn7UGrudGAHE4DXuuzfKmyxJcJaJXfJeXQ/zAnryFvfEr30G7VxTprF0Mizf3blUMNFq/uwGin7fsZq0R7AmBW2sC+LpOHX9+Bi3Ui9ZgSmzT0F8diXnNGa92P77mQwD0KDASOiqcTF44ToHQ2dR0DqgN+LILINh2Chz0Q==";

    //请求报文与签名
    const std::string reqData = "abcd6.001123GMG0011-12341399633295037630HWDPID0006140497514410000001100813543.01";
 
 /*
    //签名
    EVP_PKEY* vkey = UTILS::CryptHelper::getKeyByPKCS1(cpVkey, 1);
    if(!vkey)
    {
        std::cout<<"getKeyByPKCS1 error"<<std::endl;
    }

    std::string reqSign;
    UTILS::CryptHelper::sha1WithRsa(reqData, reqSign, vkey);
    std::cout<<"reqSign:"<<reqSign<<std::endl;
*/

	std::string reqSign = "m2S0nl5i/bNqUqPMH8+qL3f4PgG/bINqDafdxo1pzVP/oXiItT4hKNkuXVXbBOlCrsG3wDkIMfWgBeo3JHrDqlS4wF6MPNVscc8JnaHn2IzeYnbew8r+dyLB0XDBkugUcEQHlO4Aat82PRWoRWFRDbdBK9mClbBsgEObW7+kcAMFoJlpxedjzk7LzDcPf8qXfLmr0HfU70RRj6/fhlKA3ZA+PL7bzluKg53v3HARRV5MPm90U92iWuL8zecyZ1M775k3pEswW8IllQNc4EuihY5zUJozUxeRmoqLxgZJI3aECy/dKlDWcHwWg083TGX3/nynYF6JlCj5tZ6WE3zuUQ==";

    //验签
    EVP_PKEY* pkey = UTILS::CryptHelper::getKeyByPKCS1(cpPkey, 0);
    if(!pkey)
    {
        std::cout<<"getKeyByPKCS1 error"<<std::endl;
    }

    if(UTILS::CryptHelper::verifySha1WithRsa(reqData, reqSign, pkey))
    {
        std::cout<<"verify sign success"<<std::endl;
    }
    else
    {
        std::cout<<"verify sign error"<<std::endl;
    }

    return 0;

}
