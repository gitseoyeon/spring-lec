package org.example.prac.controller;

import org.example.prac.model.CustomerRank;
import org.example.prac.model.DailySales;
import org.example.prac.repository.DashboardRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {
    private final DashboardRepository dashboardRepository;

    public DashboardController(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    @GetMapping("/dailySalesPage")
    public String dailySales(Model model) {
        List<DailySales> sales = dashboardRepository.findDailySales();

        model.addAttribute("sales", sales);

        return "daily-sales";
    }

    @GetMapping("/customerRankingPage")
    public String customerRanking(Model model) {
        List<CustomerRank> ranks = dashboardRepository.findCustomerRankings();
        model.addAttribute("ranks", ranks);

        return "customer-ranking";
    }
}
