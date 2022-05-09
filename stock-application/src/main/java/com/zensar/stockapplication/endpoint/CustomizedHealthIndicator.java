package com.zensar.stockapplication.endpoint;

import java.util.Random;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.stereotype.Component;
@Component
public class CustomizedHealthIndicator extends AbstractHealthIndicator {

	@Override
	protected void doHealthCheck(Builder builder) throws Exception {
		// TODO Auto-generated method stub
		Random r=new Random();
		int random=r.nextInt(100);
		if(random%2==0) {
			builder.up();
		}
		else {
			builder.down();
		}
	}

}
