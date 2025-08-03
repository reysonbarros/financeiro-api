package com.financeiro.api.scenario;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public abstract class AbstractTestScenario {
    protected String scenarioName;
}
