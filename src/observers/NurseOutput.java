/*
 * Copyright (c)
 * Musat Bianca, 323CA
*/

package observers;

import java.util.Observable;
import java.util.Observer;

import game.Simulator;

public final class NurseOutput implements Observer {

    private static NurseOutput instance = new NurseOutput();

    /**
     * NurseOutput constructor.
     */
    private NurseOutput() { }

    /**
     * Returns the NurseOutput instance.
     * @return the NurseOutput instance
     */
    public static NurseOutput getInstance() {
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

        System.out.println("~~~~ Nurses treat patients ~~~~");
        // print the output for Nurses
        for (int i = 0; i < sim.getNurseOutput().size(); i++) {
            System.out.println(sim.getNurseOutput().get(i));
        }
        System.out.println();
    }

}
