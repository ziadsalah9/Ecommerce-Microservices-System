package com.MicroserviceEC.ecomerce.Excptions;

import java.util.Map;

public record ErrorRespone(Map<String,String> errors) {
}
