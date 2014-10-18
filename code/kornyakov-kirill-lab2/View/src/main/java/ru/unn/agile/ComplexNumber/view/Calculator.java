package ru.unn.agile.ComplexNumber.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import ru.unn.agile.ComplexNumber.viewmodel.ILogger;
import ru.unn.agile.ComplexNumber.viewmodel.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    @FXML
    private TextField txtZ1Re;
    @FXML
    private TextField txtZ1Im;
    @FXML
    private TextField txtZ2Re;
    @FXML
    private TextField txtZ2Im;
    @FXML
    private ComboBox cbOperation;
    @FXML
    private Button btnCalc;
    @FXML
    private Label lbResult;
    @FXML
    private Label lbStatus;
    @FXML
    private TextArea areaLog;

    @FXML
    @SuppressWarnings("unchecked") // FIXME
    void initialize() {
        ViewModel viewModel = new ViewModel(new ArrayLogger());
        txtZ1Re.textProperty().bindBidirectional(viewModel.re1Property());
        txtZ1Re.focusedProperty().addListener(viewModel.getFocusChangeListener());

        txtZ1Im.textProperty().bindBidirectional(viewModel.im1Property());
        txtZ1Im.focusedProperty().addListener(viewModel.getFocusChangeListener());

        txtZ2Re.textProperty().bindBidirectional(viewModel.re2Property());
        txtZ2Re.focusedProperty().addListener(viewModel.getFocusChangeListener());

        txtZ2Im.textProperty().bindBidirectional(viewModel.im2Property());
        txtZ2Im.focusedProperty().addListener(viewModel.getFocusChangeListener());

        cbOperation.itemsProperty().bind(viewModel.operationsProperty());
        cbOperation.valueProperty().bindBidirectional(viewModel.operationProperty());
        cbOperation.valueProperty().addListener(viewModel.getOperationChangedListener());

        btnCalc.disableProperty().bind(viewModel.isCalculationPossibleProperty().not());
        btnCalc.setOnAction(viewModel.getCalculationFiredEventHandler());

        areaLog.textProperty().bind(viewModel.logsProperty());

        lbResult.textProperty().bind(viewModel.resultProperty());
        lbStatus.textProperty().bind(viewModel.statusProperty());
    }

    private class ArrayLogger implements ILogger {
        private final List<String> log = new ArrayList<>();
        @Override
        public void log(final String s) {
            log.add(s);
            System.out.println(s);
        }
        @Override
        public final List<String> getLog() {
            return log;
        }
    }
}
