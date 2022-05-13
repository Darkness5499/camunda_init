package com.example.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ValidateTask implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("Hello WOrld");
        String varDiep = (String) delegateExecution.getVariable("Diep");
        System.out.println(varDiep + "/////////");
        varDiep = "Van khong biet code";
        delegateExecution.setVariable("Diep", varDiep);
    }
}
