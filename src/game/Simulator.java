package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;

import comparators.AlphabeticalComparator;
import comparators.NumericalComparator;
import enums.InvestigationResult;
import enums.State;
import observers.DoctorOutput;
import observers.NurseOutput;
import observers.PatientOutput;
import participants.ERTehnitian;
import participants.Patient;
import participants.TypeOfDoctors;
import queues.ExaminationQueue;
import queues.InvestigationsQueue;
import queues.TriageQueue;

/*
 * Copyright (c)
 * Musat Bianca, 323CA
*/

public final class Simulator extends Observable {
    public static final int SIX = 6;
    public static final int TWENTYTWO = 22;
    public static final int THREE = 3;

    private int time;  // number of round
    private TriageQueue triageQueue = new TriageQueue();
    private ExaminationQueue examinationQueue = new ExaminationQueue();
    private InvestigationsQueue investigationQueue = new InvestigationsQueue();
    private ArrayList<String> patientOutput = new ArrayList<String>(); // array
                                                        // for patients output
    private ArrayList<String> doctorOutput = new ArrayList<String>();  // array
                                                        // for doctors output
    private ArrayList<String> nurseOutput = new ArrayList<String>();  // array
                                                        // for nurses output
    // array for hospitalized patients
    private ArrayList<Patient> hospitalizedPatients = new ArrayList<Patient>();
    // array for patients that have been sent home
    private ArrayList<Patient> sentHomePatients = new ArrayList<Patient>();

    private static Simulator simulator = null;  // Simulator instance
    /**
     * DoctorOutput constructor.
     */
    private Simulator() {   }

    /**
     * Returns the Simulator instance.
     * @return the Simulator instance
     */
    public static Simulator getInstance() {
        if (simulator == null) {
            simulator = new Simulator();
        }
        return simulator;
    }

    /**
     * update method for Observer pattern.
     * sets the changes and notifies observers
     */
    public void update() {
        this.setChanged();
        this.notifyObservers();
    }

    /*
     * overrides notifyObservers method
     */
    @Override
    public void notifyObservers(Object arg0) {
        super.notifyObservers(arg0);
    }

