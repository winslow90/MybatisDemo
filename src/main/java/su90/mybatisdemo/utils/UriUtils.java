/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package su90.mybatisdemo.utils;

import java.net.URI;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import su90.mybatisdemo.web.beans.Href;
import su90.mybatisdemo.web.endpoints.RegionsEndpoints;

/**
 *
 * @author superman90
 */
public class UriUtils {
    
    public static URI generateUri(Object info){
        UriComponents uriComponents = MvcUriComponentsBuilder.fromMethodCall(
                info
        ).build();
        return uriComponents.encode().toUri();
    }
    
    public static String generateAsciiStr(Object info){
        UriComponents uriComponents = MvcUriComponentsBuilder.fromMethodCall(
                info
        ).build();
        return uriComponents.encode().toUri().toASCIIString();
    }
    
    public static Href generateHref(Object info){
        UriComponents uriComponents = MvcUriComponentsBuilder.fromMethodCall(
                info
        ).build();
        return new Href(uriComponents.encode().toUri().toASCIIString());
    }
    
}
