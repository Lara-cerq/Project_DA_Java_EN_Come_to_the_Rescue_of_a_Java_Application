package com.hemebiotech.analytics;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Symptom extends ReadSymptomDataFromFile {

	public Symptom(String filepath) {
		super(filepath);
	}
	
	public Map<String, Integer> analyseFile(List<String> symptomsList) throws IOException {
		Map<String, Integer> symptoms = new HashMap<>();

		for (String readSymptom: symptomsList) {
			symptoms.put(readSymptom, 1);

		}		
		symptoms.forEach(Symptom::print);
		return symptoms;		

	}
	
	public static void print(String symptoms, Integer value) {
		System.out.println(symptoms + "=" + value);
	}

//	public void ChangeNumber(String symptom, Integer value) throws IOException {
//
//		GetSymptoms();
//
//		int ancienv = symptoms.get(symptom);
//		symptoms.put(symptom, (ancienv + value));
//		symptoms.forEach(ReadSymptomDataFromFile::print);
//	}
//
//	public String getSymptom(String symptom) {
//		return symptom;
//	}
//
//	public void chercheSymptom(String symptom) throws IOException {
//		GetSymptoms();
//		String symp = getSymptom(symptom);
//		if (symp.equals(symptom)) {
//			int number = symptoms.get(symptom);
//			System.out.println("number of " + symptom.toString() + ": " + number);
//		}
//	}
}
