package participants;
import enums.State;

/*
 * Copyright (c)
 * Musat Bianca, 323CA
*/

public class Doctor {
    private String type;  // type of doctor
    private boolean isSurgeon;  // specifies if the doctor is a surgeon
    private int maxForTreatment;  // maximum severity for treatment
    private int positionInList = 0;  // doctor`s position in the initial list

    /**
     * Returns the C1 constant for each doctor.
     * @param the doctor.
     * @return the C1 constant
     */
    public double getC1(Doctor d) {
        if (d.type.equals("CARDIOLOGIST")) {
            return 0.4;
        } else if (d.type.equals("ER_PHYSICIAN")) {
            return 0.1;
        } else if (d.type.equals("GASTROENTEROLOGIST")) {
            return 0.5;
        } else if (d.type.equals("GENERAL_SURGEON")) {
            return 0.2;
        } else if (d.type.equals("INTERNIST")) {
            return 0.01;
        } else {
            return 0.5;
        }
    }
    /**
     * Returns the C2 constant for each doctor.
     * @param the doctor.
     * @return the C2 constant
     */
    public double getC2(Doctor d) {
        if (d.type.equals("CARDIOLOGIST")) {
            return 0.1;
        } else if (d.type.equals("ER_PHYSICIAN")) {
            return 0.3;
        } else if (d.type.equals("GASTROENTEROLOGIST")) {
            return 0.0;
        } else if (d.type.equals("GENERAL_SURGEON")) {
            return 0.2;
        } else if (d.type.equals("INTERNIST")) {
            return 0.0;
        } else {
            return 0.1;
        }
    }
    /**
     * Returns the State for each doctor hospitalizing a pacient.
     * @param the doctor.
     * @return State of doctor
     */
    public State hospitalized(Doctor d) {
        if (d.type.equals("CARDIOLOGIST")) {
            return State.HOSPITALIZED_CARDIO;
        } else if (d.type.equals("ER_PHYSICIAN")) {
            return State.HOSPITALIZED_ERPHYSICIAN;
        } else if (d.type.equals("GASTROENTEROLOGIST")) {
            return State.HOSPITALIZED_GASTRO;
        } else if (d.type.equals("GENERAL_SURGEON")) {
            return State.HOSPITALIZED_SURGEON;
        } else if (d.type.equals("INTERNIST")) {
            return State.HOSPITALIZED_INTERNIST;
        } else {
            return State.HOSPITALIZED_NEURO;
        }
    }
    /**
     * Returns the State for each doctor sending the pacient home.
     * @param the doctor.
     * @return State of doctor
     */
    public State home(Doctor d) {
        if (d.type.equals("CARDIOLOGIST")) {
            return State.HOME_CARDIO;
        } else if (d.type.equals("ER_PHYSICIAN")) {
            return State.HOME_ERPHYSICIAN;
        } else if (d.type.equals("GASTROENTEROLOGIST")) {
            return State.HOME_GASTRO;
        } else if (d.type.equals("GENERAL_SURGEON")) {
            return State.HOME_SURGEON;
        } else if (d.type.equals("INTERNIST")) {
            return State.HOME_INTERNIST;
        } else {
            return State.HOME_NEURO;
        }
    }
    /**
     * Determines the State for each doctor operating a pacient.
     * @param the doctor.
     * @return State of doctor
     */
    public State operated(Doctor d) {
        if (d.type.equals("CARDIOLOGIST")) {
            return State.OPERATED_CARDIO;
        } else if (d.type.equals("ER_PHYSICIAN")) {
            return State.OPERATED_ERPHYSICIAN;
        } else if (d.type.equals("GENERAL_SURGEON")) {
            return State.OPERATED_SURGEON;
        } else {
            return State.OPERATED_NEURO;
        }
    }
    /**
     * Function to determine the name of doctor for output.
     * @param the doctor.
     * @return name of doctor
     */
    public String doctorsName(Doctor d) {
        if (d.type.equals("CARDIOLOGIST")) {
            return "Cardiologist";
        } else if (d.type.equals("ER_PHYSICIAN")) {
            return "ERPhysician";
        } else if (d.type.equals("GASTROENTEROLOGIST")) {
            return "Gastroenterologist";
        } else if (d.type.equals("GENERAL_SURGEON")) {
            return "General Surgeon";
        } else if (d.type.equals("INTERNIST")) {
            return "Internist";
        } else {
            return "Neurologist";
        }
    }

    /**
     * Returns the type of doctor.
     * @return the type of doctor.
     */
    public String getType() {
        return type;
    }
    /**
     * Sets the type of doctor.
     * @param the type of doctor.
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     * Returns true if the doctor is a surgeon, false otherwise.
     * @return true for surgeon, false otherwise.
     */
    public boolean getIsSurgeon() {
        return isSurgeon;
    }
    /**
     * Sets the value of truth of the variable isSurgeon.
     * @param the value of truth of the variable isSurgeon.
     */
    public void setIsSurgeon(boolean isSurgeon) {
        this.isSurgeon = isSurgeon;
    }
    /**
     * Returns the maximum severity for treatment.
     * @return the maximum severity for treatment.
     */
    public int getMaxForTreatment() {
        return maxForTreatment;
    }
    /**
     * Sets the maximum severity for treatment.
     * @param the maximum severity for treatment.
     */
    public void setMaxForTreatment(int maxForTreatment) {
        this.maxForTreatment = maxForTreatment;
    }
    /**
     * Returns the doctor`s position in initial list.
     * @return the doctor`s position in initial list.
     */
    public int getPositionInList() {
        return positionInList;
    }
    /**
     * Sets the doctor`s position in initial list.
     * @param the doctor`s position in initial list.
     */
    public void setPositionInList(int positionInList) {
        this.positionInList = positionInList;
    }

}
