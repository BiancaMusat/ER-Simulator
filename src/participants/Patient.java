package participants;
import enums.IllnessType;
import enums.InvestigationResult;
import enums.Urgency;

/*
 * Copyright (c)
 * Musat Bianca, 323CA
*/

public class Patient {
    private int id;  // id of the patient
    private String name;  // name of the patient
    private int age;  // age of the patient
    private int time;  // time of arrival
    private State state;  // patient`s state
    private Urgency urgency = Urgency.NOT_DETERMINED;  // urgency of illness
    private int treatment = 0;  // the treatment
    private int roundsNumber = 0;  // number of rounds for treatment
    private InvestigationResult investigationResult =
            InvestigationResult.NOT_DIAGNOSED;  // result of the investigation
    private Doctor doctor = null;  // patient`s Doctor

    public class State {
        private IllnessType illnessName;  // name of the illness
        private int severity;  // severity of the illness

        /**
         * Returns the name of illness.
         * @return the name of illness.
         */
        public IllnessType getIllnessName() {
            return illnessName;
        }
        /**
         * Sets the name of illness.
         * @param the name of illness.
         */
        public void setIllnessName(IllnessType illnessName) {
            this.illnessName = illnessName;
        }
        /**
         * Returns the severity of the illness.
         * @return the severity of the illness.
         */
        public int getSeverity() {
            return severity;
        }
        /**
         * Sets the severity of the illness.
         * @param the severity of the illness.
         */
        public void setSeverity(int severity) {
            this.severity = severity;
        }
    }

    /**
     * Returns the id of the patient.
     * @return the id of the patient.
     */
    public int getId() {
        return id;
    }
    /**
     * Sets the id of the patient.
     * @param the id of the patient.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Returns the name of the patient.
     * @return the name of the patient.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the patient.
     * @param the name of the patient.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Returns the age of the patient.
     * @return the age of the patient.
     */
    public int getAge() {
        return age;
    }
    /**
     * Sets the age of the patient.
     * @param the age of the patient.
     */
    public void setAge(int age) {
        this.age = age;
    }
    /**
     * Returns the time of arrival.
     * @return the time of arrival.
     */
    public int getTime() {
        return time;
    }
    /**
     * Sets the time of arrival.
     * @param the time of arrival.
     */
    public void setTime(int time) {
        this.time = time;
    }
    /**
     * Returns the patient`s state.
     * @return the patient`s state.
     */
    public State getState() {
        return state;
    }
    /**
     * Sets the patient`s state.
     * @param the patient`s state.
     */
    public void setState(State state) {
        this.state = state;
    }
    /**
     * Returns the urgency of illness.
     * @return the urgency of illness.
     */
    public Urgency getUrgency() {
        return urgency;
    }
    /**
     * Sets the urgency of illness.
     * @param the urgency of illness.
     */
    public void setUrgency(Urgency urgency) {
        this.urgency = urgency;
    }
    /**
     * Returns the treatment.
     * @return the treatment.
     */
    public int getTreatment() {
        return treatment;
    }
    /**
     * Sets the treatment.
     * @param the treatment.
     */
    public void setTreatment(int treatment) {
        this.treatment = treatment;
    }
    /**
     * Returns the number of rounds.
     * @return the number of rounds.
     */
    public int getRoundsNumber() {
        return roundsNumber;
    }
    /**
     * Sets the number of rounds.
     * @param the number of rounds.
     */
    public void setRoundsNumber(int roundsNumber) {
        this.roundsNumber = roundsNumber;
    }
    /**
     * Returns the result of the investigation.
     * @return the result of the investigation.
     */
    public InvestigationResult getInvestigationResult() {
        return investigationResult;
    }
    /**
     * Sets the result of the investigation.
     * @param the result of the investigation.
     */
    public void setInvestigationResult(InvestigationResult investigationResult) {
        this.investigationResult = investigationResult;
    }
    /**
     * Returns the doctor that can treat the patient.
     * @return the doctor that can treat the patient.
     */
    public Doctor getDoctor() {
        return doctor;
    }
    /**
     * Sets the doctor that can treat the patient.
     * @param the doctor that can treat the patient.
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

}
