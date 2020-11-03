package participants;

import enums.InvestigationResult;

/*
 * Copyright (c)
 * Musat Bianca, 323CA
*/

public class ERTehnitian {
    public static final int C1 = 75;
    public static final int C2 = 40;

    /**
     * Returns the result of the investigation based on patient`s severity.
     * @param the severity
     * @return the result of the investigation
     */
    public InvestigationResult sugestion(int sev) {
        if (sev > C1) {
            return InvestigationResult.OPERATE;
        } else if (sev <= C2) {
            return InvestigationResult.TREATMENT;
        } else {
            return InvestigationResult.HOSPITALIZE;
        }
    }
}
