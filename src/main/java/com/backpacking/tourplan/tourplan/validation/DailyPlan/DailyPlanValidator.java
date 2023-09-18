package com.backpacking.tourplan.tourplan.validation.DailyPlan;

import com.backpacking.tourplan.dailyPlan.domain.DailyPlan;
import com.backpacking.tourplan.hourlyPlan.domain.HourlyPlan;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

import static com.backpacking.tourplan.tourplan.validation.DailyPlan.DailyPlanExceptionCode.HOURLY_PLAN_DESTINATION_EMPTY;
import static com.backpacking.tourplan.tourplan.validation.DailyPlan.DailyPlanExceptionCode.HOURLY_PLAN_OVER_TIME_LIMIT;

public class DailyPlanValidator implements ConstraintValidator<ValidDailyPlan, List<DailyPlan>> {
    @Override
    public void initialize(ValidDailyPlan constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<DailyPlan> value, ConstraintValidatorContext context) {

        boolean isHourlyPlanNotNul = true;
        boolean isHourlyPlanNotOverTwentyFourHour = true;

        for (DailyPlan dailyPlan: value){
            int totalExpectedTime = 0;
            for(HourlyPlan hourlyPlan: dailyPlan.getHourlyPlan()){
                if(!StringUtils.hasText(hourlyPlan.getDestination())){
                    context.disableDefaultConstraintViolation();
                    this.addConstraintViolation(context,
                            HOURLY_PLAN_DESTINATION_EMPTY.getDescription(),
                            HOURLY_PLAN_DESTINATION_EMPTY.name());
                    isHourlyPlanNotNul = false;
                }
                totalExpectedTime += hourlyPlan.getExpectedTourTime();
            }
            if(totalExpectedTime >= 24){
                context.disableDefaultConstraintViolation();
                this.addConstraintViolation(context,
                        HOURLY_PLAN_OVER_TIME_LIMIT.getDescription(),
                        HOURLY_PLAN_OVER_TIME_LIMIT.name());
                isHourlyPlanNotOverTwentyFourHour = false;
            }
        }
        return isHourlyPlanNotNul && isHourlyPlanNotOverTwentyFourHour;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String errorMessage, String firstNode) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorMessage)
                .addPropertyNode(firstNode)
                .addConstraintViolation();
    }
}
