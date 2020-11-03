package comparators;

import java.util.Comparator;

import participants.Patient;

/*
 * Copyright (c)
 * Musat Bianca, 323CA
*/

public class PatientComparator implements Comparator<Patient> {

    /**
     * Override the compare method.
     * Compares two patients by their illness severity
     * @param p1  the first patient
     * @param p2  the second patient
     * @return positive for greater, negative for lower, 0 for equal
     */
    @Override
    public int compare(Patient p1, Patient p2) {
        return -(p1.getState().getSeverity() - p2.getState().getSeverity());
    }

}
