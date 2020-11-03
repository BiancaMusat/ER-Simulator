package comparators;

import java.util.Comparator;

import participants.Patient;

/*
 * Copyright (c)
 * Musat Bianca, 323CA
*/

public class NumericalComparator implements Comparator<Patient> {

    /**
     * Override the compare method.
     * Compares two patients by their doctor`s position in input list
     * and then by patient name
     * @param p1  the first patient
     * @param p2  the second patient
     * @return positive for greater, negative for lower, 0 for equal
     */
    @Override
    public int compare(Patient p1, Patient p2) {
        if (p1.getDoctor().getPositionInList() - p2.getDoctor().getPositionInList() != 0) {
            return p1.getDoctor().getPositionInList() - p2.getDoctor().getPositionInList();
        }
        return p1.getName().compareTo(p2.getName());
    }

}
