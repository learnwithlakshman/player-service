package com.lwl.ms.playerservice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class APIErrorResponse {

	private String errorMsg;

}
