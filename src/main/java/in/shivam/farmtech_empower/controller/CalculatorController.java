package in.shivam.farmtech_empower.controller;

import in.shivam.farmtech_empower.service.CalculatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping("/calculate")
    public String calculateProfit(
            @RequestParam("name") String cropName,
            @RequestParam("fieldArea") double fieldArea,
            Model model) {

        // Example static costs (could be dynamic)
        double landPreparationCost = 5000;
        double seedCost = 3000;
        double fertilizerCost = 2000;
        double sowingCost = 1000;
        double irrigationCost = 1500;
        double pesticideCost = 1200;
        double harvestingCost = 2500;
        double postHarvestCost = 1000;

        // Calculate total costs based on the selected field area
        double totalLandPreparationCost = landPreparationCost * fieldArea;
        double totalSeedCost = seedCost * fieldArea;
        double totalFertilizerCost = fertilizerCost * fieldArea;
        double totalSowingCost = sowingCost * fieldArea;
        double totalIrrigationCost = irrigationCost * fieldArea;
        double totalPesticideCost = pesticideCost * fieldArea;
        double totalHarvestingCost = harvestingCost * fieldArea;
        double totalPostHarvestCost = postHarvestCost * fieldArea;

        double totalCost = calculatorService.calculateTotalCost(
                totalLandPreparationCost, totalSeedCost, totalFertilizerCost, totalSowingCost, 
                totalIrrigationCost, totalPesticideCost, totalHarvestingCost, totalPostHarvestCost);

        // Get sale price per kilogram based on crop type
        double salePricePerKg = calculatorService.getSalePricePerKg(cropName);

        // Calculate total production and sale value
        double totalProductionKg = calculatorService.calculateTotalProductionKg(fieldArea);
        double totalSaleValue = calculatorService.calculateTotalSaleValue(totalProductionKg, salePricePerKg);

        // Calculate profit
        double profit = calculatorService.calculateProfit(totalSaleValue, totalCost);

        // Add attributes to the model for Thymeleaf to use
        model.addAttribute("cropName", cropName);
        model.addAttribute("fieldArea", fieldArea); // Pass field area to the model
        model.addAttribute("totalLandPreparationCost", totalLandPreparationCost);
        model.addAttribute("totalSeedCost", totalSeedCost);
        model.addAttribute("totalFertilizerCost", totalFertilizerCost);
        model.addAttribute("totalSowingCost", totalSowingCost);
        model.addAttribute("totalIrrigationCost", totalIrrigationCost);
        model.addAttribute("totalPesticideCost", totalPesticideCost);
        model.addAttribute("totalHarvestingCost", totalHarvestingCost);
        model.addAttribute("totalPostHarvestCost", totalPostHarvestCost);
        model.addAttribute("totalCost", totalCost);
        model.addAttribute("totalProductionKg", totalProductionKg);
        model.addAttribute("totalSaleValue", totalSaleValue);
        model.addAttribute("profit", profit);
        model.addAttribute("salePricePerKg", salePricePerKg); // Pass sale price for display

        return "result"; // Return the result page
    }
}
