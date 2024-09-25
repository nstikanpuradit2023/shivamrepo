package in.shivam.farmtech_empower.service;

import org.springframework.stereotype.Service;
import in.shivam.farmtech_empower.entity.Crop;

@Service
public class CalculatorService {

    public double calculateTotalCost(double landPreparationCost, double seedCost, double fertilizerCost,
                                     double sowingCost, double irrigationCost, double pesticideCost,
                                     double harvestingCost, double postHarvestCost) {
        return landPreparationCost + seedCost + fertilizerCost + sowingCost + irrigationCost +
               pesticideCost + harvestingCost + postHarvestCost;
    }

    public double getSalePricePerKg(String cropName) {
        Crop crop = getCrop(cropName);
        if (crop != null) {
            return crop.getSalePricePerKg();
        }
        return 30; // Default sale price if crop not found
    }

    public double calculateTotalProductionKg(double fieldArea) {
        // Assume productivity is in kilograms per hectare
        Crop crop = getCrop("Wheat"); // Default crop if none selected
        if (crop != null) {
            return fieldArea * crop.getProductivityPerHectare();
        }
        return 0;
    }

    public double calculateTotalSaleValue(double totalProductionKg, double salePricePerKg) {
        return totalProductionKg * salePricePerKg;
    }

    public double calculateProfit(double totalSaleValue, double totalCost) {
        return totalSaleValue - totalCost;
    }

    public Crop getCrop(String name) {
        // Sample data. Replace this with actual data retrieval logic.
        switch (name) {
            case "Wheat":
                return new Crop("Wheat", 1000, 500, 600, 700, 800, 200, 1000, 500, 1000, 20);
            case "Corn":
                return new Crop("Corn", 1200, 600, 700, 800, 900, 250, 1200, 600, 1200, 25);
            case "Paddy":
                return new Crop("Paddy", 1500, 700, 800, 900, 1000, 300, 1500, 700, 1500, 18);
            case "Soybean":
                return new Crop("Soybean", 1300, 600, 750, 850, 950, 280, 1300, 600, 1300, 40);
            case "Barley":
                return new Crop("Barley", 1100, 550, 650, 750, 850, 270, 1100, 550, 1100, 22);
            default:
                return null;
        }
    }
}
