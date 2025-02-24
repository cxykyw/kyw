package com.kyw.spring.controller;

import com.kyw.spring.domain.Demo01;
import com.kyw.spring.domain.Fund;
import com.kyw.spring.domain.FundReport;
import com.kyw.spring.domain.PageInfo;
import com.kyw.spring.service.Demo01Service;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Tag(name = "测试", description = "测试")
@RestController
@RequestMapping("/api/demo")
public class DemoController {

    @Autowired
    private Demo01Service demo01Service;

    @PostMapping("list")
    public PageInfo<Demo01> list(@RequestParam(value = "userName",required = false) String userName, @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        return demo01Service.list(pageNo, pageSize, userName);
    }

    @PostMapping("executeSql")
    public Object executeSql(@RequestParam("sql") String sql) {
        // 预处理SQL，防止SQL注入
//        sql = sql.replaceAll("[';]", ""); // 去除单引号和分号
        // 检查SQL格式是否符合标准规范
        if (!sql.toLowerCase().startsWith("select")) {
            throw new IllegalArgumentException("Only SELECT queries are allowed.");
        }
        return demo01Service.executeSql(sql);
    }

    // 定义一个获取基金列表的接口，并造一些测试数据
    @GetMapping("/funds")
    public List<Fund> getAllFunds() {
        List<Fund> funds = new ArrayList<>();
        // 造5条测试数据
        for (int i = 1; i <= 5; i++) {
            Fund fund = new Fund();
            fund.setFundId(i);
            fund.setFundName("基金" + i);
            fund.setFundPrice(100 + i * 10);
            fund.setFundDate("2023-07-" + i);
            funds.add(fund);
        }
        return funds;
    }
    @GetMapping("/getFund")
    public Fund getFundByName(String fundName) {
        List<Fund> funds = new ArrayList<>();
        // 造5条测试数据
        for (int i = 1; i <= 5; i++) {
            Fund fund = new Fund();
            fund.setFundId(i);
            fund.setFundName("基金" + i);
            fund.setFundPrice(100 + i * 10);
            fund.setFundDate("2023-07-" + i);
            funds.add(fund);
        }

        //判断fundName是否为空，不为空则根据fundName查找funds中的数据，fundId是否为空，不为空则根据fundId查找funds中的数据，两者满足一个则返回funds
        if (fundName != null) {
            for (Fund fund : funds) {
                if (fund.getFundName().equals(fundName)) {
                    return fund;
                }
            }
        }
        return null;
    }

    //定义一个获取基金研报的接口，并造一些测试数据，创建研报实体类，并返回一个列表
    @GetMapping("/reports")
    public FundReport getFundReports(String fundName) {
        List<FundReport> reports = new ArrayList<>();
        // 造5条测试数据
        for (int i = 1; i <= 5; i++) {
            FundReport report = new FundReport();
            report.setReportId(i);
            report.setReportName("研报" + i);
            report.setReportContent("内容" + i);
            report.setReportDate("2023-07-" + i);
            report.setFundId(i+12);
            report.setFundName("基金" + i);
            reports.add(report);
        }
        //根据fundName查找reports中的数据
        FundReport fundReport = new FundReport();
        for (FundReport report : reports) {
            if (report.getFundName().equals(fundName)) {
                fundReport = report;
            }
        }
        return fundReport;
    }


}