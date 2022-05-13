package com.example.workflow;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
@Slf4j
public class BpmServiceImpl implements BpmService {

    private final HistoryService historyService;
    private final RuntimeService runtimeService;

    @Override
    public HistoricVariableInstance getHistoricVariableByName(String processId, String name) {
        List<HistoricVariableInstance> vars = historyService.createHistoricVariableInstanceQuery().processInstanceId(processId).variableName(name).list();
        return vars == null || vars.isEmpty() ? null : vars.get(0);
    }

    @Override
    public String startProcessInstanceByKey(String processName, Map<String, Object> variables) {
        return runtimeService.startProcessInstanceByKey(processName, variables).getProcessInstanceId();
    }

    public void checkResult(String processInstanceId) {
        HistoricVariableInstance isSuccess = getHistoricVariableByName(processInstanceId, "SUCCESS");
        Boolean success = (isSuccess == null) ? false : (Boolean) isSuccess.getValue();
        log.info("---> isSuccess: {}", success);
        if (!success) {
            HistoricVariableInstance message = getHistoricVariableByName(processInstanceId, "MESSAGE");
            throw new IllegalArgumentException(String.valueOf(message.getValue()));
        }
    }
}
