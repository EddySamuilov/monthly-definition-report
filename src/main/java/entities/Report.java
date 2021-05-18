package entities;

import java.util.List;

public class Report {
    private int topPerformersThreshold;
    private boolean useExperienceMultiplier;
    private int periodLimit;

    public Report() {
    }

    public Report(int topPerformersThreshold, boolean useExperienceMultiplier, int periodLimit) {
        this.topPerformersThreshold = topPerformersThreshold;
        this.useExperienceMultiplier = useExperienceMultiplier;
        this.periodLimit = periodLimit;
    }

    public int getTopPerformersThreshold() {
        return topPerformersThreshold;
    }


    public boolean isUseExperienceMultiplier() {
        return useExperienceMultiplier;
    }


    public int getPeriodLimit() {
        return periodLimit;
    }
}
