package org.acme.configuration;

import com.amazonaws.auth.*;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.*;
import com.amazonaws.services.dynamodbv2.xspec.S;

import java.util.LinkedHashMap;
import java.util.Map;

public class CognitoLogin {

    private AWSCognitoIdentityProvider client = createClient();

    private AWSCognitoIdentityProvider createClient(){
        AWSSessionCredentials awsCreds = new BasicSessionCredentials("ASIAZGATGYFR7SWIQ67G", "YcJyxyakJ23emkQ+ndVZ1iU/+15Dfa8slZrkBFp9","FwoGZXIvYXdzECsaDO76nhdHi/lJqdSkcSLXARdTTqvsDdOgmPqHIoYFtmmQPoi+L+jxkoV3ruX1A9Ik+yNYJYTbVrbVbD0fPKngZm4zHAs9r9Fm+6y8RIcUky6fFwktfbYdvxLlKlWK9zbwGkLRDn6IjFQVCpmuBv58w0U56LP3tRLgDUD8d2hkJycaX4GPcgAcr7AEI82QNoRUREmLDc8luyh/WLXR80B/g0MRpQA1nqdPmNAbWuJvJniyp4byqYOTDYpnf+WaamCIY7XfIDxb6h2wHPvF5lmCmTtPbxiUI2zA+JRUc3kaojUBJl+LjmXTKM+FoqIGMi3zRpQvrM9lC6guCFmWGvnr2eK3X8A3CEkN0bTx0kD/PpKclhPa4nIgUroQ+RA=");

        // Cliente de Cognito
        AWSCognitoIdentityProvider cognitoClient = AWSCognitoIdentityProviderClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .withRegion(Regions.US_EAST_1)
                .build();

        return cognitoClient;
    }

    public String Login(String email, String password) {

        // Datos de autenticación
        String clientId = "5vk8nc0q6managog4dsjolgmjl";

        Map<String, String> authParams = new LinkedHashMap<String, String>() {{
            put("USERNAME", email);
            put("PASSWORD", password);
        }};
        // Iniciar autenticación
        InitiateAuthRequest authRequest = new InitiateAuthRequest()
                .withAuthFlow(AuthFlowType.USER_PASSWORD_AUTH)
                .withClientId(clientId)
                .withAuthParameters(authParams);
        InitiateAuthResult authResult = client.initiateAuth(authRequest);

        // Obtener token de acceso
        AuthenticationResultType authResponseType = authResult.getAuthenticationResult();
        String accessToken = authResponseType.getIdToken();

        // Validar token de acceso
//        System.out.println("Access Token: " + accessToken);
        return accessToken;
    }

    public SignUpResult signUp(String name, String email, String password) {
        SignUpRequest request = new SignUpRequest().withClientId("5vk8nc0q6managog4dsjolgmjl")
                .withUsername(email)
                .withPassword(password)
                .withUserAttributes(
                        new AttributeType()
                                .withName("name")
                                .withValue(name));
        SignUpResult result = client.signUp(request);
        System.out.println(result);
        return result;
    }
}
