package queues;
/*
 * Copyright (c)
 * Musat Bianca, 323CA
*/

import java.util.ArrayList;
import java.util.Collections;

import comparators.ExamComparator;
import enums.Urgency;
import enums.UrgencyEstimator;
import participants.Doctor;
import participants.Patient;

public class ExaminationQueue {
    private ArrayList<Patient> patients = new ArrayList<Patient>();
    private ArrayList<Doctor> doctors = new ArrayList<Doctor>(); // retains
                                            // the doctors in the current order

    /*
     * Orders the patients array by urgency, using the ExamComparator
     */
    public void orderByUrgency() {
        ExamComparator examinationComparator = new ExamComparator();
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
    /**
     * Returns the array of doctors.
     * @return the array of doctors.
     */
    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }
    /**
     * Sets the array of doctors.
     * @param the array of doctors.
     */
    public void setDoctors(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
    }

}
