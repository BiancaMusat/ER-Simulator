package comparators;

import java.util.Comparator;

import participants.Patient;

/*
 * Copyright (c)
 * Musat Bianca, 323CA
*/

public class AlphabeticalComparator implements Comparator<Patient> {

    /**
     * Override the compare method.
     * Compares two patients by name
     * @param p1  the first patient
     * @param p2  the second patient
     * @return 1 for greater, -1 for lower, 0 for equal
     */
    @Override
    public int compare(Patient p1, Patient p2) {
        if (p1.getName().compareTo(p2.getName()) < 0) {
            return -1;
        } else if (p1.getName().compareTo(p2.getName()) > 0) {
            return 1;
        } else {
            return 0;
        }
    }

}
