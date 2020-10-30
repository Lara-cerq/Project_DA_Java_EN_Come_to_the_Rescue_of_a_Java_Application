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
		analyticsCount.run(); //calls run() to read and write data
	}

	Map<String, Integer> symptoms = new TreeMap<>();

	/**
	 * run() allows to open the file symptoms.txt, create a symptomsList and calls
	 * the writeFile() method
	 */
	public void run() {
		ReadSymptomDataFromFile readSymtom = new ReadSymptomDataFromFile("symptoms.txt");
		List<String> symptomsList;
		try {
			symptomsList = readSymtom.GetSymptoms();
		} catch (IOException e) {
			System.err.println("Impossible to open the file!" + e.getMessage());
			return;
		}
		this.analyseFile(symptomsList);

		try {
			this.writeFile();
		} catch (IOException e) {
			System.err.println();
		}
	}

	/**
	 * analyseFile() count the number of each symptom and put this in a TreeMap for
	 * classify symptoms by alphabetical order without duplicate data
	 * 
	 * @param symptomsList
	 * @return
	 */
	public Map<String, Integer> analyseFile(List<String> symptomsList) {

		for (String readSymptom : symptomsList) {
			Integer count = symptoms.get(readSymptom);
			symptoms.put(readSymptom, (count == null) ? 1 : count + 1);
		}

		symptoms.forEach(AnalyticsCounter::print);
		return symptoms;
	}

	/**
	 * print allows to display the list of symtoms and its number
	 */
	public static void print(String symptoms, Integer value) {
		System.out.println(symptoms + "=" + value);
	}

	/**
	 * writeFile() create a new file results.out and written inside the list of
	 * symptoms and its number
	 * @return the TreeMap symptoms (list of symptoms+number)
	 * @throws IOException
	 */
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
