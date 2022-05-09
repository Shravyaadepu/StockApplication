package com.zensar.stockapplication.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Endpoint(id="custom")
@Component
public class CustomEndpoint {
@ReadOperation
public String myOwnEndpoint() {
return "<h2> My own endpoint customised </h2>";

}
}
