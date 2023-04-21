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
        AWSSessionCredentials awsCreds = new BasicSessionCredentials("ASIAZGATGYFRQNNGOYLP", "x3vFfOR1Cx5cnSSpkZ/DMomEYLrKuJOkGf+o9eqD","FwoGZXIvYXdzEK///////////wEaDIKyMmatoZXvKlf2EiLXAQbcqhpTm866uL7C+ZxBTbU2BRWhlDP+w/Xbn+wdgLctHOR1BeFJ3cfYe3VtfJWLZ76uZEcfxA4TTyTagSi8yQ1daC8y0d5ejYQNgRmGErMgGDQi+XHcGVNSELNo7KTg5wP/UtS0Sl15UmLzVSPYlpel4fS3BRn6PMnfj7TxwN1uPL06LtvFuwf8r3OuwPW5sPbLODs9v3r5nideg+nrJfXb++o/rCl6k/4WheoFrdIsZd7L5rjEHnwVGFAQnard82kQRzzZ3kIuK+U6m4pGeJWK/ZOCYOaIKN7ShqIGMi1/IL2YHLVVyMrAs0rcippJ67R0E85e6tc5CR/tYKWZVYA3nxI9wjV052RbX18=");

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
        String clientId = "38a0emb3thd7e5bhu9462u17nl";

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
        SignUpRequest request = new SignUpRequest().withClientId("38a0emb3thd7e5bhu9462u17nl")
                .withUsername(email)
                .withPassword(password)
                .withUserAttributes(
                        new AttributeType()
                                .withName("name")
                                .withValue(name));
        SignUpResult result = client.signUp(request);
        return result;
    }
}
