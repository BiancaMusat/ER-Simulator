package participants;

import java.util.ArrayList;

import enums.IllnessType;

public class TypeOfDoctors {
    public static final double FOUR = 0.4;
    public static final double ONE = 0.1;
    public static final double FIVE = 0.5;
    public static final double TWO = 0.2;
    public static final double ZONE = 0.01;
    public static final double THREE = 0.5;

    private String doctorsType;  // Type of doctor
    private ArrayList<IllnessType> illnesses;  // the array of illnesses
                                               //the doctor can treat
    private double const1; // C1 constant
    private double const2;  // C2 constant

    /**
     * TypeOfDocotors constructor.
     *
     * @param doctorsType type of doctor
     * @param illnesses  the array of illnesses
     * @param c1  C1 constant
     * @param c2  C2 constant
     */
    public TypeOfDoctors(String doctorsType, ArrayList<IllnessType> illnesses,
            double c1, double c2) {
        this.doctorsType = doctorsType;
        this.illnesses = illnesses;
        const1 = c1;
        const2 = c2;
    }
    /**
     * Returns the array of doctors containing information about the ilnesses.
     * and the C1 and C2 constants
     * @return the static array of doctors
     */
    private static ArrayList<TypeOfDoctors> getDoctors() {
        ArrayList<TypeOfDoctors> typeOfDoctors = new ArrayList<TypeOfDoctors>();
        ArrayList<IllnessType> ills1 = new ArrayList<IllnessType>();
        ills1.add(IllnessType.HEART_ATTACK);
        ills1.add(IllnessType.HEART_DISEASE);
        typeOfDoctors.add(new TypeOfDoctors("CARDIOLOGIST", ills1, FOUR, ONE));

        ArrayList<IllnessType> ills2 = new ArrayList<IllnessType>();
        ills2.add(IllnessType.ALLERGIC_REACTION);
        ills2.add(IllnessType.BROKEN_BONES);
        ills2.add(IllnessType.BURNS);
        ills2.add(IllnessType.CAR_ACCIDENT);
        ills2.add(IllnessType.CUTS);
        ills2.add(IllnessType.HIGH_FEVER);
        ills2.add(IllnessType.SPORT_INJURIES);
        typeOfDoctors.add(new TypeOfDoctors("ER_PHYSICIAN", ills2, ONE, THREE));

        ArrayList<IllnessType> ills3 = new ArrayList<IllnessType>();
        ills3.add(IllnessType.ABDOMINAL_PAIN);
        ills3.add(IllnessType.ALLERGIC_REACTION);
        ills3.add(IllnessType.FOOD_POISONING);
        typeOfDoctors.add(new TypeOfDoctors("GASTROENTEROLOGIST", ills3, FIVE, 0.0));

        ArrayList<IllnessType> ills4 = new ArrayList<IllnessType>();
        ills4.add(IllnessType.ABDOMINAL_PAIN);
        ills4.add(IllnessType.BURNS);
        ills4.add(IllnessType.CAR_ACCIDENT);
        ills4.add(IllnessType.CUTS);
        ills4.add(IllnessType.SPORT_INJURIES);
        typeOfDoctors.add(new TypeOfDoctors("GENERAL_SURGEON", ills4, TWO, TWO));

        ArrayList<IllnessType> ills5 = new ArrayList<IllnessType>();
        ills5.add(IllnessType.ABDOMINAL_PAIN);
        ills5.add(IllnessType.ALLERGIC_REACTION);
        ills5.add(IllnessType.FOOD_POISONING);
        ills5.add(IllnessType.HEART_DISEASE);
        ills5.add(IllnessType.HIGH_FEVER);
        ills5.add(IllnessType.PNEUMONIA);
        typeOfDoctors.add(new TypeOfDoctors("INTERNIST", ills5, ZONE, 0.0));

        ArrayList<IllnessType> ills6 = new ArrayList<IllnessType>();
        ills6.add(IllnessType.STROKE);
        typeOfDoctors.add(new TypeOfDoctors("NEUROLOGIST", ills6, FIVE, ZONE));
        return typeOfDoctors;
    }
    // static final array containing all type of doctors and the
    // information we know about them
    public static final ArrayList<TypeOfDoctors> TYPEOFDOCTORS = getDoctors();

    /**
     * Returns the type of doctor.
     * @return the type of doctor.
     */
    public String getDoctorsType() {
        return doctorsType;
    }
    /**
     * Sets the type of doctor.
     * @param the type of doctor.
     */
    public void setDoctorsType(String doctorsType) {
        this.doctorsType = doctorsType;
    }
    /**
     * Returns the array of illnesses.
     * @return the array of illnesses.
     */
    public ArrayList<IllnessType> getIllnesses() {
        return illnesses;
    }
    /**
     * Sets the array of illnesses.
     * @param the array of illnesses.
     */
    public void setIllnesses(ArrayList<IllnessType> illnesses) {
        this.illnesses = illnesses;
    }
    /**
     * Returns the C1 constant.
     * @return the C1 constant.
     */
    public double getConst1() {
        return const1;
    }
    /**
     * Sets the C1 constant.
     * @param the C1 constant.
     */
    public void setConst1(double c1) {
        const1 = c1;
    }
    /**
     * Returns the C2 constant.
     * @return the C2 constant.
     */
    public double getConst2() {
        return const2;
    }
    /**
     * Sets the C2 constant.
     * @param the C2 constant.
     */
    public void setConst2(double c2) {
        const2 = c2;
    }

}
