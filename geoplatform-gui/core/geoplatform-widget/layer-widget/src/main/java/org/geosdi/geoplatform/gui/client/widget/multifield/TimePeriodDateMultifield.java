package org.geosdi.geoplatform.gui.client.widget.multifield;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.DatePickerEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.DatePicker;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.MultiField;
import com.extjs.gxt.ui.client.widget.form.SpinnerField;
import com.extjs.gxt.ui.client.widget.form.Validator;
import com.google.gwt.i18n.client.DateTimeFormat;
import org.geosdi.geoplatform.gui.client.puregwt.reset.GPResetComponentHandler;
import org.geosdi.geoplatform.gui.puregwt.properties.WidgetPropertiesHandlerManager;

import java.util.Date;

/**
 * @author Vito Salvia - CNR IMAA geoSDI Group
 * @email vito.salvia@gmail.com
 */
public abstract class TimePeriodDateMultifield extends MultiField implements GPResetComponentHandler {

    protected DateField dateField;
    private SpinnerField hourField;
    private SpinnerField minuteField;
    protected Date date;
    private DateTimeFormat dtFormat;
    protected Date limitDate;

    public TimePeriodDateMultifield() {
        super();
        this.addComponents();
        WidgetPropertiesHandlerManager.addHandler(GPResetComponentHandler.TYPE, this);
    }

    private void addComponents() {
        super.setSpacing(20);
        this.dtFormat = DateTimeFormat.getFormat("dd-MM-yyyy");
        this.dateField = new DateField();
        this.dateField.setToolTip(dateTooltip());
        this.dateField.getPropertyEditor().setFormat(dtFormat);
        this.dateField.setWidth(100);
        this.dateField.setEditable(false);
        this.dateField.setAllowBlank(Boolean.FALSE);
        DatePicker fromDatePicker = this.dateField.getDatePicker();
        fromDatePicker.addListener(Events.Select, new Listener<DatePickerEvent>() {
            @Override
            public void handleEvent(DatePickerEvent dpe) {
                date = dpe.getDate();
            }
        });
        this.hourField = new SpinnerField();
        this.hourField.setToolTip(hourTooltip());
        this.hourField.setWidth(50);
        this.hourField.setMinValue(0);
        this.hourField.setMaxValue(23);
        this.hourField.setAllowBlank(Boolean.FALSE);

        this.minuteField = new SpinnerField();
        this.minuteField.setToolTip(minuteTooltip());
        this.minuteField.setWidth(50);
        this.minuteField.setMinValue(0);
        this.minuteField.setMaxValue(59);
        this.minuteField.setAllowBlank(Boolean.FALSE);

        super.setFieldLabel(fieldLabel());
        super.setOrientation(Style.Orientation.HORIZONTAL);
        super.add(this.dateField);
        super.add(this.hourField);
        super.add(this.minuteField);
        super.addStyleName("dateMultifield");
        super.setValidator(addValidator());
    }

    protected abstract Validator addValidator();


    protected abstract String dateTooltip();

    protected abstract String hourTooltip();

    protected abstract String minuteTooltip();

    protected abstract String fieldLabel();

    /**
     * @return {@link Date}
     */
    public Date getDate() {
        if (this.hourField.getValue() != null)
            this.date.setHours(this.hourField.getValue().intValue());
        if (this.minuteField.getValue() != null)
            this.date.setMinutes(this.minuteField.getValue().intValue());
        return this.date;
    }

    protected boolean validateDate() {
        return this.date != null && this.hourField.isValid() && this.minuteField.isValid();
    }

    @Override
    protected void onAttach() {
        super.onAttach();
//        initComponents();
    }

    private void initComponents() {
        this.date = null;
        this.dateField.reset();
        this.hourField.reset();
        this.minuteField.reset();
        super.reset();
    }

    protected void buildDateField() {
        this.dateField.setValue(this.limitDate);
        this.hourField.setValue(this.limitDate.getHours());
        this.minuteField.setValue(this.limitDate.getMinutes());
    }

    /**
     * @param dateFrom
     * @param dateTo
     */
    public void bindDate(Date dateFrom, Date dateTo) {
        dateField.setMaxValue(dateTo);
        dateField.setMinValue(dateFrom);
    }

    @Override
    public void removeFilterTime() {
        initComponents();
    }
}
