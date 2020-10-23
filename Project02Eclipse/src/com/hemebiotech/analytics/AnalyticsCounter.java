package com.hemebiotech.analytics;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class AnalyticsCounter {

	public static void main(String args[]) throws Exception {

		AnalyticsCounter analyticsCount = new AnalyticsCounter();
		analyticsCount.run();
	}

	Map<String, Integer> symptoms = new TreeMap<>();

	public void run() {
		ReadSymptomDataFromFile readSymtom = new ReadSymptomDataFromFile("symptoms.txt");
		List<String> symptomsList;
		try {
			symptomsList = readSymtom.GetSymptoms();
		} catch (IOException e) {
			System.err.println("Impossible d'ouvrir le fichier" + e.getMessage());
			return;
		}
		this.analyseFile(symptomsList);

		try {
			this.writeFile();
		} catch (IOException e) {
			System.err.println();
		}
	}

	public Map<String, Integer> analyseFile(List<String> symptomsList) {

		for (String readSymptom : symptomsList) {
			Integer count = symptoms.get(readSymptom);
			symptoms.put(readSymptom, (count == null) ? 1 : count + 1);
		}

		symptoms.forEach(Symptom::print);
		return symptoms;
	}

	public static void print(String symptoms, Integer value) {
		System.out.println(symptoms + "=" + value);
	}

	public Map<String, Integer> writeFile() throws IOException {
		FileWriter writer = new FileWriter("results.out");
		BufferedWriter out = new BufferedWriter(writer);
		for (Entry<String, Integer> m : symptoms.entrySet()) {
			out.write(m.getKey() + "=" + m.getValue() + "\n");
		}

		out.close();
		return symptoms;
	}
}
