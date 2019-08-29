/*
 * Copyright 2017 Alibaba Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.cloudapi.sdk.util;

import com.alibaba.cloudapi.sdk.constant.HttpConstant;
import com.alibaba.cloudapi.sdk.constant.SdkConstant;

import com.alibaba.cloudapi.sdk.exception.SdkException;
import com.alibaba.cloudapi.sdk.model.ApiHttpMessage;
import com.alibaba.cloudapi.sdk.model.ApiRequest;
import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.alibaba.cloudapi.sdk.signature.ISignerFactory;
import com.alibaba.cloudapi.sdk.signature.ISinger;
import com.alibaba.cloudapi.sdk.signature.SignerFactoryManager;
import org.apache.commons.codec.binary.Base64;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import java.util.TreeMap;

/**
 * 签名工具类
 *
 * @author fred
 * @date 16/9/7
 */
public class SignUtil {
    //protected static final Logger logger = LoggerFactory.getLogger(SignUtil.class);
    /**
     *  签名方法
     *  本方法将Request中的httpMethod、headers、path、queryParam、formParam合成一个字符串用hmacSha256算法双向加密进行签名
     */
    public static String sign(ApiRequest request , String secret) {
        try {
//            String signString = buildStringToSign(request);
//            ISignerFactory signerFactory = SignerFactoryManager.findSignerFactory(request.getSignatureMethod());
//
//            if (null == signerFactory) {
//                throw new SdkException("unsupported signature method:" + request.getSignatureMethod());
//            }
//
//            ISinger signer = signerFactory.getSigner();
//
//            if (null == signer) {
//                throw new SdkException("Oops!");
//            }
//
//            try {
//                return signer.sign(signString, secret);
//            } catch (Exception e) {
//                throw new SdkException(e);
//            }
            StringBuilder sb = new StringBuilder();
            String path = request.getPath();
            sb.append(path).append("?");
            Map<String,String> queryParams = request.getQuerys();
            if(queryParams!=null){
                queryParams.forEach((key,value)->{
                    if(!sb.toString().endsWith("&")){
                        sb.append("&").append(key).append("=").append(value);
                    }else{
                        sb.append(key).append("=").append(value);
                    }
                });
            }
            return getEncryptUrl(sb.toString(),secret);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 将Request中的httpMethod、headers、path、queryParam、formParam合成一个字符串
     */
    public static String buildStringToSign(ApiRequest apiRequest) {

        StringBuilder sb = new StringBuilder();
        sb.append(apiRequest.getMethod().getValue()).append(SdkConstant.CLOUDAPI_LF);

        //如果有@"Accept"头，这个头需要参与签名
        if (apiRequest.getFirstHeaderValue(HttpConstant.CLOUDAPI_HTTP_HEADER_ACCEPT) != null) {
            sb.append(apiRequest.getFirstHeaderValue(HttpConstant.CLOUDAPI_HTTP_HEADER_ACCEPT));
        }
        sb.append(SdkConstant.CLOUDAPI_LF);

        //如果有@"Content-MD5"头，这个头需要参与签名
        if (apiRequest.getFirstHeaderValue(HttpConstant.CLOUDAPI_HTTP_HEADER_CONTENT_MD5) != null) {
            sb.append(apiRequest.getFirstHeaderValue(HttpConstant.CLOUDAPI_HTTP_HEADER_CONTENT_MD5));
        }
        sb.append(SdkConstant.CLOUDAPI_LF);

        //如果有@"Content-Type"头，这个头需要参与签名
        if (apiRequest.getFirstHeaderValue(HttpConstant.CLOUDAPI_HTTP_HEADER_CONTENT_TYPE) != null) {
            sb.append(apiRequest.getFirstHeaderValue(HttpConstant.CLOUDAPI_HTTP_HEADER_CONTENT_TYPE));
        }
        sb.append(SdkConstant.CLOUDAPI_LF);

        //签名优先读取HTTP_CA_HEADER_DATE，因为通过浏览器过来的请求不允许自定义Date（会被浏览器认为是篡改攻击）
        if (apiRequest.getFirstHeaderValue(HttpConstant.CLOUDAPI_HTTP_HEADER_DATE) != null) {
            sb.append(apiRequest.getFirstHeaderValue(HttpConstant.CLOUDAPI_HTTP_HEADER_DATE));
        }
        sb.append(SdkConstant.CLOUDAPI_LF);

        //将headers合成一个字符串
        sb.append(buildHeaders(apiRequest));

        //将path、queryParam、formParam合成一个字符串
        sb.append(buildResource(apiRequest));

        return sb.toString();
    }


    public static String buildStringToSign(ApiResponse apiResponse){
        StringBuilder sb = new StringBuilder();
        sb.append(apiResponse.getCode()).append(SdkConstant.CLOUDAPI_LF);
        String signatureHeaders = apiResponse.getFirstHeaderValue(SdkConstant.CLOUDAPI_X_CA_SIGNATURE_HEADERS);
        //logger.info("signatureHeaders : " + signatureHeaders);
        if(!HttpCommonUtil.isBlank(signatureHeaders)){
            signatureHeaders = signatureHeaders.toLowerCase();
            String[] signatureHeaderList = signatureHeaders.split(",");


            for(int i = 0 ; i < signatureHeaderList.length ; i++ ){

                //logger.info("signatureHeaderList[i]:" + signatureHeaderList[i]);
                //logger.info("apiResponse.getFirstHeaderValue(signatureHeaderList[i]) : " + apiResponse.getFirstHeaderValue(signatureHeaderList[i]));

                if (apiResponse.getFirstHeaderValue(signatureHeaderList[i]) != null) {
                    sb.append(apiResponse.getFirstHeaderValue(signatureHeaderList[i]));
                    sb.append(SdkConstant.CLOUDAPI_LF);
                }
            }
        }

        Map<String, List<String>> headers = apiResponse.getHeaders();
        for(String key : headers.keySet()){
            //logger.info("key:" + key);
            //logger.info("headers[i]:" + headers.get(key).get(0));
        }

        return sb.toString();
    }


    /**
     * 将path、queryParam、formParam合成一个字符串
     */
    private static String buildResource(ApiRequest request) {
        StringBuilder result = new StringBuilder();
        result.append(request.getPath());

        //使用TreeMap,默认按照字母排序
        TreeMap<String , String> parameter = new TreeMap<String , String>();
        if(null!= request.getQuerys() && request.getQuerys().size() > 0){
            parameter.putAll(request.getQuerys());
        }

        if(null != request.getFormParams() && request.getFormParams().size() > 0){
            parameter.putAll(request.getFormParams());
        }

        if(parameter.size() > 0) {
            result.append("?");
            boolean isFirst = true;
            for (String key : parameter.keySet()) {
                if (isFirst == false) {
                    result.append("&");
                } else {
                    isFirst = false;
                }
                result.append(key);
                String value = parameter.get(key);
                if(null != value && !"".equals(value)){
                    result.append("=").append(value);
                }
            }
        }
        return result.toString();
    }

    /**
     *  将headers合成一个字符串
     *  需要注意的是，HTTP头需要按照字母排序加入签名字符串
     *  同时所有加入签名的头的列表，需要用逗号分隔形成一个字符串，加入一个新HTTP头@"X-Ca-Signature-Headers"
     */
    private static String buildHeaders(ApiHttpMessage apiHttpMessage) {
        //使用TreeMap,默认按照字母排序
        Map<String, String> headersToSign = new TreeMap<String, String>();


        StringBuilder signHeadersStringBuilder = new StringBuilder();

        int flag = 0;
        for (Map.Entry<String, List<String>> header : apiHttpMessage.getHeaders().entrySet()) {
            if (header.getKey().startsWith(SdkConstant.CLOUDAPI_CA_HEADER_TO_SIGN_PREFIX_SYSTEM)) {
                if (flag != 0) {
                    signHeadersStringBuilder.append(",");
                }
                flag++;
                signHeadersStringBuilder.append(header.getKey());
                headersToSign.put(header.getKey(), apiHttpMessage.getFirstHeaderValue(header.getKey()));
            }
        }

        //同时所有加入签名的头的列表，需要用逗号分隔形成一个字符串，加入一个新HTTP头@"X-Ca-Signature-Headers"
        apiHttpMessage.addHeader(SdkConstant.CLOUDAPI_X_CA_SIGNATURE_HEADERS, signHeadersStringBuilder.toString());


        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> e : headersToSign.entrySet()) {
            sb.append(e.getKey()).append(':').append(e.getValue()).append(SdkConstant.CLOUDAPI_LF);
        }
        return sb.toString();
    }

    public static String base64AndMD5(byte[] bytes) {
        if (bytes == null) {
            throw new IllegalArgumentException("bytes can not be null");
        }
        try {
            final MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(bytes);
            byte[] md5Result = md.digest();
            String base64Result = Base64.encodeBase64String(md5Result);
            /*
             * 正常情况下，base64的结果为24位，因与服务器有约定，在超过24位的情况下，截取前24位
             */
            return base64Result.length() > 24 ? base64Result.substring(0, 23) : base64Result;
        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("unknown algorithm MD5");
        }
    }

    public static String getEncryptUrl(String url, String secret) {
        String str01 = url.substring(url.indexOf("?") + 1, url.length());
        String str = str01.replaceAll("&", "").replaceAll("=", "")+secret;
        str = toCompare(str);
        String Md5Url = getMD5(str);
        url = url + "&sign=" + Md5Url;
        return url;
    }

    /**
     * 字符串排序
     * 例如：jkd1s5f3gac5b 排序后 1355abcdfgjks
     * @param str
     * @return
     */
    public static String toCompare(String str){
        char [] b = str.toCharArray();
        Arrays.sort(b);
        String str2 = String.copyValueOf(b);
        return str2;
    }

    public static String getMD5(String s)  {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            if (s != null && !"".equals(s.trim())) {
                byte[] strTemp = s.getBytes();
                MessageDigest mdTemp = MessageDigest.getInstance("MD5");
                mdTemp.update(strTemp);
                byte[] md = mdTemp.digest();
                int j = md.length;
                char str[] = new char[j * 2];
                int k = 0;
                for (int i = 0; i < j; i++) {
                    byte byte0 = md[i];
                    str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                    str[k++] = hexDigits[byte0 & 0xf];
                }
                return new String(str);
            } else {
                return "";
            }
        } catch (Exception e) {
            return null;
        }

    }
}
