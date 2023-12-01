//package com.dti.primeiro_projeto;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.function.FunctionItemProcessor;
//import org.springframework.batch.item.support.IteratorItemReader;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import java.util.Arrays;
//import java.util.List;
//
//@Configuration
//public class ParImparBatchConfig {
//
//    @Autowired
//    PlatformTransactionManager platformTransactionManager;
//
//    @Autowired
//    JobRepository jobRepository;
//
//    @Bean
//    public Job parImparJob(Step parImparStep) {
//        return new JobBuilder("parImparJob", this.jobRepository)
//                .start(parImparStep)
//                .incrementer(new RunIdIncrementer())
//                .build();
//    }
//
//    @Bean
//    Step parImparStep() {
//        return new StepBuilder("parImparStep", this.jobRepository)
//                .<Integer, String>chunk(11, platformTransactionManager)
//                .reader(reader())
//                .processor(processor())
//                .writer(writer())
//                .build();
//
//    }
//
//    private IteratorItemReader<Integer> reader() {
//        List<Integer> numArr = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
//        return new IteratorItemReader<>(numArr.iterator());
//    }
//
//    private FunctionItemProcessor<Integer, String> processor() {
//        return new FunctionItemProcessor<>(item -> item % 2 == 0 ? String.format(item + " => par") : String.format(item + " => impar"));
//    }
//
//    private ItemWriter<String> writer() {
//        return itens -> itens.forEach(System.out::println);
//    }
//
//}
