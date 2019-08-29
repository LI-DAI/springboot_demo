package ${packageName};

import com.alibaba.cloudapi.sdk.client.ApacheHttpClient;
import com.alibaba.cloudapi.sdk.enums.Scheme;
import com.alibaba.cloudapi.sdk.enums.HttpMethod;
import com.alibaba.cloudapi.sdk.model.ApiRequest;
import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.alibaba.cloudapi.sdk.model.ApiCallback;
import com.alibaba.cloudapi.sdk.model.HttpClientBuilderParams;
import com.alibaba.cloudapi.sdk.enums.ParamPosition;
import com.alibaba.cloudapi.sdk.enums.WebSocketApiType;
import com.alibaba.fastjson.JSONObject;

public class ${className} extends ApacheHttpClient{

    public final static String HOST = "10.0.91.186:8008";
    private static ${className} instance = new ${className}();
    public static ${className} getInstance(){return instance;}

    public void init(HttpClientBuilderParams httpClientBuilderParams){
        httpClientBuilderParams.setScheme(Scheme.HTTP);
        httpClientBuilderParams.setHost(HOST);
        super.init(httpClientBuilderParams);
    }

    public void ${apiServiceName}(String tel , ApiCallback callback) {
        String path = ${apiRequestPath};
        ApiRequest request = new ApiRequest(HttpMethod.GET , path);
        request.addParam("tel" , tel , ParamPosition.PATH , true);

        sendAsyncRequest(request , callback);
    }

    public ApiResponse ${apiServiceName}_syncMode(String tel) {
        String path = ${apiRequestPath};
        ApiRequest request = new ApiRequest(HttpMethod.GET , path);
        request.addParam("tel" , tel , ParamPosition.PATH , true);

        return sendSyncRequest(request);
    }

}