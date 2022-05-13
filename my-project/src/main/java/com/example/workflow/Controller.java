package com.example.workflow;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;
import org.camunda.bpm.engine.history.HistoricVariableInstance;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final BpmService bpmService;

    @GetMapping(value = "hello")
    public void hello(){
        Map<String, Object> variables = new HashMap<>();
        variables.put("Diep", "Khong biet code java");
        String processName = "my-project-process";
        String processInstanceId = bpmService.startProcessInstanceByKey(processName, variables);
        HistoricVariableInstance reply = bpmService.getHistoricVariableByName(processInstanceId, "Diep");
        System.out.println("Ket qua sau xu ly : " + reply.getValue());
    }
}
