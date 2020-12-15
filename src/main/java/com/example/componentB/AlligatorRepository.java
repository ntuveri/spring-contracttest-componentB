package com.example.componentB;

import org.springframework.stereotype.Component;

@Component
public class AlligatorRepository {
	public Alligator findByName(String name) {
		throw new UnsupportedOperationException();
	}
}
