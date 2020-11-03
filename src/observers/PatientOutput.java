/*
 * Copyright (c)
 * Musat Bianca, 323CA
*/

package observers;

import java.util.Observable;
import java.util.Observer;

import game.Simulator;

public final class PatientOutput implements Observer {

    private static PatientOutput instance = new PatientOutput();

    /**
     * PatientOutput constructor.
     */
    private PatientOutput() { }

    /**
     * Returns the PatientOutput instance.
     * @return the PatientOutput instance
     */
    public static PatientOutput getInstance() {
        return instance;
    }

    /**
     * update method for Observer pattern.
     * @param o    the subject
     * @param arg  argument
     */
    @Override
    public void update(Observable o, Object arg) {
        Simulator sim = ((Simulator) o).getInstance();  // get the instance
                                                        // of the subject

        System.out.println("~~~~ Patients in round " + (sim.getTime() + 1)
                                                                + " ~~~~");
        // print the output for Patients
        for (int i = 0; i < sim.getPatientOutput().size(); i++) {
            System.out.println(sim.getPatientOutput().get(i));
        }
        System.out.println();
    }

}
