package com.MicroserviceEC.ecomerce.product.Execptions;

import java.util.Map;

public record ErrorResponse(Map<String,String> errors) {
}
