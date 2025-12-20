package com.MicroserviceEC.ecomerce.Execptions;

import java.util.Map;

public record ErrorResponse(Map<String,String> errors) {
}
