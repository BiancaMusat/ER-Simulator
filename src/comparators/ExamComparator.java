package comparators;

import java.util.Comparator;

import enums.Urgency;
import participants.Patient;

/*
 * Copyright (c)
 * Musat Bianca, 323CA
*/

public class ExamComparator implements Comparator<Patient> {
    public static final int THREE = 3;

    /**
     * Override the compare method.
     * Compares two patients first by urgency, then by severity and then by
     * name
     * @param p1  the first patient
     * @param p2  the second patient
     * @return 1 for greater, -1 for lower, 0 for equal
     */
    @Override
    public int compare(Patient p1, Patient p2) {
        if (compareUrgency(p1.getUrgency(), p2.getUrgency()) > 0) {
            return -1;
        } else if (compareUrgency(p1.getUrgency(), p2.getUrgency()) < 0) {
            return 1;
        } else {
            int severity = -(p1.getState().getSeverity() - p2.getState().getSeverity());
            if (severity > 0) {
                return 1;
            } else if (severity < 0) {
                return -1;
            } else {
                return -(p1.getName().compareTo(p2.getName()));
            }
        }
    }
    /**
     * Compares two urgencies.
     * Used by the compare method
     * @param u1  the first urgency
     * @param u2  the second urgnecy
     * @return positive for greater, negative for lower, 0 for equal
     */
    public int compareUrgency(Urgency u1, Urgency u2) {
        int urg1;
        int urg2;
        if (u1.equals(Urgency.IMMEDIATE)) {
            urg1 = THREE;
        } else if (u1.equals(Urgency.URGENT)) {
            urg1 = 2;
        } else if (u1.equals(Urgency.LESS_URGENT)) {
            urg1 = 1;
        } else {
            urg1 = 0;
        }
        if (u2.equals(Urgency.IMMEDIATE)) {
            urg2 = THREE;
        } else if (u2.equals(Urgency.URGENT)) {
            urg2 = 2;
        } else if (u2.equals(Urgency.LESS_URGENT)) {
            urg2 = 1;
        } else {
            urg2 = 0;
        }
        return urg1 - urg2;
    }
}
