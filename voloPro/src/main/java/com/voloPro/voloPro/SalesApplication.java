package com.voloPro.voloPro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class SalesApplication {
	public static void main(String[] args) {
		SpringApplication.run(SalesApplication.class, args);
	}

	private List<Map<String, String>> readDatasetFromCSV() {
		List<Map<String, String>> dataset = new ArrayList<>();
		String csvFile = "data.csv";
		String line = "";
		String csvSeparator = ",";
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			String[] header = br.readLine().split(csvSeparator);
			while ((line = br.readLine()) != null) {
				String[] data = line.split(csvSeparator);
				Map<String, String> row = new HashMap<>();
				for (int i = 0; i < header.length; i++) {
					row.put(header[i], data[i]);
				}
				dataset.add(row);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataset;
	}

	// API 1: Total item (total seats) sold in Marketing for the last quarter of the year
	@GetMapping("/api/total_items")
	public int getTotalItems(@RequestParam String start_date, @RequestParam String end_date, @RequestParam(defaultValue = "Marketing") String department) {
		List<Map<String, String>> dataset = readDatasetFromCSV();

		int totalItemsSold = 0;
		for (Map<String, String> row : dataset) {
			String rowDepartment = row.get("department");
			String rowDate = row.get("date");
			if (rowDepartment.equals(department) && isWithinDateRange(rowDate, start_date, end_date)) {
				int seats = Integer.parseInt(row.get("seats"));
				totalItemsSold += seats;
			}
		}
		return totalItemsSold;
	}

	// API 2: Nth most sold item based on quantity or total price within a specified date range
	@GetMapping("/api/nth_most_total_item")
	public String getNthMostTotalItem(@RequestParam String item_by, @RequestParam String start_date, @RequestParam String end_date, @RequestParam int n) {
		List<Map<String, String>> dataset = readDatasetFromCSV();

		List<Map<String, String>> filteredData = new ArrayList<>();
		for (Map<String, String> row : dataset) {
			String rowDate = row.get("date");
			if (isWithinDateRange(rowDate, start_date, end_date)) {
				filteredData.add(row);
			}
		}

		if (item_by.equals("quantity")) {
			filteredData.sort((a, b) -> Integer.compare(Integer.parseInt(b.get("seats")), Integer.parseInt(a.get("seats"))));
		} else if (item_by.equals("price")) {
			filteredData.sort((a, b) -> Float.compare(Float.parseFloat(b.get("amount")), Float.parseFloat(a.get("amount"))));
		}

		if (n >= 1 && n <= filteredData.size()) {
			return filteredData.get(n - 1).get("software");
		} else {
			return "Invalid value for n parameter. Must be between 1 and the total number of items.";
		}
	}

// API 3: Percentage of sold items (seats) department-wise
@GetMapping("/api/percentage_of_department_wise_sold_items")
public Map<String, Double> getPercentageOfDepartmentWiseSoldItems(@RequestParam String start_date, @RequestParam String end_date) {
	List<Map<String, String>> dataset = readDatasetFromCSV();

	List<Map<String, String>> filteredData = new ArrayList<>();
	for (Map<String, String> row : dataset) {
		String rowDate = row.get("date");
		if (isWithinDateRange(rowDate, start_date, end_date)) {
			filteredData.add(row);
		}
	}

	Map<String, Integer> departmentCounts = new HashMap<>();
	int totalCount = 0;

	for (Map<String, String> row : filteredData) {
		String department = row.get("department");
		int seats = Integer.parseInt(row.get("seats"));

		departmentCounts.put(department, departmentCounts.getOrDefault(department, 0) + seats);
		totalCount += seats;
	}

	Map<String, Double> departmentPercentages = new HashMap<>();

	for (Map.Entry<String, Integer> entry : departmentCounts.entrySet()) {
		String department = entry.getKey();
		int count = entry.getValue();
		double percentage = (count / (double) totalCount) * 100;
		departmentPercentages.put(department, Math.round(percentage * 100.0) / 100.0);
	}

	return departmentPercentages;
}

	// API 4: Monthly sales for a specific product
	@GetMapping("/api/monthly_sales")
	public List<Double> getMonthlySales(@RequestParam String product, @RequestParam int year) {
		List<Map<String, String>> dataset = readDatasetFromCSV();

		List<Map<String, String>> filteredData = new ArrayList<>();
		for (Map<String, String> row : dataset) {
			String rowSoftware = row.get("software");
			String rowDate = row.get("date");
			if (rowSoftware.equals(product) && rowDate.startsWith(Integer.toString(year))) {
				filteredData.add(row);
			}
		}

		List<Double> monthlySales = new ArrayList<>(12);
		for (int i = 0; i < 12; i++) {
			monthlySales.add(0.0);
		}

		for (Map<String, String> row : filteredData) {
			String rowDate = row.get("date");
			int month = Integer.parseInt(rowDate.substring(5, 7));
			int seats = Integer.parseInt(row.get("seats"));
			double amount = Double.parseDouble(row.get("amount"));

			monthlySales.set(month - 1, monthlySales.get(month - 1) + seats);
		}

		return monthlySales;
	}

	private boolean isWithinDateRange(String date, String startDate, String endDate) {
		return date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0;
	}
}



