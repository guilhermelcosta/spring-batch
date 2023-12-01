package com.dti.primeiro_projeto.tasklets;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ImprimeOlaTasklet implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.print("Ola,mundo!");
        return RepeatStatus.FINISHED;
    }

    public RepeatStatus execute(@Value("#{jobParameters['nome']}") String name, StepContribution contribution, ChunkContext chunkContext) throws Exception {
        System.out.printf("Ola, %s!", name);
        return RepeatStatus.FINISHED;
    }
}
