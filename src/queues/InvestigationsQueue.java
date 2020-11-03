package queues;
/*
 * Copyright (c)
 * Musat Bianca, 323CA
*/

import java.util.ArrayList;
import java.util.Collections;

import comparators.ExaminationComparator;
import enums.Urgency;
import enums.UrgencyEstimator;
import participants.Patient;

public class InvestigationsQueue {
    private ArrayList<Patient> patients = new ArrayList<Patient>();

    /*
     * Orders the patients array by urgency, using the ExaminationComparator
     */
    public void orderByUrgency() {
        ExaminationComparator examinationComparator =
                            new ExaminationComparator();
        Collections.sort(patients, examinationComparator);
    }

    /*
     * sets the urgency for each patient using the estimateUrgency() method
     * in Urgency class
     */
    public void estimateUrgency() {
        Urgency urgency;
        UrgencyEstimator estimate = UrgencyEstimator.getInstance();
        for (int i = 0; i < patients.size(); i++) {
            urgency = estimate.estimateUrgency(patients.get(i).getState().
                   getIllnessName(), patients.get(i).getState().getSeverity());
            patients.get(i).setUrgency(urgency);
        }
    }

    /**
     * Returns the array of patients.
     * @return the array of patients.
     */
    public ArrayList<Patient> getPatients() {
        return patients;
    }
    /**
     * Sets the array of patients.
     * @param the array of patients.
     */
    public void setPatients(ArrayList<Patient> patients) {
        this.patients = patients;
    }
}
