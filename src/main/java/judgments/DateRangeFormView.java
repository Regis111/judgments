package judgments;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.time.LocalDate;

public class DateRangeFormView extends JFrame {
    private final DatePickerSettings startDatePickerSettings;
    private final DatePickerSettings endDatePickerSettings;

    private final DatePicker startDatePicker;
    private final DatePicker endDatePicker;

    private final Controller controller;

    private static final LocalDate lowerLimit = LocalDate.of(2012, 1,1);
    private static final LocalDate upperLimit = LocalDate.now();


    public DateRangeFormView(Controller controller) {
        this.controller = controller;

        setTitle("Form");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // date settings
        startDatePickerSettings = new DatePickerSettings();
        endDatePickerSettings = new DatePickerSettings();

        startDatePicker = new DatePicker(startDatePickerSettings);
        endDatePicker = new DatePicker(endDatePickerSettings);

        startDatePicker.addPropertyChangeListener("date", this::startDateChanged);
        endDatePicker.addPropertyChangeListener("date", this::endDateChanged);

        startDatePickerSettings.setDateRangeLimits(lowerLimit, upperLimit);
        endDatePickerSettings.setDateRangeLimits(lowerLimit, upperLimit);

        // date pickers
        JPanel datePickerPane = new JPanel();
        datePickerPane.setLayout(new BoxLayout(datePickerPane, BoxLayout.PAGE_AXIS));
        datePickerPane.add(startDatePicker);
        datePickerPane.add(Box.createRigidArea(new Dimension(5,5)));
        datePickerPane.add(endDatePicker);
        datePickerPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        //import button
        JButton importButton = new JButton("Import");
        importButton.addActionListener(this::handleForm);
        importButton.addActionListener(this::closeFrame);
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this::closeFrame);

        // buttons
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(cancelButton);
        buttonPane.add(Box.createRigidArea(new Dimension(5,5)));
        buttonPane.add(importButton);

        Container contentPane = getContentPane();
        contentPane.add(datePickerPane, BorderLayout.CENTER);
        contentPane.add(buttonPane, BorderLayout.PAGE_END);

        pack();
        setLocationRelativeTo(null);
    }

    private void startDateChanged(PropertyChangeEvent propertyChangeEvent) {
        endDatePickerSettings.setDateRangeLimits(startDatePicker.getDate(), upperLimit);
    }

    private void endDateChanged(PropertyChangeEvent propertyChangeEvent) {
        startDatePickerSettings.setDateRangeLimits(lowerLimit, endDatePicker.getDate());
    }

    private void closeFrame(ActionEvent actionEvent) {
        setVisible(false);
        dispose();
    }

    private void handleForm(ActionEvent actionEvent) {
        LocalDate start = startDatePicker.getDate();
        LocalDate last = endDatePicker.getDate();
        controller.updateJudgments(start, last);
    }
}
