package org.example.business;

import org.example.integration.LLMClient;
import org.example.model.Patient;
import org.example.data.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LLMService {

    private final LLMClient llmClient;
    private final PatientRepository patientRepository;

    public LLMService(LLMClient llmClient, PatientRepository patientRepository) {
        this.llmClient = llmClient;
        this.patientRepository = patientRepository;
    }


    public String generateResponse(String prompt) {
        return llmClient.generateText(prompt);
    }


    public List<Patient> enrichAllPatients() {
        List<Patient> patients = patientRepository.getAllPatients();

        for (Patient patient : patients) {
            String prompt = String.format(
                    "Generate a brief clinical summary (2-3 sentences) for a patient named %s, age %d. " +
                            "Include potential health considerations based on age.",
                    patient.getName(),
                    patient.getAge()
            );

            String aiSummary = llmClient.generateText(prompt);
            patient.setAiSummary(aiSummary);
        }

        return patients;
    }
}