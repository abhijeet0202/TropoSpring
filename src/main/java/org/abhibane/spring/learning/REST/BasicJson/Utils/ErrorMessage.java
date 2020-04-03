package org.abhibane.spring.learning.REST.BasicJson.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {
	
	private String status;
	private String message;
	
}
