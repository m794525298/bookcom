package com.book.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.book.common.Coder;

class MD5Test {

	@Test
	void MD5test() {
		System.out.println(Coder.encrypted("ABC123123"));
	}

}
