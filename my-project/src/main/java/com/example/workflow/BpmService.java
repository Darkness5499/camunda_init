package com.example.workflow;

import org.camunda.bpm.engine.history.HistoricVariableInstance;

import java.util.Map;

public interface BpmService {
    HistoricVariableInstance getHistoricVariableByName(String processId, String name);

    String startProcessInstanceByKey(String processName, Map<String, Object> variables);

    void checkResult(String processInstanceId);
}
