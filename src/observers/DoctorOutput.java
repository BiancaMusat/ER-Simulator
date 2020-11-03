/*
 * Copyright (c)
 * Musat Bianca, 323CA
*/

package observers;

import java.util.Observable;
import java.util.Observer;

import game.Simulator;

public final class DoctorOutput implements Observer {

    private static DoctorOutput instance = new DoctorOutput();

    /**
     * DoctorOutput constructor.
     */
    private DoctorOutput() { }

    /**
     * Returns the DoctorOutput instance.
     * @return the DoctorOutput instance
     */
    public static DoctorOutput getInstance() {
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

        System.out.println("~~~~ Doctors check their hospitalized "
                                + "patients and give verdicts ~~~~");
        // print the output for Doctors
        for (int i = 0; i < sim.getDoctorOutput().size(); i++) {
            System.out.println(sim.getDoctorOutput().get(i));
        }
        System.out.println();
    }

}
