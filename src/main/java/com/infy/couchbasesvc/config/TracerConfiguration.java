package com.infy.couchbasesvc.service;

import java.util.regex.Pattern;

import javax.servlet.ServletContextEvent;

import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

import com.uber.jaeger.Configuration;
//import com.uber.jaeger.micrometer.MicrometerMetricsFactory;
import com.uber.jaeger.samplers.ConstSampler;

import io.opentracing.Tracer;
import io.opentracing.contrib.metrics.prometheus.PrometheusMetricsReporter;

@org.springframework.context.annotation.Configuration
public class TracerConfiguration {
	
	

	@Bean
    @ConditionalOnMissingBean
	public io.opentracing.Tracer tracer() {
		//MicrometerMetricsFactory metricsFactory1 = new MicrometerMetricsFactory();
	    Configuration configuration = new Configuration("couchbasesvc");
	    Tracer tracer1 = configuration
	        .withReporter(
	            new Configuration.ReporterConfiguration()
	                .withLogSpans(true)
	        )
	        .withSampler(
	            new Configuration.SamplerConfiguration()
	                .withType(ConstSampler.TYPE)
	                .withParam(1)
	        )
	        .getTracerBuilder()
	        
	        .build();
	    
		return io.opentracing.contrib.metrics.Metrics.decorate(
				tracer1,
				PrometheusMetricsReporter.newMetricsReporter()
					.withBaggageLabel("transaction","n/a")
					.build());
	}

	

	
}
