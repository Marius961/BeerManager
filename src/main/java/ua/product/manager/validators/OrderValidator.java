package ua.product.manager.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.product.manager.models.Order;
import ua.product.manager.models.OrderItem;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class OrderValidator implements Validator {

    private static final int HOURS_LIMIT = 8;
    private static final int VOLUME_STEP = 5;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof Order) {
            Order order = (Order) target;
            boolean isAnyItemActive = false;
            boolean isValidVolume = true;
            for (OrderItem item : order.getOrderItems()) {
                if (item.getVolume() > 0 && !isAnyItemActive) {
                    isAnyItemActive = true;
                }
                if (item.getVolume() % VOLUME_STEP != 0 && isValidVolume) {
                    isValidVolume = false;
                }
            }
            if (!isAnyItemActive) {
                errors.rejectValue("orderItems", "order.items.empty");
            }
            if (!isValidVolume) {
                errors.rejectValue("orderItems", "order.item.volume");
            }
            Pattern p = Pattern.compile("^\\d\\d\\d\\d-\\d\\d-\\d\\d$");
            Matcher m = p.matcher(order.getExecDate());
            if (m.matches()) {
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date currentDate = null;
                Date orderExecDate = null;
                try {
                    currentDate = formatter.parse(formatter.format(new Date()));
                    orderExecDate = new SimpleDateFormat("yyyy-MM-dd").parse(order.getExecDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if ((orderExecDate) != null) {
                    if (orderExecDate.before(currentDate)) {
                        errors.rejectValue("execDate", "order.execDate.invalid");
                    }
                    Calendar now = Calendar.getInstance();
                    int hour = now.get(Calendar.HOUR_OF_DAY);
                    if (orderExecDate.equals(currentDate) && hour > HOURS_LIMIT) {
                        errors.rejectValue("execDate", "order.execDate.timeLimit");
                    }
                } else {
                    errors.rejectValue("execDate", "order.execDate.invalid");
                }
            }
        }
    }
}
