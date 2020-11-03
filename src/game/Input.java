package game;
/*
 * Copyright (c)
 * Musat Bianca, 323CA
*/

import java.util.ArrayList;

import participants.Doctor;
import participants.Patient;

public class Input {
    private int simulationLength;  // length of the simulation
    private int nurses;  // number of nurses
    private int investigators;  // number of ERTehnitians
    private ArrayList<Doctor> doctors;  // array of all doctors
    private ArrayList<Patient> incomingPatients;  // array of all patients

    /**
     * Returns the length of the simulation.
     * @return the length of the simulation.
     */
    public int getSimulationLength() {
        return simulationLength;
    }
    /**
     * Sets the length of the simulation.
     * @param the length of the simulation.
     */
    public void setSimulationLength(int simulationLength) {
        this.simulationLength = simulationLength;
    }
    /**
     * Returns the number of nurses.
     * @return the number of nurses.
     */
    public int getNurses() {
        return nurses;
    }
    /**
     * Sets the number of nurses.
     * @param the number of nurses.
     */
    public void setNurses(int nurses) {
        this.nurses = nurses;
    }
    /**
     * Returns the number of ERTehnitians.
     * @return the number of ERTehnitians.
     */
    public int getInvestigators() {
        return investigators;
    }
    /**
     * Sets the number of ERTehnitians.
     * @param the number of ERTehnitians.
     */
    public void setInvestigators(int investigators) {
        this.investigators = investigators;
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
    /**
     * Returns the array of patients.
     * @return the array of patients.
     */
    public ArrayList<Patient> getIncomingPatients() {
        return incomingPatients;
    }
    /**
     * Sets the array of patients.
     * @param the array of patients.
     */
    public void setIncomingPatients(ArrayList<Patient> incomingPatients) {
        this.incomingPatients = incomingPatients;
    }

}
