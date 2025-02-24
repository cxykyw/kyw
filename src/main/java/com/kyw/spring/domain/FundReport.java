package com.kyw.spring.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "基金研报对象")
public class FundReport {
    @Schema(description = "研报ID")
    private Integer reportId;
    @Schema(description = "研报名称")
    private String reportName;
    @Schema(description = "研报内容")
    private String reportContent;
    @Schema(description = "研报日期")
    private String reportDate;
    @Schema(description = "基金ID")
    private Integer fundId;
    @Schema(description = "基金名称")
    private String fundName;
}