    /**
     * Simulates the program logic.
     * @param input  the data from input
     */
    public void simulation(Input input) {
        // sets the initial position in the input list for each doctor
        for (int j = 0; j < input.getDoctors().size(); j++) {
            input.getDoctors().get(j).setPositionInList(j);
        }

        int i = 0;  // index of current patient in input list
        // adds all the doctors in the examination queue
        for (int j = 0; j < input.getDoctors().size(); j++) {
            examinationQueue.getDoctors().add(input.getDoctors().get(j));
        }
        //  here starts the simulation for each round
        for (time = 0; time < input.getSimulationLength(); time++) {
            // adds new patients in triage queue
            while (i < input.getIncomingPatients().size()
                   && (input.getIncomingPatients().get(i).getTime() == time)) {
                triageQueue.getPatients().add(input.getIncomingPatients().
                                                                    get(i));
                i++;
            }
            triageQueue.orderBySeverity();  // order patients in triageQueue
                                            // by severity
            //  gets the minimum between number of patients in triage queue
            //  and number of nurses
            int min = minimum(triageQueue.getPatients().size(),
                                            input.getNurses());
            for (int j = 0; j < min; j++) {  // adds patients
                                            // in examination quque
                examinationQueue.getPatients().add(triageQueue.
                                        getPatients().get(0));
                triageQueue.getPatients().remove(0);
            }
            examinationQueue.estimateUrgency(); // estimate urgency
                                                // for each patient
            examinationQueue.orderByUrgency(); // order patients by
                                               // urgency
            //  simulates what happens in the examination queue
            for (int j = 0; j < examinationQueue.getPatients().size(); j++) {
                int ok = 0;  // ok == 0 means that we didn`t find a doctor for
                            // the current pacient
                for (int k = 0; k < examinationQueue.getDoctors().size(); k++) {
                    if (ok == 0) {
                        int ind = 0;
                        // search for the doctor in the static array of doctors
                        for (int l = 0; l < SIX; l++) {
                            if (examinationQueue.getDoctors().get(k).getType().
                                    equals(TypeOfDoctors.TYPEOFDOCTORS.get(l).
                                    getDoctorsType())) {
                                ind = l;
                                break;
                            }
                        }
                        for (int n = 0; n < TypeOfDoctors.TYPEOFDOCTORS.
                                get(ind).getIllnesses().size(); n++) {
                            if (TypeOfDoctors.TYPEOFDOCTORS.get(ind).
                                    getIllnesses().get(n).
                                    equals(examinationQueue.getPatients().
                                    get(j).getState().getIllnessName())) {
                                if ((!examinationQueue.getPatients().get(j).
                                        getInvestigationResult().
                                        equals(InvestigationResult.OPERATE))
                                        || (examinationQueue.getPatients().
                                        get(j).getInvestigationResult().
                                        equals(InvestigationResult.OPERATE)
                                        && examinationQueue.getDoctors().
                                        get(k).getIsSurgeon())) {

                                    examinationQueue.getPatients().get(j).
                                            setDoctor(examinationQueue.
                                            getDoctors().get(k));
                                    ok = 1;  // we found a doctor
                                            // that can treat the pacient
                                    break;
                                }
                            }
                        }
                        if (ok == 1) {  // if we found a doctor,
                                        // we add it to the end of the list
                            examinationQueue.getDoctors().
                                add(examinationQueue.getDoctors().get(k));
                            examinationQueue.getDoctors().remove(k);
                        }
                    }
                }
                if (ok == 0) {  // if we couldn`t find a doctor,
                                // the pacient is transfered to other hospital
                    patientOutput.add(examinationQueue.getPatients().get(j).
                          getName() + " is " + State.OTHERHOSPITAL.getValue());
                    examinationQueue.getPatients().get(j).setDoctor(null);
                }
            }
            //  all the pacients in examination queue are examined
            for (int j = 0; j < examinationQueue.getPatients().size(); j++) {
                if (examinationQueue.getPatients().get(j).getDoctor() != null) {
                    //  if the pacient hasn`t been diagnosed
                    if (examinationQueue.getPatients().get(j).
                                   getInvestigationResult().
                                   equals(InvestigationResult.NOT_DIAGNOSED)) {
                        //  if the pacient can be sent home with treatment
                        if (examinationQueue.getPatients().get(j).
                                getState().getSeverity() <= examinationQueue.
                                getPatients().get(j).getDoctor().
                                getMaxForTreatment()) {

                            State st = examinationQueue.getPatients().get(j).
                                        getDoctor().home(examinationQueue.
                                        getPatients().get(j).getDoctor());
                            patientOutput.add(examinationQueue.getPatients().
                                    get(j).getName() + " is " + st.getValue());

                        } else {  //  if the pacient must go to ERTehnitian
                            investigationQueue.getPatients().
                                    add(examinationQueue.getPatients().get(j));
                        }
                      //  if the pacient needs to be treated
                    } else if (examinationQueue.getPatients().get(j).
                                       getInvestigationResult().
                                       equals(InvestigationResult.TREATMENT)) {
                        // state that the pacient is in examination queue
                        patientOutput.add(examinationQueue.getPatients().
                                get(j).getName() + " is " + examinationQueue.
                                getPatients().get(j).getDoctor().
                                home(examinationQueue.getPatients().
                                get(j).getDoctor()).getValue());
                      //  if the pacient must be operated
                    } else if (examinationQueue.getPatients().get(j).
                                    getInvestigationResult().
                                    equals(InvestigationResult.OPERATE)) {
                        //  calculate severity
                        double sev = (examinationQueue.getPatients().get(j).
                                getState().getSeverity() * examinationQueue.
                                getPatients().get(j).getDoctor().
                                getC2(examinationQueue.getPatients().
                                get(j).getDoctor()));
                        examinationQueue.getPatients().get(j).getState().
                            setSeverity(examinationQueue.getPatients().
                            get(j).getState().getSeverity()
                            - Math.round((float) (sev)));
                        // calculate severity for number of rounds
                        sev = examinationQueue.getPatients().get(j).getState().
                                getSeverity() * examinationQueue.getPatients().
                                get(j).getDoctor().getC1(examinationQueue.
                                getPatients().get(j).getDoctor());
                        // sets new severity
                        examinationQueue.getPatients().get(j).getState().
                            setSeverity(examinationQueue.getPatients().
                            get(j).getState().getSeverity() - TWENTYTWO);
                        // sets number of rounds
                        examinationQueue.getPatients().get(j).
                                    setRoundsNumber(maximum(Math.
                                    round((float) (sev)), THREE));
                        //  add pacient to the hospitalized pacient array
                        hospitalizedPatients.add(examinationQueue.
                                            getPatients().get(j));
                        patientOutput.add(examinationQueue.getPatients().
                                get(j).getName() + " is " + examinationQueue.
                                getPatients().get(j).getDoctor().
                                operated(examinationQueue.getPatients().
                                get(j).getDoctor()).getValue());
                      //  if the pacient must be hospitalized
                    } else if (examinationQueue.getPatients().get(j).
                                getInvestigationResult().
                                equals(InvestigationResult.HOSPITALIZE)) {
                        // calculate severity
                        double sev = examinationQueue.getPatients().get(j).
                                getState().getSeverity() * examinationQueue.
                                getPatients().get(j).getDoctor().
                                getC1(examinationQueue.getPatients().
                                get(j).getDoctor());
                        // sets new severity
                        examinationQueue.getPatients().get(j).getState().
                             setSeverity(examinationQueue.getPatients().get(j).
                             getState().getSeverity() - TWENTYTWO);
                        // sets the number of rounds
                        examinationQueue.getPatients().get(j).
                                    setRoundsNumber(maximum(Math.
                                    round((float) (sev)), THREE));
                        //  add pacient to the hospitalized pacient array
                        hospitalizedPatients.add(examinationQueue.
                                            getPatients().get(j));
                        patientOutput.add(examinationQueue.getPatients().
                                get(j).getName() + " is " + examinationQueue.
                                getPatients().get(j).getDoctor().
                                hospitalized(examinationQueue.getPatients().
                                get(j).getDoctor()).getValue());
                    }
                }
            }
            //  empty examination queue
            examinationQueue.getPatients().removeAll(examinationQueue.
                                                        getPatients());

            investigationQueue.orderByUrgency(); //  order investigation queue
                                                //  by patients` urgency
            // gets the minimum between the number of pacients in investigation
            // queue and the number of ERTehnitians
            int minim = minimum(input.getInvestigators(), investigationQueue.
                                                        getPatients().size());
            for (int j = 0; j < minim; j++) {  //  ERTehnitians investigate
                                               // pacients
                ERTehnitian erteh = new ERTehnitian();
                //  sets the result of the investigation
                investigationQueue.getPatients().get(0).
                        setInvestigationResult(erteh.
                        sugestion(investigationQueue.getPatients().
                        get(0).getState().getSeverity()));
                //  adds pacient to examination queue
                examinationQueue.getPatients().add(investigationQueue.
                                                getPatients().get(0));
                //  removes pacient from investigation queue
                investigationQueue.getPatients().remove(0);
            }
            //  updates the patients Output
            for (int j = 0; j < examinationQueue.getPatients().size(); j++) {
                patientOutput.add(examinationQueue.getPatients().get(j).
                      getName() + " is " + State.EXAMINATIONSQUEUE.getValue());
            }
            for (int j = 0; j < triageQueue.getPatients().size(); j++) {
                patientOutput.add(triageQueue.getPatients().get(j).getName()
                        + " is " + State.TRIAGEQUEUE.getValue());
            }
            for (int j = 0; j < investigationQueue.getPatients().size(); j++) {
                patientOutput.add(investigationQueue.getPatients().get(j).
                    getName() + " is " + State.INVESTIGATIONSQUEUE.getValue());
            }
            //  orders hospitalized patients by their doctor`s position
            //  in the input list
            NumericalComparator nc = new NumericalComparator();
            Collections.sort(hospitalizedPatients, nc);
            //  doctors verifies their hospitalized patients and give verdicts
            for (int j = 0; j < hospitalizedPatients.size(); j++) {
                if (hospitalizedPatients.get(j).getState().getSeverity() <= 0
                       || hospitalizedPatients.get(j).getRoundsNumber() <= 0
                       || (hospitalizedPatients.get(j).getRoundsNumber() == 1
                       && hospitalizedPatients.get(j).getInvestigationResult().
                       equals(InvestigationResult.HOSPITALIZE))) {
                    doctorOutput.add(hospitalizedPatients.get(j).getDoctor().
                            doctorsName(hospitalizedPatients.get(j).
                            getDoctor()) + " sent " + hospitalizedPatients.
                            get(j).getName() + " home");
                    //  sets the patient id to -1 so that we will know that he
                    //  was sent home
                    hospitalizedPatients.get(j).setId(-1);
                    //  adds the pacient to the sentHomePatients array
                    getSentHomePatients().add(hospitalizedPatients.get(j));
                } else {
                    doctorOutput.add(hospitalizedPatients.get(j).getDoctor().
                           doctorsName(hospitalizedPatients.get(j).getDoctor())
                           + " says that " + hospitalizedPatients.get(j).
                           getName() + " should remain in hospital");
                }
            }
            //  orders the hospitalized patients alphabetically
            AlphabeticalComparator alp = new AlphabeticalComparator();
            Collections.sort(hospitalizedPatients, alp);
            int indice = 0;  // the id of the nurse
            for (int j = 0; j < hospitalizedPatients.size(); j++) {
                if (indice == input.getNurses()) {
                    indice = 0;
                }
                //  updates the severity
                hospitalizedPatients.get(j).getState().
                            setSeverity(hospitalizedPatients.get(j).
                            getState().getSeverity() - TWENTYTWO);
                //  updates the number of rounds
                hospitalizedPatients.get(j).
                            setRoundsNumber(hospitalizedPatients.
                            get(j).getRoundsNumber() - 1);
                if (hospitalizedPatients.get(j).getRoundsNumber() >= 0) {
                    if (hospitalizedPatients.get(j).getRoundsNumber() != 1) {
                        //  updates the nurses output
                        nurseOutput.add("Nurse " + indice + " treated "
                                       + hospitalizedPatients.get(j).getName()
                                       + " and patient has "
                                       + hospitalizedPatients.get(j).
                                       getRoundsNumber() + " more rounds");
                    } else {
                        // updates the nurses output
                        nurseOutput.add("Nurse " + indice + " treated "
                                    + hospitalizedPatients.get(j).getName()
                                    + " and patient has "
                                    + hospitalizedPatients.get(j).
                                    getRoundsNumber() + " more round");
                    }
                        indice++;
                }
            }

            //  removes the patients that have been sent home from
            //  the hospitalizedPatients array
            for (int j = 0; j < hospitalizedPatients.size(); j++) {
                if ((hospitalizedPatients.get(j).getState().getSeverity()
                        <= 0 || hospitalizedPatients.get(j).getRoundsNumber()
                        <= 0) && (hospitalizedPatients.get(j).getId() == -1
                        || hospitalizedPatients.get(j).getId() == -1)) {
                    hospitalizedPatients.remove(j);
                    j--;
                }
            }

            ArrayList<String> patientsOutputNonDuplicate = new ArrayList<String>();
            //  remove all duplicates from patientOutput
            for (int j = 0; j < patientOutput.size(); j++) {
                String[] tokens1 = patientOutput.get(j).split(" ");
                int ok = 0;
                for (int k = j + 1; k < patientOutput.size(); k++) {
                    String[] tokens2 = patientOutput.get(k).split(" ");
                    if (tokens1[0].equals(tokens2[0])
                            && tokens1[1].equals(tokens2[1])) {
                        ok = 1;
                        break;
                    }
                }
                if (ok == 0) {
                    patientsOutputNonDuplicate.add(patientOutput.get(j));
                }
            }
            patientOutput.removeAll(patientOutput);  //  empty array
            //  copy patientsOutputNonDuplicate to patientOutput
            for (int j = 0; j < patientsOutputNonDuplicate.size(); j++) {
                patientOutput.add(patientsOutputNonDuplicate.get(j));
            }
            //  empty patientsOutputNonDuplicate array
            patientsOutputNonDuplicate.removeAll(patientsOutputNonDuplicate);
            //  sort patientOutput alphabetically
            Collections.sort(patientOutput);

            //  gets DoctorOutput instance
            DoctorOutput dr = DoctorOutput.getInstance();
            //  adds DoctorOutput to the list of observers
            simulator.getInstance().addObserver(dr);
            //  gets NurseOutput instance
            NurseOutput nr = NurseOutput.getInstance();
            //  adds NurseOutput to the list of observers
            simulator.getInstance().addObserver(nr);
            //  gets PatientOutput instance
            PatientOutput pa = PatientOutput.getInstance();
            //  adds PatientOutput to the list of observers
            simulator.getInstance().addObserver(pa);
            simulator.getInstance().update();  //  calls the update method

            //  updates the pacient output for those pacients that has been
            //  sent home
            for (int j = 0; j < getSentHomePatients().size(); j++) {
                    patientOutput.add(getSentHomePatients().get(j).getName()
                            + " is " + State.HOME_DONE_TREATMENT.getValue());
                    getSentHomePatients().remove(j);  // remove pacient from
                                                      // array
                    j--;
            }
            //  empty the nurseOutput array
            nurseOutput.removeAll(nurseOutput);
            //  empty the nurseOutput array
            doctorOutput.removeAll(doctorOutput);
        }
    }

