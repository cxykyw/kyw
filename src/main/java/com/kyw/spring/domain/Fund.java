package com.kyw.spring.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "基金对象")
public class Fund {
    @Schema(description = "基金ID")
    private int fundId;
    @Schema(description = "基金名称")
    private String fundName;
    @Schema(description = "基金价格")
    private double fundPrice;
    @Schema(description = "基金日期")
    private String fundDate;
}
