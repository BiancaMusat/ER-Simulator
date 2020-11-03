package queues;

import java.util.ArrayList;
import java.util.Collections;

import comparators.PatientComparator;
import participants.Patient;

/*
 * Copyright (c)
 * Musat Bianca, 323CA
*/

public class TriageQueue {
    private ArrayList<Patient> patients = new ArrayList<Patient>();

    /*
     * Orders the patients array by severity, using the PatientComparator
     */
    public void orderBySeverity() {
        PatientComparator patientComparator = new PatientComparator();
        Collections.sort(patients, patientComparator);
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