    /**
     * Returns the maximum of two numbers.
     * @param a  first number
     * @param b  second number
     * @return the maximum of the two numbers
     */
    public int maximum(int a, int b) {
        if (a > b) {
            return a;
        }
        return b;
    }
    /**
     * Returns the minimum of two numbers.
     * @param a  first number
     * @param b  second number
     * @return the minimum of the two numbers
     */
    public int minimum(int a, int b) {
        if (a < b) {
            return a;
        }
        return b;
    }

    /**
     * Returns the time/round.
     * @return the time/round.
     */
    public int getTime() {
        return time;
    }
    /**
     * Sets the time/round.
     * @param the time/round.
     */
    public void setTime(int time) {
        this.time = time;
    }
    /**
     * Returns the triageQueue.
     * @return the triageQueue.
     */
    public TriageQueue getTriageQueue() {
        return triageQueue;
    }
    /**
     * Sets the triageQueue.
     * @param the triageQueue.
     */
    public void setTriageQueue(TriageQueue triageQueue) {
        this.triageQueue = triageQueue;
    }
    /**
     * Returns the examinationQueue.
     * @return the examinationQueue.
     */
    public ExaminationQueue getExaminationQueue() {
        return examinationQueue;
    }
    /**
     * Sets the examinationQueue.
     * @param the examinationQueue.
     */
    public void setExaminationQueue(ExaminationQueue examinationQueue) {
        this.examinationQueue = examinationQueue;
    }
    /**
     * Returns the investigationQueue.
     * @return the investigationQueue.
     */
    public InvestigationsQueue getInvestigationQueue() {
        return investigationQueue;
    }
    /**
     * Sets the investigationQueue.
     * @param the investigationQueue.
     */
    public void setInvestigationQueue(InvestigationsQueue investigationQueue) {
        this.investigationQueue = investigationQueue;
    }
    /**
     * Returns the output for the Pacients.
     * @return the output for the Pacients.
     */
    public ArrayList<String> getPatientOutput() {
        return patientOutput;
    }
    /**
     * Sets the output for the Pacients.
     * @param the output for the Pacients.
     */
    public void setPatientOutput(ArrayList<String> patientOutput) {
        this.patientOutput = patientOutput;
    }
    /**
     * Returns the output for the Doctors.
     * @return the output for the Doctors.
     */
    public ArrayList<String> getDoctorOutput() {
        return doctorOutput;
    }
    /**
     * Sets the output for the Doctors.
     * @param the output for the Doctors.
     */ void setDoctorOutput(ArrayList<String> doctorOutput) {
        this.doctorOutput = doctorOutput;
    }
    /**
     * Returns the output for the Nurses.
     * @return the output for the Nurses.
     */
    public ArrayList<String> getNurseOutput() {
        return nurseOutput;
    }
    /**
     * Sets the output for the Nurses.
     * @param the output for the Nurses.
     */
    public void setNurseOutput(ArrayList<String> nurseOutput) {
        this.nurseOutput = nurseOutput;
    }
    /**
     * Returns the array of hospitalized patients.
     * @return the array of hospitalized patients.
     */
    public ArrayList<Patient> getHospitalizedPatients() {
        return hospitalizedPatients;
    }
    /**
     * Sets the array of hospitalized patients.
     * @param the array of hospitalized patients.
     */
    public void setHospitalizedPatients(ArrayList<Patient> hospitalizedPatients) {
        this.hospitalizedPatients = hospitalizedPatients;
    }
    /**
     * Returns the array of patients that were sent home.
     * @return the array of patients that were sent home.
     */
    public ArrayList<Patient> getSentHomePatients() {
        return sentHomePatients;
    }
    /**
     * Sets the array of patients that were sent home.
     * @param the array of patients that were sent home.
     */
    public void setSentHomePatients(ArrayList<Patient> sentHomePatients) {
        this.sentHomePatients = sentHomePatients;
    }

}
